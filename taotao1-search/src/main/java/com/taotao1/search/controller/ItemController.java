package com.taotao1.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2016/12/31.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAll() {
        return itemService.importItems();
    }
}
