package com.shenchao.taotao.service.impl;

import com.shenchao.taotao.service.PictureService;
import com.shenchao.taotao.utils.FastDFSClient;
import com.taotao.common.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shenchao on 2016/12/11.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult uploadPic(MultipartFile picture) {
        PictureResult pictureResult = new PictureResult();
        if (picture.isEmpty()) {
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
            return pictureResult;
        }
        FastDFSClient fastDFSClient = null;
        try {
            fastDFSClient = new FastDFSClient("classpath:properties/client.conf");
            int i = picture.getOriginalFilename().lastIndexOf(".");
            String result = fastDFSClient.uploadFile(picture.getBytes(), picture.getOriginalFilename().substring(i + 1));
            pictureResult.setError(0);
            pictureResult.setUrl(IMAGE_SERVER_BASE_URL+result);
            return pictureResult;
        } catch (Exception e) {
            e.printStackTrace();
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
        }
        return pictureResult;
    }
}
