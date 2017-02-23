package com.shenchao.taotao.controller;

import com.shenchao.common.utils.HttpClientUtil;
import com.shenchao.taotao.pojo.TbContent;
import com.shenchao.taotao.service.ContentService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

/**
 * Created by shenchao on 2016/12/24.
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content) {
        TaotaoResult result = contentService.insertContent(content);
        HttpClientUtil.doGet("http://localhost:8081/rest/sync/content/" + content.getCategoryId());
        return result;
    }
}
