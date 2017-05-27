package elk;

import java.net.InetAddress;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

/**
 * 使用java API操作elasticSearch
 * search API
 * @author 231
 *
 */
public class JavaESTest2 {

    private TransportClient client;

    /**
     * 获取client对象
     */
    @Before
    public void testBefore() throws Exception {
//        Builder builder = Settings.settingsBuilder();
//        builder.put("cluster.name", "wenbronk_escluster");
////                .put("client.transport.ignore_cluster_name", true);
//        Settings settings = builder.build();
//
//        org.elasticsearch.client.transport.TransportClient.Builder transportBuild = TransportClient.builder();
//        TransportClient client1 = transportBuild.settings(settings).build();
//        client = client1.addTransportAddress((new InetSocketTransportAddress(new InetSocketAddress("192.168.50.37", 9300))));
//        System.out.println("success connect to escluster");

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.30.22.239"), 9300));


    }

    /**
     * 测试查询
     */
    @Test
    public void testSearch() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("twitter");


         SearchResponse response = searchRequestBuilder.setTypes("user", "type2")
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                            .setQuery(QueryBuilders.termQuery("user", "kimchy"))
                            .setPostFilter(QueryBuilders.rangeQuery("age").from(0).to(10))// from  to  从0-10（包括0，和10）
                            .setFrom(0).setSize(2)//.setExplain(true)
                            // setFrom  setSize setfrom,setsize是指一页显示的多少，从第几个开始，显示size个数据
                            .execute().actionGet();


//        SearchResponse response = client.prepareSearch()
//                .execute().actionGet();
//        SearchHits hits = response.getHits();
//        for (SearchHit searchHit : hits) {
//            for(Iterator<SearchHitField> iterator = searchHit.iterator(); iterator.hasNext(); ) {
//                SearchHitField next = iterator.next();
//                System.out.println(next.getValues());
//            }
//        }
        System.out.println(response);
    }

    /**
     * 测试scroll api
     * 对大量数据的处理更有效
     */
    @Test
    public void testScrolls() {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("user", "tweet");


        SearchResponse response = client.prepareSearch("twitter")
//        .addSort(SortParseElement.DOC_FIELD_NAME, SortOrder.ASC)  //TODO
        .setScroll(new TimeValue(60000))
        .setQuery(queryBuilder)
        .setSize(100).execute().actionGet();

        while(true) {
            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println("i am coming");
            }
            SearchResponse response2 = client.prepareSearchScroll(response.getScrollId())
                .setScroll(new TimeValue(60000)).execute().actionGet();
            if (response2.getHits().getHits().length == 0) {
                System.out.println("oh no=====");
                break;
            }
        }

    }

    /**
     * 测试multiSearch
     */
    @Test
    public void testMultiSearch() {
        QueryBuilder qb1 = QueryBuilders.queryStringQuery("elasticsearch");
        SearchRequestBuilder requestBuilder1 = client.prepareSearch().setQuery(qb1).setSize(1);

        QueryBuilder qb2 = QueryBuilders.matchQuery("user", "kimchy");
        SearchRequestBuilder requestBuilder2 = client.prepareSearch().setQuery(qb2).setSize(1);

        MultiSearchResponse multiResponse = client.prepareMultiSearch().add(requestBuilder1).add(requestBuilder2)
                .execute().actionGet();
        long nbHits = 0;
        for (MultiSearchResponse.Item item : multiResponse.getResponses()) {
            SearchResponse response = item.getResponse();
            nbHits = response.getHits().getTotalHits();
            SearchHit[] hits = response.getHits().getHits();
            System.out.println(nbHits);
        }

    }

    /**
     * 测试聚合查询
     */
    @Test
    public void testAggregation() {
        SearchResponse response = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery()) // 先使用query过滤掉一部分
                .addAggregation(AggregationBuilders.terms("term").field("user"))
                .addAggregation(AggregationBuilders.dateHistogram("agg2").field("birth")
//                    .interval(DateHistogramInterval.YEAR) TODO
                )
                .execute().actionGet();
        Aggregation aggregation2 = response.getAggregations().get("term");
        Aggregation aggregation = response.getAggregations().get("agg2");
//        SearchResponse response2 = client.search(new SearchRequest().searchType(SearchType.QUERY_AND_FETCH)).actionGet();
    }

    /**
     * 测试terminate
     */
    @Test
    public void testTerminateAfter() {
        SearchResponse response = client.prepareSearch("twitter").setTerminateAfter(1000).get();
        if (response.isTerminatedEarly()) {
            System.out.println("ternimate");
        }
    }

    /**
     * 过滤查询: 大于gt, 小于lt, 小于等于lte, 大于等于gte
     */
    @Test
    public void testFilter() {
        SearchResponse response = client.prepareSearch("twitter")
                .setTypes("user")
                .setQuery(QueryBuilders.matchAllQuery()) //查询所有
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setPostFilter(QueryBuilders.rangeQuery("age").from(0).to(19)
                      .includeLower(true).includeUpper(true))
                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
                .get();
    }

    /**
     * 分组查询
     */
    @Test
    public void testGroupBy() {
        SearchResponse searchResponse = client.prepareSearch("twitter").setTypes("user")
                .setQuery(QueryBuilders.matchAllQuery())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .addAggregation(AggregationBuilders.terms("gradeAgg").field("class.field")      // 根据user进行分组
                        // size(0) 也是10
                ).get();
        System.out.println(JSON.toJSONString(searchResponse));

    }



    /**
     * 分组查询
     */
    @Test
    public void testGroupByClass() {
        SearchRequestBuilder srb = client.prepareSearch("my_index");
        srb.setTypes("my_type");
        srb.setSearchType(SearchType.DEFAULT);

//        TermsAggregationBuilder gradeAgg = AggregationBuilders.terms("gradeAgg");
        ValueCountAggregationBuilder field = AggregationBuilders.count("classAgg").field("my_field");
//        gradeAgg.subAggregation(grade);
        srb.addAggregation(field);
        SearchResponse sr = srb.execute().actionGet();
        System.out.println(JSON.toJSONString(sr.getHits()));

    }

    @Test
    public void testGroupByClass2() {
        SearchRequestBuilder srb = client.prepareSearch("mapping_test_index");
        srb.setTypes("user_type");
        srb.setSearchType(SearchType.DEFAULT);

//        TermsAggregationBuilder gradeAgg = AggregationBuilders.terms("gradeAgg");

        TermsAggregationBuilder grade= AggregationBuilders.terms("classAgg").field("my_field");
//        gradeAgg.subAggregation(grade);
        srb.addAggregation(grade);
        SearchResponse sr = srb.execute().actionGet();
        Aggregation aggregation = sr.getAggregations().get("classAgg");
        SearchHits searchHits = sr.getHits();
        for (SearchHit hit : searchHits) {
            hit.getSourceAsString();
        }
        sr.getAggregations().get("classAgg").getMetaData();
        System.out.println(JSON.toJSONString(sr.getAggregations()));

    }


}