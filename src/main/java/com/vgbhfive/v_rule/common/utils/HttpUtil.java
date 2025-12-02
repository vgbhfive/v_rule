package com.vgbhfive.v_rule.common.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2025/12/2 10:50
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    @Autowired
    private Gson gson;

    public String post(String url, String data, boolean isPrint) {
        HttpRequest request = HttpRequest.
                post(url).
                header(Header.CONTENT_TYPE, "application/json; charset=utf-8").
                body(data);
        String resp = request.execute().body();
        if (isPrint) {
            logger.info("http post url: {}, data: {}", url, data);
        } else {
            logger.info("http post url: {}", url);
        }
        return resp;
    }

    public String post(String url, String data, boolean isPrint, int timeout) {
        HttpRequest request = HttpRequest.
                post(url).
                header(Header.CONTENT_TYPE, "application/json; charset=utf-8").
                body(data).
                timeout(timeout);
        String resp = request.execute().body();
        if (isPrint) {
            logger.info("http post url: {}, timeout: {}, data: {}", url, timeout, data);
        } else {
            logger.info("http post url: {}, timeout: {}", url, timeout);
        }
        return resp;
    }

    public String post(String url, String data, Map<String, String> headers) {
        String resp = HttpRequest.
                post(url).
                headerMap(headers, true).
                body(data).
                execute().body();
        logger.info("http post url: {}, header: {}, data: {}", url, headers, data);
        return resp;
    }

    public String get(String url) {
        String resp = HttpRequest.get(url).timeout(5000).execute().body();
        logger.info("http get url: {}", url);
        return resp;
    }

    public String get(String url, int timeout) {
        String resp = HttpRequest.get(url).timeout(timeout).execute().body();
        logger.info("http get url: {}, timeout: {}", url, timeout);
        return resp;
    }

}
