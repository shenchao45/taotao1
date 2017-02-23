package com.taotao1.sso.service.impl;

import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.mapper.TbUserMapper;
import com.shenchao.taotao.pojo.TbUser;
import com.shenchao.taotao.pojo.TbUserExample;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.sso.component.JedisClient;
import com.taotao1.sso.service.LoginService;
import com.taotao1.sso.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Author:shenchao
 * Date:2017/1/5 21:36
 * Description:
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private int SESSION_EXPIRE;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
        List<TbUser> list = userMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbUser tbUser = list.get(0);
            UUID uuid = UUID.randomUUID();
            jedisClient.set(REDIS_SESSION_KEY + ":" + uuid.toString(), JsonUtils.objectToJson(tbUser));
            jedisClient.expire(REDIS_SESSION_KEY + ":" + uuid.toString(), SESSION_EXPIRE);
            CookieUtils.setCookie(request,response,"TT_TOKEN",uuid.toString());
            return TaotaoResult.ok(uuid.toString());
        }else{
            return TaotaoResult.build(400, "用户名或者密码错误");
        }
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String s = jedisClient.get(REDIS_SESSION_KEY+":"+token);
        if (StringUtils.isBlank(s)) {
            return TaotaoResult.build(400, "用户的session已经过期");
        }
        jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(s, TbUser.class);
        return TaotaoResult.ok(user);
    }
}
