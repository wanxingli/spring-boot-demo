package com.sb.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.platform.commons.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static Map<String, Object> sendHttpGet(Map<String, Object> map) {
        String result = null;
        Map<String, Object> responseMap = new HashMap<>();
        try {
            HttpClientBuilder httpClientBuilder = null;
            if (map.get("userName") != null && map.get("password") != null) {
                CredentialsProvider provider = new BasicCredentialsProvider();
                AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(map.get("userName").toString().trim(),
                        map.get("password").toString().trim());
                provider.setCredentials(scope, credentials);
                httpClientBuilder = HttpClientBuilder.create();
                httpClientBuilder.setDefaultCredentialsProvider(provider);
            } else {
                httpClientBuilder = HttpClientBuilder.create();
            }
            CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
            HttpGet httpGet = new HttpGet(map.get("url").toString());
            String token = map.get("token") != null ? map.get("token").toString() : null;
            if (map.get("userName") != null && map.get("password") != null) {
                httpGet.setHeader("Authorization", "Basic " + new BASE64Encoder().encode((map.get("userName")
                        .toString().trim() + ":" + map.get("password").toString().trim()).getBytes(StandardCharsets.UTF_8)));
            } else if (StringUtils.isNotBlank(token)) {
                httpGet.setHeader("Authorization", map.get("token").toString());
            }
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.setHeader("ServiceApi", "true");
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int status = statusLine.getStatusCode();
            log.info("status: {}", result);
            responseMap.put("response", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMap;
    }

    public static String sendHttpPost (String requestBody, Map<String, Object> map) {
        String result = null;
        try {
            log.info("url: {}", map.get("url").toString());
            log.info("requestBody: {}", requestBody);
            HttpPost httpPost = new HttpPost(map.get("url").toString());
            httpPost.setHeader("Authorization", map.get("token") != null ? map.get("token").toString() : null);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.setHeader("ServiceApi", "true");
            StringEntity stringEntry = new StringEntity(requestBody, StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntry);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            int status = statusLine.getStatusCode();
            log.info("status: {}", status);
            result = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            log.info("result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String sendHttpPut (String requestBody, Map<String, Object> map) {
        String result = null;
        try {
            log.info("url: {}", map.get("url").toString());
            log.info("requestBody: {}", requestBody);
            HttpPut httpPut = new HttpPut(map.get("url").toString());
            httpPut.setHeader("Authorization", map.get("token") != null ? map.get("token").toString() : null);
            httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpPut.setHeader("ServiceApi", "true");
            StringEntity stringEntry = new StringEntity(requestBody, StandardCharsets.UTF_8);
            httpPut.setEntity(stringEntry);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse httpResponse = httpClient.execute(httpPut);
            StatusLine statusLine = httpResponse.getStatusLine();
            int status = statusLine.getStatusCode();
            log.info("status: {}", status);
            result = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            log.info("result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
