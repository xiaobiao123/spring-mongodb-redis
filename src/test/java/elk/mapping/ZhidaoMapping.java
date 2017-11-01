package elk.mapping;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.threadpool.ThreadPool.Names.INDEX;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ZhidaoMapping {
    public static XContentBuilder getMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder().startObject()
                    //开启倒计时功能
                    .startObject("properties").
                            startObject("title").field("type", "string").endObject().
                            startObject("question").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("answer").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("category").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("author").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("date").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("answer_author").field("type", "string").field("index", "not_analyzed")
                    .endObject().
                            startObject("answer_date").field("type", "string").field("index", "not_analyzed")
                    .endObject().
                            startObject("description").field("type", "string").field("index", "not_analyzed")
                    .endObject().
                            startObject("keywords").field("type", "string").field("index", "not_analyzed").endObject().
                            startObject("read_count").field("type", "integer").field("index", "not_analyzed")
                    .endObject()
                    //关联数据
                    .startObject("list").field("type", "object").endObject().endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapping;
    }


    public static XContentBuilder getMapping2() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    //开启倒计时功能
                    .startObject("properties").field("type", "string").endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapping;
    }

    //    XContentBuilder builder =
    //            XContentFactory.jsonBuilder()
    //                    .startObject()
    //                    .field("properties")
    //                    .startObject()
    //                    .field("name")
    //                    .startObject()
    //                    .field("index", "not_analyzed")
    //                    .field("type", "string")
    //                    .endObject()
    //                    .field("age")
    //                    .startObject()
    //                    .field("index", "not_analyzed")
    //                    .field("type", "integer")
    //                    .endObject()
    //                    .endObject()
    //                    .endObject();

    /**
     * 给索引增加mapping。
     *
     * @param index 索引名
     * @param type  mapping所对应的type
     */
    public static void addMapping(String index, String type, TransportClient client) {
        try {
            // 使用XContentBuilder创建Mapping
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("properties")
                    .startObject()
                    .field("user")
                    .startObject()
                    .field("index", "not_analyzed")
                    .field("type", "string")
                    .field("fields")
                    .startObject()
                    .field("keyword")
                    .startObject()
                    .field("type", "keyword")
                    .endObject()
                    .endObject()
                    .endObject()
                    .field("gender")
                    .startObject()
                    .field("index", "not_analyzed")
                    .field("type", "string")
                    .field("fields")
                    .startObject()
                    .field("keyword")
                    .startObject()
                    .field("type", "keyword")
                    .endObject()
                    .endObject()
                    .endObject()
                    .field("age")
                    .startObject()
                    .field("index", "not_analyzed")
                    .field("type", "integer")
                    .endObject()
                    .endObject()
                    .endObject();
            System.out.println(builder.string());
            PutMappingRequest mappingRequest = Requests.putMappingRequest(index).source(builder).type(type);
            client.admin().indices().putMapping(mappingRequest).actionGet();
        } catch (ElasticsearchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {

        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new
                InetSocketTransportAddress(InetAddress.getByName("172.30.22.11"), 9300));

        ////判断索引是否存在
        //IndicesExistsRequest request1 = new IndicesExistsRequest("mapping_test_index");
        //ActionFuture<IndicesExistsResponse> exists = client.admin().indices().exists(request1);
        //exists.actionGet().isExists();
        //
        ////构建一个Index（索引）
        CreateIndexRequest request = new CreateIndexRequest("twitter");
        ActionFuture<CreateIndexResponse> createIndexResponseActionFuture = client.admin().indices().create(request);
        CreateIndexResponse createIndexResponse = createIndexResponseActionFuture.actionGet();
        //
        //
        ////        创建可用户聚合查询的索引
        addMapping("twitter", "tweet2", client);
        //创建PutMappingRequest类型的索引
        //PutMappingRequest mapping = Requests.putMappingRequest("mapping_test_index").type("mapping").source
        // (ZhidaoMapping.getMapping2());
        //client.admin().indices().putMapping(mapping).actionGet();


        ////创建索引并指定主分片及副本分片数
        //Settings settings2 = Settings.builder()
        //        //5个主分片
        //        .put("number_of_shards", 3)
        //        //测试环境，减少副本提高速度
        //        .put("number_of_replicas", 2).H1build();
        //client.admin().indices()
        //        //这个索引库的名称还必须不包含大写字母
        //        .prepareCreate("testindex").setSettings(settings2)
        //        //这里直接添加type的mapping
        //        .addMapping("testType", getMapping()).execute();
    }


}