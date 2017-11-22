package springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 上午10:31:46 
 *
 * @UpdateTime 2017年11月22日 上午10:31:46 
 *
 * @Version 
 *
 * @desc
 *
 */
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value = "/req")
	public String requestTest(){
		System.out.println("正在测试热部署功能");
		return "请求测试SpringBoot环境搭建";
	}

}
