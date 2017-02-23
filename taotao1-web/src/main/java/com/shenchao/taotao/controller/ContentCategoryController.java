package com.shenchao.taotao.controller;

import com.shenchao.taotao.service.ContentCategoryService;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by shenchao on 2016/12/23.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
        return contentCategoryService.getContentCatList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createNode(Long parentId, String name) {
        return contentCategoryService.insertCategory(parentId, name);
    }
}
