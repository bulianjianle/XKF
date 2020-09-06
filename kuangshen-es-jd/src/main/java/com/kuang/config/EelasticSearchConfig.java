package com.kuang.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EelasticSearchConfig
 *
 * @author danggui
 * @date 2020/8/23 20:22
 */

/**
 * 1.找对象
 * 2.放到spring中待用
 * 3.如果是spring
 */

@Configuration
public class EelasticSearchConfig {

    // spring <beans id="RestHighLevelClient">
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client=new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1",9200,"http")));
        return client;
    }

}
