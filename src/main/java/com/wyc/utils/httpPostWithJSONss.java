package com.wyc.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class httpPostWithJSONss {
	public static String httpPostWithJSON(String url) throws Exception {
		 
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        
////        json��ʽ
//        JSONObject jsonParam = new JSONObject();  
//        jsonParam.put("name", "admin");
//        jsonParam.put("pass", "123456");
//        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//���������������    
//        entity.setContentEncoding("UTF-8");    
//        entity.setContentType("application/json");    
//        httpPost.setEntity(entity);
//        System.out.println();
        
    
        //����ʽ
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(); 
        pairList.add(new BasicNameValuePair("username", "admin"));
        pairList.add(new BasicNameValuePair("password", "123456"));
        pairList.add(new BasicNameValuePair("remember", "yes"));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        
        
        HttpResponse resp = client.execute(httpPost);
        if(resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he,"UTF-8");
        }
        return respContent;
    }
 
    
    public static void main(String[] args) throws Exception {
        String result = httpPostWithJSON("http://192.168.1.39:8080");
        System.out.println(result);
    }

}
