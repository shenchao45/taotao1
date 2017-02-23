package com.taotao1.rest.service;

import com.shenchao.taotao.pojo.TbItem;
import com.shenchao.taotao.pojo.TbItemDesc;
import com.shenchao.taotao.pojo.TbItemParamItem;

/**
 * Created by shenchao on 2017/1/1.
 */
public interface ItemService {
    TbItem getItemById(Long id);

    TbItemDesc getItemDescById(Long itemId);

    TbItemParamItem getItemParamById(Long itemId);
}
