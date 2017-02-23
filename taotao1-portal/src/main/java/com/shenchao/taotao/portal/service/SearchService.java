package com.shenchao.taotao.portal.service;

import com.shenchao.taotao.portal.pojo.SearchResult;

/**
 * Created by shenchao on 2016/12/31.
 */
public interface SearchService {
    SearchResult search(String queryString, int page, int rows);
}
