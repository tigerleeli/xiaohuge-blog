package com.example.elasticsearchdemo.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.example.elasticsearchdemo.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("elastic")
@RestController
public class ElasticController {
    @Resource
    private ElasticsearchClient esClient;

    @GetMapping("createIndex")
    public Boolean createIndex() throws IOException {
        // 创建索引
        esClient.indices().create(c -> c
                .index("products")
        );
        return true;
    }

    @GetMapping("initData")
    public Boolean initData() throws IOException {
        long startTime = System.currentTimeMillis();

        // 模拟生成10万条数据
        List<BulkOperation> bulkOperations = new ArrayList<>();
        for (int i = 0; i < 10 * 10000; i++) {
            Product product = new Product(String.valueOf(i), UUID.randomUUID().toString());

            bulkOperations.add(new BulkOperation.Builder()
                    .create(d -> d.document(product).id(product.getCode()))
                    .build());
        }
        BulkResponse response = esClient.bulk(e -> e.index("products").operations(bulkOperations));

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("耗费时间：" + elapsedTime + "毫秒");
        return true;
    }

    @GetMapping("getData")
    public Product getData(@RequestParam String code) throws IOException {
        // 获取数据
        long startTime = System.currentTimeMillis();

        GetResponse<Product> response = esClient.get(g -> g
                        .index("products")
                        .id(code),
                Product.class
        );

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("耗费时间：" + elapsedTime + "毫秒");

        if (response.found()) {
            Product product = response.source();
            log.info("查询到商品:{}", product);
            return product;
        } else {
            log.info("商品不存在");
            return null;
        }

    }
}
