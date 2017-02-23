package com.taotao1.sso.service;

import com.taotao.common.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author:shenchao
 * Date:2017/1/5 21:34
 * Description:
 */
public interface LoginService {
    TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    TaotaoResult getUserByToken(String token);
}
