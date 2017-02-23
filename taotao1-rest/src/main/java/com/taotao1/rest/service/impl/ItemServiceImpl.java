package com.taotao1.rest.service.impl;

import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.mapper.TbItemDescMapper;
import com.shenchao.taotao.mapper.TbItemMapper;
import com.shenchao.taotao.mapper.TbItemParamItemMapper;
import com.shenchao.taotao.pojo.*;
import com.taotao1.rest.component.JedisClient;
import com.taotao1.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenchao on 2017/1/1.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;

    /**
     * 根据商品id查询商品
     *
     * @param id
     * @return
     */
    @Override
    public TbItem getItemById(Long id) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + id);
            if (StringUtils.isNotBlank(json)) {
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                if (tbItem != null) {
                    System.out.println("缓存中有tbItem，不需要去数据库查询了");
                    return tbItem;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItem item = itemMapper.selectByPrimaryKey(id);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + id, JsonUtils.objectToJson(item));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + id, ITEM_EXPIRE_SECOND);
            System.out.println("缓存中没有tbItem，加入缓存");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" + itemId);
            if (StringUtils.isNotBlank(json)) {
                TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                if (tbItemDesc != null) {
                    System.out.println("缓存中有tbItemDesc，不需要去数据库查询了");
                    return tbItemDesc;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" + itemId, JsonUtils.objectToJson(tbItemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + ITEM_DESC_KEY + ":" + itemId, ITEM_EXPIRE_SECOND);
            System.out.println("缓存中没有tbItemDesc，加入缓存");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbItemDesc;
    }

    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId);
            if (StringUtils.isNotBlank(json)) {
                TbItemParamItem tbItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                if (tbItem != null) {
                    System.out.println("缓存中有tbItemParamItems，不需要去数据库查询了");
                    return tbItem;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (tbItemParamItems != null && tbItemParamItems.size() > 0) {
            try {
                jedisClient.set(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId, JsonUtils.objectToJson(tbItemParamItems.get(0)));
                jedisClient.expire(REDIS_ITEM_KEY + ":" + ITEM_PARAM_KEY + ":" + itemId, ITEM_EXPIRE_SECOND);
                System.out.println("缓存中没有tbItemParamItems，加入缓存");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tbItemParamItems.get(0);
        }
        return null;
    }
}
