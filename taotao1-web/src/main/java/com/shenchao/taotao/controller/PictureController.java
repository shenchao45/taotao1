package com.shenchao.taotao.controller;

import com.shenchao.common.utils.JsonUtils;
import com.shenchao.taotao.service.PictureService;
import com.taotao.common.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shenchao on 2016/12/11.
 */
@Controller
@Scope("prototype")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {
        PictureResult pictureResult = pictureService.uploadPic(uploadFile);
        return JsonUtils.objectToJson(pictureResult);
    }
}
