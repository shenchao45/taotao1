package com.shenchao.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shenchao on 2016/12/11.
 */
public interface PictureService {
    PictureResult uploadPic(MultipartFile picture);
}
