package org.seckill.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	private Log LOG = LogFactory.getLog(this.getClass());
	@Autowired
	private SeckillService seckillService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		 //list.jsp + model = ModelAndView
		List<Seckill> list=seckillService.getSeckillList();
		model.addAttribute("list",list);
		return "list";		
	}
}
