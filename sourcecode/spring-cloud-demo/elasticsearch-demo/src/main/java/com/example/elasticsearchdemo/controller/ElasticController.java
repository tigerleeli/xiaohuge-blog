package com.example.elasticsearchdemo.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.example.elasticsearchdemo.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@RequestMapping("elastic")
@RestController
public class ElasticController {
    @Resource
    private ElasticsearchClient esClient;

    @GetMapping("create")
    public Boolean create() throws IOException {
        // 创建索引
        esClient.indices().create(c -> c
                .index("products")
        );

        // 存放数据
        Product product = new Product("bk-1", "City bike", 123.0f);

        IndexResponse response = esClient.index(i -> i
                .index("products")
                .id(product.getCode())
                .document(product)
        );
        log.info("Indexed with version " + response.version());


        return true;
    }

    @GetMapping("get")
    public Product get() {
        // 获取数据
        GetResponse<Product> response = null;
        try {
            response = esClient.get(g -> g
                            .index("products")
                            .id("bk-1"),
                    Product.class
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (response.found()) {
            Product product = response.source();
            log.info("Product name " + product.getName());
            return product;
        } else {
            log.info("Product not found");
            return null;
        }
    }
}
