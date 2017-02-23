package com.taotao1.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:shenchao
 * Date:2017/1/5 23:17
 * Description:
 */
@Controller
public class PageController {
    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }
    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }

}
