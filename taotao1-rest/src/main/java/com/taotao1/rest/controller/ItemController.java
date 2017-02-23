package com.taotao1.rest.controller;

import com.shenchao.common.utils.ExceptionUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2017/1/2.
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/base/{itemId}")
    @ResponseBody
    public TaotaoResult getItemById(@PathVariable Long itemId) {
        try {
            return TaotaoResult.ok(itemService.getItemById(itemId));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescById(@PathVariable Long itemId) {
        try {
            return TaotaoResult.ok(itemService.getItemDescById(itemId));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParamById(@PathVariable Long itemId) {
        try {
            return TaotaoResult.ok(itemService.getItemParamById(itemId));
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
