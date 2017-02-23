package com.taotao.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenchao on 2016/12/24.
 */
public class HttpClientTest {
    @Test
    public void testHttpGet() throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://card2.usts.edu.cn/accountDoLoss.action");
        CloseableHttpResponse response = closeableHttpClient.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
        response.close();
        closeableHttpClient.close();
    }

    @Test
    public void testHttpPost() throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://card2.usts.edu.cn/accountDoLoss.action");
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("account", "30760"));
        parameters.add(new BasicNameValuePair("passwd", "084228"));
        StringEntity entity = new UrlEncodedFormEntity(parameters,"UTF-8");
        post.setEntity(entity);
        CloseableHttpResponse re = client.execute(post);
        System.out.println(EntityUtils.toString(re.getEntity()));
        post.clone();
        client.close();
    }
}
