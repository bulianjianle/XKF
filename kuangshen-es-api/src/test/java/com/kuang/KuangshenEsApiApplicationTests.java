package com.kuang;

import com.alibaba.fastjson.JSON;
import com.kuang.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * es7.6.x高级客户端api测试
 *
 */

@SpringBootTest
class KuangshenEsApiApplicationTests {

	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;


	// 测试索引的创建
	@Test
	void testCreateIndex() throws IOException {
		// 创建索引请求
		CreateIndexRequest request = new CreateIndexRequest("kuangshen_index");
		// 客户端执行请求,获得响应
		CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

		System.out.println(createIndexResponse);

	}

	// 测试获取索引
	@Test
	void testExistIndex() throws IOException {
		GetIndexRequest request=new GetIndexRequest("kuangshen_index");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);


	}

	// 删除索引
	@Test
	void deleteIndex() throws IOException{
		DeleteIndexRequest request=new DeleteIndexRequest("kuangshen_index");
		AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());

	}

	// 测试添加文档
	@Test
	void testAddDocument() throws IOException {
		// 创建对象
		User user=new User("张三",3);
		// 创建请求
		IndexRequest request = new IndexRequest("kuang_index");
		// 规则 put /kuang_index/_doc/1
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));
		request.timeout("1s");

		// 将我们的数据放入请求
		IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);
		// 客户端发送请求,获取响应结果
		IndexResponse index = client.index(request, RequestOptions.DEFAULT);
		System.out.println(index.toString());
		System.out.println(index.status()); // 对应命令返回的状态
	}
	// 获取文档
	@Test
	void testIsExists() throws IOException {
		GetRequest getRequest = new GetRequest("kuang_index", "1");
		// 不获取返回的_source 的上下文效率更高
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		getRequest.storedFields("_none_");

		boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

		System.out.println(exists);
	}

	// 获取文档的信息
	@Test
	void testGetDocument()throws IOException {
		GetRequest getRequest = new GetRequest("kuang_index", "1");
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		// 打印文档的内容
		System.out.println(getResponse.getSourceAsString()); // 只打印_source里的内容 ,map类型
		System.out.println(getResponse); //返回的内容和命令是一样的
	}

	// 更新文档的信息
	@Test
	void testUpdateDocument()throws IOException {
		UpdateRequest updateRequest = new UpdateRequest("kuang_index", "1");
		updateRequest.timeout("1s");

		User user = new User("狂神说Java111", 19);
		updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);

		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
		System.out.println(updateResponse.status());

	}

	// 删除文档记录
	@Test
	void testDeleteRequest()throws IOException{
		DeleteRequest request=new DeleteRequest("kuang_index","1");
		request.timeout("1s");
		DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
		System.out.println(delete.status());

	}

	// 特殊的,真实的项目一般都会批量插入数据!
	@Test
	void testBulkRequest() throws IOException {
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.timeout("10s");

		List<User> userList =new ArrayList<>();
		userList.add(new User("kuangshen1",63));
		userList.add(new User("kuangshen2",37));
		userList.add(new User("kuangshen3",35));
		userList.add(new User("kuangshen4",33));
		userList.add(new User("kuangshen5",43));
		userList.add(new User("kuangshen6",32));
		userList.add(new User("kuangshen7",13));
		userList.add(new User("kuangshen8",32));

		for (int i=0;i<userList.size();i++){
			// 批量更新和批量删除修改对应的请求
			bulkRequest.add(new IndexRequest("kuang_index").id(""+(i+1)).source(JSON.toJSONString(userList.get(i)),XContentType.JSON));
		}
		BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
		System.out.println(bulk.hasFailures());

	}

	// 查询
	// SearchRequest 搜索请求
	// SearchSourceBuilder条件构造
	// HighlighterBuilder构建高亮
	// TermQueryBuilder精确查询
	// MatchAllQueryBuilder 全量
	@Test
	void testSearch() throws IOException {
		SearchRequest searchRequest = new SearchRequest("kuang_index");
		// 构建搜索条件
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		// 高亮
		searchSourceBuilder.highlighter();
		// 精确查询termQuery
		// QueryBuilders工具类
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "kuangshen6");
		SearchSourceBuilder query = searchSourceBuilder.query(termQueryBuilder);
		//MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
		searchSourceBuilder.from();
		searchSourceBuilder.size();
		searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		SearchRequest source = searchRequest.source(searchSourceBuilder);
		SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
		// 结果都封装在这
		System.out.println(JSON.toJSONString(search.getHits()));
		System.out.println("----------------------------");
		for (SearchHit documentFields:search.getHits().getHits()){
			System.out.println(documentFields.getSourceAsMap());
		}

	}


	@Test
	void contextLoads() {

	}

}
