package com.zzwei.server.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtils {

    public static void main(String[] args) throws IOException {
        System.out.println("测试httpClient配置");
        //1：创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2：通过httpGet发起get方式，传入网址为参数，并创建httpGet对象。
        HttpGet httpGet = new HttpGet("https://www.itcast.cn");
        //3：使用httpClient对象发起请求，
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response);
        //4:解析响应，从页获取数据
        //判断http状态码
        if (response.getStatusLine().getStatusCode() == 200) {
            //Entity是发送和接收信息载体、 httpEntity可能通过request或 reponse
            HttpEntity httpEntity = response.getEntity();
            System.out.println("输出httpEntity对象");
            System.out.println(httpEntity);
            //不加UTF-8参数，会中文乱码
            String content = EntityUtils.toString(httpEntity, "UTF-8");
            System.out.println("获取内容" + content);

        }
    }
}
