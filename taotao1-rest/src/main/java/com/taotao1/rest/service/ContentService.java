package com.taotao1.rest.service;

import com.shenchao.taotao.pojo.TbContent;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * Created by shenchao on 2016/12/24.
 */
public interface ContentService {
    List<TbContent> getContentList(Long cid);

    TaotaoResult syncContent(Long cid);
}
