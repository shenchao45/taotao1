package com.taotao1.search.dao.impl;

import com.taotao1.search.dao.SearchDao;
import com.taotao1.search.pojo.SearchItem;
import com.taotao1.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shenchao on 2016/12/31.
 */
@Repository
public class SearchDaoImpl implements SearchDao{
    @Autowired
    private SolrServer solrServer;
    @Override
    public SearchResult search(SolrQuery query) throws SolrServerException {
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        List<SearchItem> list = new ArrayList<>();
        for (SolrDocument document:results) {
            SearchItem item = new SearchItem();
            item.setId((String) document.get("id"));
            item.setCategory_name((String) document.get("item_category_name"));
            item.setPrice((Long) document.get("item_price"));
            item.setImage((String) document.get("item_image"));
            item.setTitle((String) document.get("item_title"));
            item.setSell_point((String) document.get("item_sell_point"));
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            Map<String, List<String>> map = highlighting.get(document.get("id"));
            List<String> item_title = map.get("item_title");
            String mytitle = null;
            if (item_title != null && item_title.size() > 0) {
                mytitle = item_title.get(0).toString();
            }else{
                mytitle = document.get("item_title").toString();
            }
            item.setTitle(mytitle);
            list.add(item);
        }
        SearchResult result = new SearchResult();
        result.setItemList(list);
        result.setRecordCount(results.getNumFound());
        return result;
    }
}
