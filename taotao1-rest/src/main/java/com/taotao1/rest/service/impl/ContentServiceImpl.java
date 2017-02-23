package com.taotao1.rest.service.impl;

import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.mapper.TbContentMapper;
import com.shenchao.taotao.pojo.TbContent;
import com.shenchao.taotao.pojo.TbContentExample;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.rest.component.JedisClient;
import com.taotao1.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenchao on 2016/12/24.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    @Override
    public List<TbContent> getContentList(Long cid) {
        try {
            String json = jedisClient.hget(REDIS_CONTENT_KEY, cid + "");
            if (StringUtils.isNotBlank(json)) {
                System.out.println("不需要查询，直接从redis中取得："+json);
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        try {
            jedisClient.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
            System.out.println("存储到redis缓存中");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TaotaoResult syncContent(Long cid) {
        jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
        return TaotaoResult.ok();
    }
}
