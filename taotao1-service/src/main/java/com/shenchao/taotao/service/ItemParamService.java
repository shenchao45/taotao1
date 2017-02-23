package com.shenchao.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by shenchao on 2016/12/13.
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long cid);

    TaotaoResult insertItemParam(Long cid,String paramData);

    String getItemParamHtml(Long itemId);
}
