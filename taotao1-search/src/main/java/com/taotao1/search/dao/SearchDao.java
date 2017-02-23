package com.taotao1.search.dao;

import com.taotao1.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * Created by shenchao on 2016/12/31.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws SolrServerException;
}
