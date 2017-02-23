package com.shenchao.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by shenchao on 2017/1/2.
 */
public interface StaticPageService {
    TaotaoResult getItemHtml(Long itemId) throws IOException, TemplateException;
}
