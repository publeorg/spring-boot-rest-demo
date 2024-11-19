package demo;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

public class RestTemplateDemo {

  public static void main(String[] args) {
    HttpHost proxy = new HttpHost("localhost", 8888);
    RequestConfig config = RequestConfig.custom()
        .setProxy(proxy)
        .build();
    HttpClient client = HttpClientBuilder.create()
        .setDefaultRequestConfig(config)
        //.setDefaultHeaders(Collections.singletonList(new BasicHeader("Connection", "close")))
        .build();
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);

    RestTemplate myResttemplate = new RestTemplateBuilder().requestFactory(() -> httpComponentsClientHttpRequestFactory).build();
    String url = "https://httpbin.org/post";
    try {
      myResttemplate.postForObject(
          url,
          new HashMap<>(),
          String.class);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      //Thread.sleep(2000);
      myResttemplate.postForObject(
          url,
          new HashMap<>(),
          String.class);

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}

