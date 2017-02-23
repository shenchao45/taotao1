package com.shenchao.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shenchao on 2016/12/10.
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
