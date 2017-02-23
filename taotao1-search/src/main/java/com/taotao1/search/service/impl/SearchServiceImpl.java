package com.taotao1.search.service.impl;

import com.taotao1.search.dao.SearchDao;
import com.taotao1.search.pojo.SearchResult;
import com.taotao1.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenchao on 2016/12/31.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryString);
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlight(true);
        solrQuery.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        solrQuery.setHighlightSimplePost("</font>");
        solrQuery.set("df", "item_title");
        SearchResult search = searchDao.search(solrQuery);
        int pageCount = (int) ((search.getRecordCount() - 1) / rows);
        search.setPageCount(pageCount);
        search.setCurPage(page);
        return search;
    }
}
