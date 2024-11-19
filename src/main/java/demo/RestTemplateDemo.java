package demo;

import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

public class RestTemplateDemo {

  public static void main(String[] args) {
    HttpHost proxy = new HttpHost("localhost", 8888);
    CloseableHttpClient client = HttpClients.custom()
        .setProxy(proxy)
        //.setDefaultHeaders(Collections.singletonList(new BasicHeader("Connection", "close")))
        .build();
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(client);

    RestTemplate myResttemplate = new RestTemplateBuilder().requestFactory(() -> httpComponentsClientHttpRequestFactory).build();
    System.out.println("1. call");
    callPost(myResttemplate);
    System.out.println("2. call");
    callPost(myResttemplate);
  }

  static void callPost(RestTemplate myResttemplate) {
    String url = "https://httpbin.org/post";
    try {
      myResttemplate.postForObject(url, new HashMap<>(), String.class);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
