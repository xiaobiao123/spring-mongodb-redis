package elk;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 使用java API操作elasticSearch
 *
 * @author 231
 */
public class JavaESTest {

    private TransportClient client;
    private IndexRequest source;

    private String index = "twitter";
    private String type = "tweet2";

    /**
     * 获取连接, 第一种方式
     *
     * @throws Exception
     */
    @Before
    public void before() throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.30.22.11"), 9300));

    }

    /**
     * 查看集群信息
     */
    @Test
    public void testInfo() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress());
        }
    }

    /**
     * 组织json串, 方式1,直接拼接
     */
    public String createJson1() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        return json;
    }

    /**
     * 使用map创建json
     */
    public Map<String, Object> createJson2() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("first name", "111");


        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }

    /**
     * 使用fastjson创建
     */
    public JSONObject createJson3() {
        JSONObject json = new JSONObject();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out elasticsearch");
        return json;
    }

    /**
     * 使用es的帮助类
     */
    public XContentBuilder createJson4(int i) throws Exception {

        String gender = "male";
        if (i % 2 == 0) {
            gender = "man";
        }

        // 创建json对象, 其中一个创建json的方式
        XContentBuilder source = jsonBuilder()
                .startObject()
                .field("user", "kimchy is a good boy "+i)
                .field("age", i)
                .field("gender", gender)
                .field("postDate", new Date())
                .field("message", "trying to out ElasticSearch" + i)
                .endObject();
        return source;
    }


    /**
     * 使用fastjson创建
     */
    public JSONObject createJsonClass() {


        JSONObject json2 = new JSONObject();

        JSONObject json3 = new JSONObject();


        json2.put("type", "string");
        json2.put("index", "not_analyzed");
        json2.put("field", "test1231");

        JSONObject json = new JSONObject();
        json.put("grade", 1);
        json.put("class", json2);
        json.put("name", "xiao 5");
        return json;
    }

    /**
     * 存入索引中
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        for (int i = 200; i < 400; i++) {
        XContentBuilder source = createJson4(i);
        // 存json入索引中
        IndexResponse response = client.prepareIndex("index_test", type, i + "").setSource(source).get();
        // 结果获取
        //IndexResponse response =client.prepareIndex("ypt_da","elastatic_serarch").setSource(source).get();
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        RestStatus status = response.status();
        System.out.println(index + " : " + type + ": " + id + ": " + version + ": " + JSONObject.toJSONString
                (response.toString()));
        }
    }


    /**
     * get API 获取指定文档信息
     */
    @Test
    public void testGet() {
        //        GetResponse response = client.prepareGet("twitter", "tweet", "1")
        //删除api允许执行前设置线程模式（operationThreaded选项），operationThreaded这个选项是使这个操作在另外一个线程中执行，
        // 或在一个正在请求的线程（假设这个api仍是异步的）中执行。默认的话operationThreaded会设置成true，这意味着这个操作将在一
        // 个不同的线程中执行。下面是设置成false的方法：
        GetResponse response = client.prepareGet(index, type, "10")
                .setOperationThreaded(false)    // 线程安全
                .get();

        GetResponse getResponse = client.prepareGet(index, type, "2").get();

        //QueryBuilder builder = QueryBuilders.typeQuery("tweet");//查询整个type
        System.out.println("====================================================");
        System.out.println(response.getSourceAsMap());
        System.out.println("====================================================");
        System.out.println(getResponse.getSourceAsMap());
    }

    /**
     * 测试 delete api
     */
    @Test
    public void testDelete() {
        DeleteResponse response = client.prepareDelete(index, type, "1")
                .get();

        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }

    /**
     * 查询并删除
     */
    @Test
    public void testDeleteByQueryAction() {
        //BulkByScrollResponse scrollResponse =
        //        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
        //                .filter(QueryBuilders.matchQuery("my_field", "test"))
        //                .source("twitter")      //index
        //                .get();
        //long deleted = scrollResponse.getDeleted();


        BulkByScrollResponse scrollResponse = DeleteByQueryAction.INSTANCE.newRequestBuilder(client).
                filter(QueryBuilders.matchQuery("age", "110")).source("twitter").get();

        System.out.println("deleted numbier is " + scrollResponse.getDeleted());

    }

    /**
     * 测试更新 update API
     * 使用 updateRequest 对象
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        //UpdateRequest updateRequest = new UpdateRequest();
        //updateRequest.index("twitter");
        //updateRequest.type("tweet");
        //updateRequest.id("1");
        //updateRequest.doc(jsonBuilder()
        //        .startObject()
        //        // 对没有的字段添加, 对已有的字段替换
        //            .field("gender", "male")
        //            .field("message", "hello")
        //        .endObject());
        //UpdateResponse response = client.update(updateRequest).get();

        //UpdateResponse response = client.prepareUpdate("twitter", "tweet", "6").setDoc(createJson4(7)).get();

        UpdateResponse response = client.prepareUpdate("twitter", "tweet2", "5").setDoc(createJson4(6)).get();

        // 打印
        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        System.out.println(index + " : " + type + ": " + id + ": " + version);
    }

    /**
     * 测试update api, 使用client
     *
     * @throws Exception
     */
    @Test
    public void testUpdate2() throws Exception {
        // 使用Script对象进行更新
        //        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
        //                .setScript(new Script("hits._source.gender = \"male\""))
        //                .get();

        // 使用XContFactory.jsonBuilder() 进行更新
        //        UpdateResponse response = client.prepareUpdate("twitter", "tweet", "1")
        //                .setDoc(XContentFactory.jsonBuilder()
        //                        .startObject()
        //                            .field("gender", "malelelele")
        //                        .endObject()).get();

        // 使用updateRequest对象及script
        //        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
        //                .script(new Script("ctx._source.gender=\"male\""));
        //        UpdateResponse response = client.update(updateRequest).get();

        // 使用updateRequest对象及documents进行更新
        UpdateResponse response = client.update(new UpdateRequest("twitter", "tweet2", "1")
                .doc(jsonBuilder()
                        .startObject()
                        .field("gender", "man")
                        .endObject()
                )).get();
        System.out.println(response.getIndex());
    }

    /**
     * 测试update
     * 使用updateRequest
     *
     * @throws Exception
     * @throws InterruptedException
     */
    @Test
    public void testUpdate3() throws InterruptedException, Exception {
        UpdateRequest updateRequest = new UpdateRequest("twitter", "tweet", "1")
                .script(new Script("ctx._source.gender=\"male\""));
        UpdateResponse response = client.update(updateRequest).get();
        System.out.println(response);
    }

    /**
     * 测试upsert方法
     *
     * @throws Exception
     */
    @Test
    public void testUpsert() throws Exception {
        // 设置查询条件, 查找不到则添加生效
        IndexRequest indexRequest = new IndexRequest("twitter", "tweet2", "201")
                .source(jsonBuilder()
                        .startObject()
                        .field("name", "214")
                        .field("gender", "man")
                        .endObject());
        // 设置更新, 查找到更新下面的设置
        UpdateRequest upsert = new UpdateRequest("twitter", "tweet2", "202")
                .doc(jsonBuilder()
                        .startObject()
                        .field("user", "wenbronk")
                        .endObject())
                .upsert(indexRequest);
        UpdateResponse updateResponse = client.update(upsert).get();

    }

    /**
     * 测试multi get api
     * 从不同的index, type, 和id中获取
     */
    @Test
    public void testMultiGet() {
        MultiGetResponse multiGetResponse = client.prepareMultiGet()
                .add("twitter", "tweet2", "1")
                .add("twitter", "tweet2", "2", "3", "4")
                .add("anothoer", "type", "foo")
                .get();

        for (MultiGetItemResponse itemResponse : multiGetResponse) {
            GetResponse response = itemResponse.getResponse();
            if (response != null) {
                String sourceAsString = response.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
    }

    /**
     * bulk 批量执行
     * 一次查询可以update 或 delete多个document
     */
    @Test
    public void testBulk() throws Exception {

        BulkResponse response1 = client.prepareBulk().add(client.prepareDelete(index, type, "111")).get();

        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex(index, type, "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()));
        bulkRequest.add(client.prepareIndex(index, type, "2")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "another post")
                        .endObject()));


        BulkResponse response = bulkRequest.get();
        if (response.hasFailures()) {
            // process failures by iterating through each bulk response item
        }
        System.out.println(response.hasFailures());
    }

    /**
     * 使用bulk processor
     * <p>
     * 1）beforeBulk会在批量提交之前执行，可以从BulkRequest中获取请求信息request.requests()或者请求数量request.numberOfActions()。
     * 2） 第一个afterBulk会在批量成功后执行，可以跟beforeBulk配合计算批量所需时间。
     * 3）第二个afterBulk会在批量失败后执行。
     * 4）在例子中，当请求超过10000个（default=1000）或者总大小超过1GB（default=5MB）时，触发批量提交动作。
     */
    @Test
    public void testBulkProcessor() throws Exception {
        // 创建BulkPorcessor对象
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, new Listener() {
            public void beforeBulk(long paramLong, BulkRequest paramBulkRequest) {
                // TODO Auto-generated method stub
            }

            // 执行出错时执行
            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, Throwable paramThrowable) {
                // TODO Auto-generated method stub
            }

            public void afterBulk(long paramLong, BulkRequest paramBulkRequest, BulkResponse paramBulkResponse) {
                // TODO Auto-generated method stub
            }
        })
                // 1w次请求执行一次bulk
                .setBulkActions(10000)
                // 1gb的数据刷新一次bulk
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                // 固定5s必须刷新一次
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                // 并发请求数量, 0不并发, 1并发允许执行
                .setConcurrentRequests(1)
                // 设置退避, 100ms后执行, 最大请求3次
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        // 添加单次请求
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1"));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));

        // 关闭
        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
        // 或者
        bulkProcessor.close();
    }
}