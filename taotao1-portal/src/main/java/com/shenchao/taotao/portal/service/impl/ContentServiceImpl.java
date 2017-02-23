package com.shenchao.taotao.portal.service.impl;

import com.shenchao.common.utils.HttpClientUtil;
import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.pojo.TbContent;
import com.shenchao.taotao.portal.pojo.AdNode;
import com.shenchao.taotao.portal.service.ContentService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenchao on 2016/12/24.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;

    @Override
    public String getAd1List() {
        //调用服务获得数据
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        //把json装换成java对象
        TaotaoResult result = TaotaoResult.formatToList(json, TbContent.class);
        List<TbContent> contentList = (List<TbContent>) result.getData();
        //把内容列表装换成AdNode列表
        List<AdNode> resultList = new ArrayList<>();
        for (TbContent content:contentList) {
            AdNode node = new AdNode();
            node.setWidth(670);
            node.setHeight(240);
            node.setSrc(content.getPic());
            node.setHeightB(240);
            node.setWidthB(550);
            node.setSrcB(content.getPic2());
            node.setAlt(content.getSubTitle());
            resultList.add(node);
            node.setHref(content.getUrl());
        }
        return JsonUtils.objectToJson(resultList);
    }
}
