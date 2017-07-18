package org.seckill.web;

import org.seckill.entity.Items;
import org.seckill.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/19.
 */
@Controller  //@Controller注解下的类添加进spring容器中
@RequestMapping("/items")//url:/模块/资源/{id}/细分
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public String getAllItems(HttpServletRequest request){
        List<Items> itemsList = itemsService.getAllItems();
        request.getSession().setAttribute("itemsList",itemsList);
        return "itemsList";
    }


    @RequestMapping(value="/getItemsByCart",method= RequestMethod.GET)
    public String getItemsByCart(HttpServletRequest request,long uID){
        Set<Items> itemsSet = itemsService.getItemsByCart(uID);
        request.getSession().setAttribute("itemsSet",itemsSet);
        return "itemsSet";
    }


}
