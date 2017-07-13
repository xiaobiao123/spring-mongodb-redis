package elk;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.search.Query;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BaseTermQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.global.Global;
import org.elasticsearch.search.aggregations.bucket.histogram.*;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.rescore.RescoreBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * 使用java API操作elasticSearch
 * search API
 *
 * @author 231
 */
public class JavaESTest2 {

    private TransportClient client;

    private String index = "twitter";

    private String type = "tweet2";

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
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.30.21.92"), 9300));
        //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.30.21.92"), 9300));


    }

    /**
     * 测试查询
     */
    @Test
    public void testSearch() {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("twitter");
        SearchResponse response = searchRequestBuilder.setTypes("tweet2")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("gender", "man"))
                .setPostFilter(QueryBuilders.rangeQuery("age").from(110).to(140))// from  to  从0-10（包括0，和10）
                .setFrom(0).setSize(5)//.setExplain(true)
                // setFrom  setSize setfrom,setsize是指一页显示的多少，从第几个开始，显示size个数据
                .execute().actionGet();

        //        SearchResponse response = client.prepareSearch()
        //                .execute().actionGet();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit searchHit : hits) {
            searchHit.getSourceAsMap();
            System.out.println(searchHit.getSourceAsString());
        }
        System.out.println(response);
    }

    /**
     * 测试scroll api
     * 对大量数据的处理更有效
     * <p>
     * 使用Scroll/size，如果结果超过size大小，会返回size条记录，并返回一个scrollId，可以用这个scrollId继续往下查询。
     * <p>
     * 第二种分页查询必须在设定的缓存时间内往下查询，而且一旦进行了查询就必须往下继续查，不能回头重复查询。
     */
    @Test
    public void testScrolls() {
        //term query 分词精确查询
        QueryBuilder queryBuilder = termQuery("user", "kimchy");
        SearchResponse response = client.prepareSearch(index)
                .addSort("age", SortOrder.ASC)  //TODO
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .setSize(10).execute().actionGet();

        while (true) {
            for (SearchHit hit : response.getHits().getHits()) {
                System.out.println("i am coming" + hit.getSourceAsString());
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
            System.out.println(hits[0].getSourceAsMap());
        }

    }

    /**
     * 测试聚合查询
     * <p>
     * AggregationBuilders.terms count grouy by
     */
    @Test
    public void testAggregation() {
        SearchResponse response = client.prepareSearch("twitter")
                .setQuery(QueryBuilders.matchAllQuery()) // 先使用query过滤掉一部分
                .addAggregation(AggregationBuilders.terms("term").field("gender"))
                .addAggregation(AggregationBuilders.dateHistogram("da").field("postDate").format("yyyy-MM-dd")
                        .interval(86400l) // TODO
                )
                .execute().actionGet();
        Terms term = response.getAggregations().get("term");
        List<Terms.Bucket> buckets = term.getBuckets();

        for (Terms.Bucket bucket : buckets) {
            System.out.println("field name is " + bucket.getKey() + "  numbe is " + bucket.getDocCount());
        }
        System.out.println("----------------------分割线---------------------------");
        Histogram histogram = response.getAggregations().get("da");

        List<? extends Histogram.Bucket> buckets1 = histogram.getBuckets();

        for (Histogram.Bucket bucket : buckets1) {
            System.out.println("field name is " + bucket.getKeyAsString() + "  numbe is " + bucket.getDocCount());
        }


    }


    /**
     * 测试聚合查询
     * <p>
     * AggregationBuilders.dateHistogram
     * <p>
     * 通常的 histogram bucket（桶）是可以处理日期的。 但是它不能自动识别日期。 而用 date_histogram ，
     * 你可以指定时间段如 1 个月 ，它能聪明地知道 2 月的天数比 12 月少。 date_histogram 还具有另外一个优势
     * ，即能合理地处理时区，这可以使你用客户端的时区进行图标定制，而不是用服务器端时区。
     * <p>
     * 通常的 histogram 会把日期看做是数字，这意味着你必须以微秒为单位指明时间间隔。另外聚合并不知道日历时间间隔，
     * 使得它对于日期而言几乎没什么用处。
     * <p>
     * DateHistogramInterval.HOUR 安小时统计
     * <p>
     * DateHistogramInterval. 安月统计
     */
    @Test
    public void testDateHistogram() {
        //"extended_bounds" : {
        //    "min" : "2014-01-01",
        //            "max" : "2014-12-31"
        //}
        //设置统计查询的时间范围
        ExtendedBounds extendedBounds = new ExtendedBounds("2017-01-01", "2017-12-12");
        SearchResponse response = client.prepareSearch("twitter")
                .setQuery(QueryBuilders.matchAllQuery()) // 先使用query过滤掉一部分
                .addAggregation(AggregationBuilders.dateHistogram("da").field("postDate").format("yyyy-MM-dd")
                                .dateHistogramInterval(DateHistogramInterval.MONTH).extendedBounds(extendedBounds)
                        //.interval(DateHistogramInterval.QUARTER) // TODO 随意设置时间
                )
                .execute().actionGet();
        Histogram histogram = response.getAggregations().get("da");
        List<? extends Histogram.Bucket> buckets1 = histogram.getBuckets();

        for (Histogram.Bucket bucket : buckets1) {
            System.out.println("field name is " + bucket.getKeyAsString() + "  numbe is " + bucket.getDocCount());
        }


    }

    /**
     * 测试 terminate（终止）
     */
    @Test
    public void testTerminateAfter() {
        SearchResponse response = client.prepareSearch(index).setTerminateAfter(1000).get();
        if (response.isTerminatedEarly()) {
            System.out.println("ternimate");
        }
    }

    /**
     * 过滤查询: 大于gt, 小于lt, 小于等于lte, 大于等于gte
     */
    @Test
    public void testFilter() {
        ////前缀查找
        //QueryBuilders.prefixQuery("","");//
        ////匹配短语查询
        //QueryBuilders.matchPhraseQuery("","");
        //QueryBuilders.rangeQuery("age").gt()
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.matchAllQuery()) //查询所有
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setPostFilter(QueryBuilders.rangeQuery("age").from(10).to(110)
                        //.includeLower(true).includeUpper(true)
                ).setSize(20).addSort("age", SortOrder.DESC)
                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
                .get();


        SearchHit[] hits = response.getHits().getHits();

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }


        //QueryBuilders.matchPhraseQuery()//精确查找
        //QueryBuilders.prefixQuery()
        //QueryBuilders.boolQuery().must(termQuery("content", "test1"));
        //QueryBuilders.fuzzyQuery("","");//模糊查询
    }


    /**
     * 分组查询
     */
    @Test
    public void testSubAggregation() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        TermsAggregationBuilder one = AggregationBuilders.terms("classAgg").field("gender").size(20);
        // TODO 可以设置子 size（pageSize,默认10）
        TermsAggregationBuilder two = AggregationBuilders.terms("age").field("age").size(20);

        one.subAggregation(two);
        srb.addAggregation(one);

        SearchResponse sr = srb.execute().actionGet();

        Terms ss = sr.getAggregations().get("classAgg");

        List<Terms.Bucket> buckets = ss.getBuckets();

        for (Terms.Bucket bucket : buckets) {
            System.out.println("关键字：" + bucket.getKey() + "，数量" + bucket.getDocCount());

            Terms terms_age = bucket.getAggregations().get("age");
            for (Terms.Bucket age_buk : terms_age.getBuckets()) {
                System.out.println("关键字：" + bucket.getKey() + "，数量" + bucket.getDocCount()
                        + "子集关键字：" + age_buk.getKey() + ",数量：" + age_buk.getDocCount());
            }
        }


        // System.out.println(JSON.toJSONString(ss));

    }

    @Test
    public void testGroupByClass2() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        srb.setQuery(QueryBuilders.prefixQuery("last_name", "smith"));//查询条件
        //srb.setPostFilter(QueryBuilders.rangeQuery("age").from(0).to(10));

        TermsAggregationBuilder grade = AggregationBuilders.terms("classAgg").field("age");
        srb.addAggregation(grade);
        SearchResponse sr = srb.execute().actionGet();

        //获取Terms类型的聚合查询结果
        //API https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/_bucket_aggregations.html
        Terms agg = sr.getAggregations().get("classAgg");
        List<Terms.Bucket> buckets = agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            bucket.getDocCount();
            bucket.getKeyAsString();
        }


        SearchHit[] hits = sr.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
        sr.getAggregations().get("classAgg").getMetaData();
        //System.out.println(JSON.toJSONString(sr.getAggregations()));

    }

    /**
     * AggregationBuilders.terms
     */
    @Test
    public void testGroupByMyStoreTrems() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        //srb.setQuery(QueryBuilders.prefixQuery("make", "ford"));
        //AggregationBuilders.terms
        TermsAggregationBuilder field = AggregationBuilders.terms("classAgg").field("color");
        AvgAggregationBuilder price = AggregationBuilders.avg("price").field("price");

        field.subAggregation(price);
        srb.addAggregation(field);
        srb.setSize(0);
        SearchResponse sr = srb.execute().actionGet();
        //SearchHit[] hits = sr.getHits().getHits();
        //for (SearchHit hit : hits) {
        //    System.out.println(hit.getSourceAsMap());
        //}
        Terms agg = sr.getAggregations().get("classAgg");
        List<Terms.Bucket> buckets = agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.println("bucket.getDocCount:" + bucket.getDocCount());
            System.out.println("bucket.getKeyAsString:" + bucket.getKeyAsString());
            Avg agg2 = bucket.getAggregations().get("price");
            agg2.getValue();
            agg2.getName();
        }
    }

    /**
     * AggregationBuilders.count
     */
    @Test
    public void testGroupByMyStoreCount() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        srb.setQuery(QueryBuilders.rangeQuery("age").gt(10));
        ValueCountAggregationBuilder count = AggregationBuilders.count("age_key");
        count.field("age");
        srb.addAggregation(count);

        SearchResponse sr_count = srb.execute().actionGet();
        //SearchHit[] hits_count = sr_count.getHits().getHits();
        //for (SearchHit hit : hits_count) {
        //    System.out.println("hits_count"+hit.getSourceAsMap());
        //}
        //总共有几个
        ValueCount agg = sr_count.getAggregations().get("age_key");
        System.out.println("总共有" + agg.getValue() + "个商品数量的价格是大于10");
    }

    /**
     * AggregationBuilders.avg
     */
    @Test
    public void testGroupByMyStoreAvg() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        srb.setQuery(QueryBuilders.rangeQuery("age").gt(10));
        AvgAggregationBuilder classAgg = AggregationBuilders.avg("age_key");
        classAgg.field("age");
        srb.addAggregation(classAgg);

        SearchResponse sr_count = srb.execute().actionGet();
        //SearchHit[] hits_count = sr_count.getHits().getHits();
        //for (SearchHit hit : hits_count) {
        //    System.out.println("hits_count"+hit.getSourceAsMap());
        //}
        //总共有几个
        Avg agg = sr_count.getAggregations().get("age_key");
        System.out.println("商品的平均价格是" + agg.getValue() + "个商品数量的价格是大于10");
    }

    /**
     * AggregationBuilders.max
     */
    @Test
    public void testGroupByMyStoreMax() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        srb.setQuery(QueryBuilders.rangeQuery("age").gt(10));
        MaxAggregationBuilder classAgg = AggregationBuilders.max("age_key");
        classAgg.field("age");
        srb.addAggregation(classAgg);

        SearchResponse sr_count = srb.execute().actionGet();
        //SearchHit[] hits_count = sr_count.getHits().getHits();
        //for (SearchHit hit : hits_count) {
        //    System.out.println("hits_count"+hit.getSourceAsMap());
        //}
        //总共有几个
        Max agg = sr_count.getAggregations().get("age_key");
        System.out.println("商品最大价格是" + agg.getValue() + "个商品数量的价格是大于10");
    }

    /**
     * AggregationBuilders.min
     */
    @Test
    public void testGroupByMyStoreMin() {
        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        srb.setQuery(QueryBuilders.constantScoreQuery(QueryBuilders.rangeQuery("age").gt(10)));
        MinAggregationBuilder classAgg = AggregationBuilders.min("age_key");
        classAgg.field("age");
        srb.addAggregation(classAgg);

        SearchResponse sr_count = srb.execute().actionGet();

        //总共有几个
        Min agg = sr_count.getAggregations().get("age_key");
        System.out.println("value:" + agg.getValue() + "  name:" + agg.getName());

    }


    /**
     * 布尔查询
     * <p>
     * must
     * 所有的语句都 必须（must） 匹配，与 AND 等价。
     * must_not
     * 所有的语句都 不能（must not） 匹配，与 NOT 等价。
     * should
     * 至少有一个语句要匹配，与 OR 等价。
     */
    @Test
    public void testbbBool() {
        ////前缀查找
        //QueryBuilders.prefixQuery("","");//
        ////匹配短语查询
        //QueryBuilders.matchPhraseQuery("","");
        SearchResponse response = client.prepareSearch("my_store")
                .setTypes("products")
                .setQuery(QueryBuilders.disMaxQuery().add(QueryBuilders.matchAllQuery()))
                //.setQuery(QueryBuilders.boolQuery().mustNot(termQuery("price", 20)))
                //.setQuery(QueryBuilders.boolQuery().should(termQuery("price", 20)).should(termQuery("price", 10)))
                //.setQuery(QueryBuilders.boolQuery().should(QueryBuilders.boolQuery().must(termQuery("productID", "XHDK-A-1293-#fJ3"))).should(termQuery("price", 10))) //查询所有
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
                .get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
        //QueryBuilders.matchPhraseQuery()//精确查找
        //QueryBuilders.prefixQuery()
        //QueryBuilders.boolQuery().must(termQuery("content", "test1"));
        //QueryBuilders.fuzzyQuery("","");//模糊查询
    }


    /**
     * 判断字段是否存在（不关系字段内容是否为空/null）
     */
    @Test
    public void testExistsQuery() {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.constantScoreQuery(QueryBuilders.existsQuery("age")))
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
                .get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }

    /**
     * QueryBuilders.constantScoreQuery
     */
    @Test
    public void testTermsQuery() {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.constantScoreQuery(QueryBuilders.termsQuery("", "", "")))
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
                .get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    /**
     * dis_max 查询
     * 分离 最大化查询（Disjunction Max Query） 。分离（Disjunction）的意思是 或（or）
     * dis_max 查询只会简单地使用 单个 最佳匹配语句的评分 _score 作为整体评分
     * <p>
     * tie_breaker 参数
     * 获得最佳匹配语句的评分 _score 。
     * 将其他匹配语句的评分结果与 tie_breaker 相乘。
     * 对以上评分求和并规范化。
     */
    @Test
    public void testbDisMaxQuery() {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setQuery(QueryBuilders.disMaxQuery()
                        .add(QueryBuilders.matchQuery("title", "Brown fox"))
                        .add(QueryBuilders.matchQuery("body", "Brown fox")))
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    /**
     * multi_match 查询为能在多个字段上反复执行相同查询提供了一种便捷方式。
     * best_fields 、 most_fields 和 cross_fields （最佳字段、多数字段、跨字段）
     * <p>
     * best_fields 类型是默认值，可以不指定。
     * 查询字段名称的模糊匹配
     */
    @Test
    public void testMultiMatch() {
        SearchResponse response = client.prepareSearch("my_index")
                .setTypes("my_type")
                .setQuery(QueryBuilders.multiMatchQuery("Brown fox", "title", "body"))
                .setQuery(QueryBuilders.matchPhrasePrefixQuery("title", "Brown fox"))//输入即搜索
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                //.addRescorer(RescoreBuilder.queryRescorer(QueryBuilders.disMaxQuery()),10)//结果集重新评分
                .get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    @Test
    public void testNestedBuilder() {

        SearchRequestBuilder srb = client.prepareSearch(index);
        srb.setTypes(type);
        srb.setSearchType(SearchType.DEFAULT);
        
        //嵌套查询root节点
        NestedAggregationBuilder nestedBuilder = AggregationBuilders.nested("xxx", "quests");
        //嵌套查询的子查询中分组count
        TermsAggregationBuilder tb = AggregationBuilders.terms("group_name");
        tb.field("quests.name");
        //添加子查询到root节点里面
        nestedBuilder.subAggregation(tb);
        srb.addAggregation(nestedBuilder);
        
        //获取一级嵌套
        Nested negsted= srb.get().getAggregations().get("negsted");
        //打印一级嵌套的结果
        System.out.println(negsted.getDocCount());
        //从一级嵌套查询的结果中获取二级嵌套查询结果
        Terms term = negsted.getAggregations().get("group_name");
        //获取嵌套下面的结果
        for (Terms.Bucket tbket : term.getBuckets()) {
            System.out.println(tbket.getKey() + "  " + tbket.getDocCount());
        }
    }
    //TODO  地理位置索引
    //http://blog.csdn.net/yusewuhen/article/details/50896151

}