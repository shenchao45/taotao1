package com.taotao1.sso.service.impl;

import com.shenchao.taotao.mapper.TbUserMapper;
import com.shenchao.taotao.pojo.TbUser;
import com.shenchao.taotao.pojo.TbUserExample;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Author:shenchao
 * Date:2017/1/5 20:33
 * Description:
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper userMapper;

    /**
     * 根据数据类型检查数据
     */
    @Override
    public TaotaoResult checkData(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (1 == type) {//username
            criteria.andUsernameEqualTo(param);
        } else if (2 == type) {//phone
            criteria.andPhoneEqualTo(param);
        } else if (3 == type) {//email
            criteria.andEmailEqualTo(param);
        }
        List<TbUser> tbUsers = userMapper.selectByExample(example);
        if (tbUsers != null && tbUsers.size() > 0) {
            return TaotaoResult.ok(false);
        }
        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或者密码不存在");
        }
        TaotaoResult result = checkData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return TaotaoResult.build(400, "用户名重复");
        }
        user.setUpdated(new Date());
        user.setCreated(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return TaotaoResult.ok();
    }
}
