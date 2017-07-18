package org.seckill.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seckill.entity.Seckill;
import org.seckill.entity.User;
import org.seckill.service.SeckillService;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
	private Log LOG = LogFactory.getLog(this.getClass());
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user) {
		User checkUser = userService.checkUser(user.getName(), user.getPassword());
		if (checkUser!=null){
			request.getSession().setAttribute("user",checkUser);
			return "login_success";
		}else {
			return "login_fail";
		}
	}

	/**
	 * 返回首页
	 * @return
	 */
	@RequestMapping(value="/returnHome")
	public String returnHome() {
		return "login_success";
	}
}
