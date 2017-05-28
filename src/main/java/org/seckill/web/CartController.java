package org.seckill.web;

import org.seckill.entity.Cart;
import org.seckill.entity.Items;
import org.seckill.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daiwei on 2017/5/20.
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping(value="/add",method= RequestMethod.GET)
    public String addCart(HttpServletRequest request,long itemsID, long uID){
        Cart myCart = cartService.findMyCart(uID, itemsID);
        //如果购物车没有则进行添加
        if(myCart==null){
            int num = cartService.addCart(itemsID, uID);
            request.getSession().setAttribute("num",num);
            return "add_success";
        }else {
            return "add_fail";
        }
    }


    @RequestMapping(value="/findMyCart",method= RequestMethod.GET)
    public String findMyCart(HttpServletRequest request,long uID){
        List<Items> myCart=new ArrayList<Items>();
        myCart = cartService.findMyCart(uID);
        request.getSession().setAttribute("myCart",myCart);
        return "myCart";
    }

}
