package com.dg.main.configuration;
//@Configuration
//public class ElasticConfig {
//    @Value("${es.host}")
//    public String host;
//    @Value("${es.port}")
//    public Integer port;
//    @Value("${es.schema}")
//    public String schema;
//    public static RestHighLevelClient client = null;
//
//    @PostConstruct
//    public void init() {
//        try {
//            if (client != null) {
//                client.close();
//            }
//            client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, schema)));
//            if (this.indexExist("url_log")) {
//                return;
//            }
//            CreateIndexRequest request = new CreateIndexRequest("url_log");
//            request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
//            request.mapping("url_log", XContentType.JSON);
//            CreateIndexResponse res = client.indices().create(request, RequestOptions.DEFAULT);
//            if (!res.isAcknowledged()) {
//                throw new RuntimeException("初始化失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//    /**
//     * Description: 判断某个index是否存在
//     *
//     * @param index index名
//     * @return boolean
//     * @author fanxb
//     * @date 2019/7/24 14:57
//     */
//    public boolean indexExist(String index) throws Exception {
//        GetIndexRequest request = new GetIndexRequest(index);
//        request.local(false);
//        request.humanReadable(true);
//        request.includeDefaults(false);
//        return client.indices().exists(request, RequestOptions.DEFAULT);
//    }
//
//    /** 断某个index是否存在
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:27
//     * @param idxName index名
//     * @return boolean
//     * @throws
//     * @since
//     */
//    public boolean isExistsIndex(String idxName) throws Exception {
//        return client.indices().exists(new GetIndexRequest(idxName),RequestOptions.DEFAULT);
//    }
//
//    /** 设置分片
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 19:27
//     * @param request
//     * @return void
//     * @throws
//     * @since
//     */
//    public void buildSetting(CreateIndexRequest request){
//        request.settings(Settings.builder().put("index.number_of_shards",3)
//                .put("index.number_of_replicas",2));
//    }
//    /**
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:27
//     * @param idxName index
//     * @param entity    对象
//     * @return void
//     * @throws
//     * @since
//     */
//    public void insertOrUpdateOne(String idxName, ElasticEntity entity) {
//        IndexRequest request = new IndexRequest(idxName);
////        log.error("Data : id={},entity={}",entity.getId(), JSON.toJSONString(entity.getData()));
//        request.id(entity.getId());
//        request.source(entity.getData(), XContentType.JSON);
////        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
//        try {
//            client.index(request, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    /** 批量插入数据
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:26
//     * @param idxName index
//     * @param list 带插入列表
//     * @return void
//     * @throws
//     * @since
//     */
//    public void insertBatch(String idxName, List<ElasticEntity> list) {
//        BulkRequest request = new BulkRequest();
//        list.forEach(item -> request.add(new IndexRequest(idxName).id(item.getId())
//                .source(item.getData(), XContentType.JSON)));
//        try {
//            client.bulk(request, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** 批量删除
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:14
//     * @param idxName index
//     * @param idList    待删除列表
//     * @return void
//     * @throws
//     * @since
//     */
//    public <T> void deleteBatch(String idxName, Collection<T> idList) {
//        BulkRequest request = new BulkRequest();
//        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));
//        try {
//            client.bulk(request, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:14
//     * @param idxName index
//     * @param builder   查询参数
//     * @param c 结果类对象
//     * @return java.util.List<T>
//     * @throws
//     * @since
//     */
//    public <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
//        SearchRequest request = new SearchRequest(idxName);
//        request.source(builder);
//        try {
//            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//            SearchHit[] hits = response.getHits().getHits();
//            List<T> res = new ArrayList<>(hits.length);
//            for (SearchHit hit : hits) {
//                res.add(JSON.parseObject(hit.getSourceAsString(), c));
//            }
//            return res;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** 删除index
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:13
//     * @param idxName
//     * @return void
//     * @throws
//     * @since
//     */
//    public void deleteIndex(String idxName) {
//        try {
//            if (!this.indexExist(idxName)) {
////                log.error(" idxName={} 已经存在",idxName);
//                System.out.println(idxName + " idxName={} 已经存在");
//                return;
//            }
//            client.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    /**
//     * @author WCNGS@QQ.COM
//     * @See
//     * @date 2019/10/17 17:13
//     * @param idxName
//     * @param builder
//     * @return void
//     * @throws
//     * @since
//     */
//    public void deleteByQuery(String idxName, QueryBuilder builder) {
//
//        DeleteByQueryRequest request = new DeleteByQueryRequest(idxName);
//        request.setQuery(builder);
//        //设置批量操作数量,最大为10000
//        request.setBatchSize(10000);
//        request.setConflicts("proceed");
//        try {
//            client.deleteByQuery(request, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
