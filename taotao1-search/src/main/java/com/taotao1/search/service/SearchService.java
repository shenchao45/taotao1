package com.taotao1.search.service;

import com.taotao1.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * Created by shenchao on 2016/12/31.
 */
public interface SearchService {
    SearchResult search(String queryString, int page ,int rows) throws SolrServerException;
}
