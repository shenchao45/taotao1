package com.taotao.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * Created by shenchao on 2016/12/11.
 */
public class FastdfsTest {

    public static void main(String[] args) throws IOException, MyException {
        // 1、把FastDFS提供的jar包添加到工程中
        // 2、初始化全局配置。加载一个配置文件。
        ClientGlobal.init("C:\\Users\\shenchao\\IdeaProjects\\taotao1\\taotao1-web\\src\\main\\resources\\properties\\client.conf");
        // 3、创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 4、创建一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 5、声明一个StorageServer对象，null。
        StorageServer storageServer = null;
        // 6、获得StorageClient对象。
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 7、直接调用StorageClient对象方法上传文件即可。
        String[] strings = storageClient.upload_file("C:\\Users\\shenchao\\Desktop\\风景图片\\4ec2d5628535e5dd102a013a76c6a7efcf1b62d5.jpg", "jpg", null);
        StringBuffer buffer = new StringBuffer("http://192.168.187.128");
        for (String string : strings) {
            buffer.append("/"+string);
        }
        System.out.println(buffer.toString());

    }
}
