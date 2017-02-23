package com.taotao1.search.service.impl;

import com.shenchao.common.utils.ExceptionUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.search.mapper.ItemMapper;
import com.taotao1.search.pojo.SearchItem;
import com.taotao1.search.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenchao on 2016/12/31.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SolrServer solrServer;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public TaotaoResult importItems() {
        List<SearchItem> itemList = itemMapper.getItemList();
        try {
            for (SearchItem searchItem : itemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_category_name", searchItem.getCategory_name());
                document.addField("item_desc", searchItem.getItem_desc());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_title", searchItem.getTitle());
                solrServer.add(document);
            }
            solrServer.commit();
            return TaotaoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
