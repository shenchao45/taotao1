package com.taotao1.sso.service;

import com.shenchao.taotao.pojo.TbUser;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Author:shenchao
 * Date:2017/1/5 20:32
 * Description:
 */
public interface RegisterService {
    TaotaoResult checkData(String param, int type);

    TaotaoResult register(TbUser user);
}
