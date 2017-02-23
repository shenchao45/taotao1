package com.shenchao.taotao.portal.service;

import com.shenchao.taotao.pojo.TbItem;

/**
 * Created by shenchao on 2017/1/2.
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
