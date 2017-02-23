package com.shenchao.taotao.service.impl;

import com.shenchao.taotao.mapper.TbContentMapper;
import com.shenchao.taotao.pojo.TbContent;
import com.shenchao.taotao.service.ContentService;
import com.taotao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shenchao on 2016/12/24.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public TaotaoResult insertContent(TbContent content) {
        content.setUpdated(new Date());
        content.setCreated(new Date());
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }
}
