package com.shenchao.taotao.portal.controller;

import com.shenchao.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2016/12/18.
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/index")
    public String showIndex(Model model){
        //取打广告位内容
        String json = contentService.getAd1List();
        model.addAttribute("ad1", json);
        return "index";
    }

    @RequestMapping("/httpclient/test")
    @ResponseBody
    public String testHttpClient(String username, String password) {
        System.out.println(username+":"+password);
        return "OK";
    }
}
