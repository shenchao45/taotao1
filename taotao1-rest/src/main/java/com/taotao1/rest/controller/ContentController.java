package com.taotao1.rest.controller;

import com.shenchao.common.utils.ExceptionUtil;
import com.shenchao.taotao.pojo.TbContent;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by shenchao on 2016/12/24.
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/{cid}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long cid) {
        try {
            List<TbContent> list = contentService.getContentList(cid);
            return TaotaoResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/sync/content/{cid}")
    @ResponseBody
    public TaotaoResult sysncContent(@PathVariable("cid") Long cid) {
        try {
            TaotaoResult result = contentService.syncContent(cid);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
