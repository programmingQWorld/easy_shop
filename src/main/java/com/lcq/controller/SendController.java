package com.lcq.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendController {
	@RequestMapping("/{url}")
	public String url (@PathVariable("url") String url) {
		return url;
	}
	@RequestMapping("/user/{url}")
	public String userUrl (@PathVariable("url") String url) {
		System.out.println("进入 -> com.lcq.controller.SendController.userUrl()" );
		System.out.println("退出 -> com.lcq.controller.SendController.userUrl()");
		return "user/" + url;
	}
}
