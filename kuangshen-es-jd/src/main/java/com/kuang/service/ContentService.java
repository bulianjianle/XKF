package com.kuang.service;

import com.alibaba.fastjson.JSON;
import com.kuang.pojo.Content;
import com.kuang.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ContentService
 *
 * @author danggui
 * @date 2020/9/6 18:36
 */
@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 1解析数据放入es索引中
    public Boolean pareContent(String keywords) throws Exception{
        List<Content> contentList= HtmlParseUtil.parseJD(keywords);
        // 把查询到的数据放入es中
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i= 0 ;i<contentList.size();i++){
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(contentList.get(i)), XContentType.JSON)

            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }

    // 2获取这些数据实现搜索功能
    public List<Map<String,Object>> searchPage(String keyword,Integer pageNo,Integer pageSize) throws Exception {
        if (pageNo<=1){
            pageNo=1;
        }
        // 条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页,可以链式编程
        searchSourceBuilder.from(pageNo).size(pageSize);

        // 精准匹配
        TermQueryBuilder termQueryBuilder= QueryBuilders.termQuery("title",keyword);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // 解析结果
        List<Map<String,Object>> list=new ArrayList<>();
        for (SearchHit documentFields:searchResponse.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;

    }

}
