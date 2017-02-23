package com.taotao1.search.controller;

import com.shenchao.common.utils.ExceptionUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.search.pojo.SearchResult;
import com.taotao1.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shenchao on 2016/12/31.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows){
        try {
            keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
            SearchResult search = searchService.search(keyword, page, rows);
            return TaotaoResult.ok(search);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
