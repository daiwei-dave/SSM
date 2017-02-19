package org.seckill.web;


import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller  //@Controllerע���µ������ӽ�spring������
@RequestMapping("/seckill")//url:/ģ��/��Դ/{id}/ϸ��
public class SeckillController {
	private Log LOG = LogFactory.getLog(this.getClass());

	@Autowired
	private SeckillService seckillService;
	/**
	 * ��ȡ�б�ҳ
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		 //list.jsp + model = ModelAndView
		List<Seckill> list=seckillService.getSeckillList();
		model.addAttribute("list",list);
		return "list";		
	}
	/**
	 * ��������ҳ
	 * @param seckillId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		//��idΪ�������б�ҳ
		if(seckillId==null){
			return "redirect:/seckill/list";
		}
		Seckill seckill=seckillService.getById(seckillId);
		if(seckill==null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";		
	}
	/**
	 * ��¶��ɱ��ַDTO
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SeckillResult<Exposer> result;
		try {
			Exposer exposer=seckillService.exportSeckillUrl(seckillId);
			result=new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			//��¶��ɱ��ַ�쳣
			LOG.error(e.getMessage());
			result=new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;		
	}
	/**
	 * ִ����ɱ
	 * @param seckillId
	 * @param md5
	 * @param killPhone
	 * @return
	 */
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,
			produces="application/json;charset=utf-8")
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
			@CookieValue(value="killPhone",required=false) Long killPhone){
		if(killPhone==null){
			return new SeckillResult<SeckillExecution>(false,"δע��");
		}
		try {
			SeckillExecution execution=seckillService.executeSeckill(seckillId, killPhone, md5);
			//����ִ����ɱ
			return new SeckillResult<SeckillExecution>(true, execution);
		}catch (RepeatKillException e) {
			SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
		catch (SeckillCloseException e2) {
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, execution);
		}catch (Exception e) {
			LOG.error(e.getMessage());
			SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}		
	}
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time(){
		Date now=new Date();
		return new SeckillResult(true,now.getTime());
		
	}
	
}