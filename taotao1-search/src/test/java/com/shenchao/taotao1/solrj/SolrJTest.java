package com.shenchao.taotao1.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by shenchao on 2016/12/31.
 */
public class SolrJTest {
    @Test
    public void testSolrJ() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.187.128:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "solrtest01");
        document.addField("item_title", "测试产品");
        document.addField("item_sell_point","卖点");
        solrServer.add(document);
        solrServer.commit();
    }
    @Test
    public void testQuery() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://192.168.187.128:8080/solr");
        SolrQuery query = new SolrQuery("item_title:试品");
        QueryResponse query1 = solrServer.query(query);
        SolrDocumentList results = query1.getResults();
        for (SolrDocument document : results) {
            System.out.println(document.get("id"));
            System.out.println(document.get("item_title"));
            System.out.println(document.get("item_sell_point"));
        }
    }
    @Test
    public void testSolrCloud() throws IOException, SolrServerException {
        CloudSolrServer solrServer = new CloudSolrServer("192.168.187.128:2181,192.168.187.128:2182,192.168.187.128:2183");
        //设置默认的collection
        solrServer.setDefaultCollection("collection2");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "solrtest02");
        document.addField("item_title", "测试产品");
        document.addField("item_sell_point","卖点");
        solrServer.add(document);
        solrServer.commit();
    }


}
