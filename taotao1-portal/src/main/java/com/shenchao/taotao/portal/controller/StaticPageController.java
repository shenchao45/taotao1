package com.shenchao.taotao.portal.controller;

import com.shenchao.common.utils.ExceptionUtil;
import com.shenchao.taotao.portal.service.StaticPageService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2017/1/2.
 */
@Controller
public class StaticPageController {
    @Autowired
    private StaticPageService staticPageService;

    @RequestMapping("/gen/item/{itemId}")
    @ResponseBody
    public TaotaoResult getItemPage(@PathVariable Long itemId) {
        try {
            return staticPageService.getItemHtml(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
