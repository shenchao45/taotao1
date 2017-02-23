package com.taotao1.sso.controller;

import com.shenchao.common.utils.ExceptionUtil;
import com.shenchao.taotao.pojo.TbUser;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao1.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:shenchao
 * Date:2017/1/5 20:25
 * Description:校验数据是否可用
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RegisterService registerService;


    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object check(@PathVariable("param") String param,@PathVariable("type") int type,String callback) {
        try {
            TaotaoResult result = registerService.checkData(param, type);
            if (StringUtils.isNotBlank(callback)) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user) {
        try {
            TaotaoResult result = registerService.register(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

}
