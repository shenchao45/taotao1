package com.shenchao.taotao.portal.service.impl;

import com.shenchao.common.utils.HttpClientUtil;
import com.shenchao.taotao.portal.pojo.SearchResult;
import com.shenchao.taotao.portal.service.SearchService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenchao on 2016/12/31.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public SearchResult search(String queryString, int page, int rows) {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", queryString);
        params.put("page", page+"");
        params.put("rows", rows+"");
        String s = HttpClientUtil.doGet("http://localhost:8083/search/q", params);
        TaotaoResult result = TaotaoResult.formatToPojo(s, SearchResult.class);
        return (SearchResult) result.getData();
    }
}
