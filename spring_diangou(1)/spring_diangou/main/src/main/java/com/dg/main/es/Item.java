//package com.dg.main.es;
//
//import com.dg.main.Entity.Items;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Data
//@Document(indexName = "item",type = "docs", shards = 1, replicas = 0)
//public class Item {
//        @Id
//        private Long id;
//
//        @Field(type = FieldType.Text, analyzer = "ik_max_word")
//        private String title; //标题
//
//        @Field(type = FieldType.Keyword)
//        private String category;// 分类
//
//        @Field(type = FieldType.Keyword)
//        private String brand; // 品牌
//
//        @Field(type = FieldType.Text)
//        private String price; // 价格
//
//        @Field(index = false, type = FieldType.Keyword)
//        private String images; // 图片地址
//
//        public Item(Long id, String title, String category, String brand, String price, String images) {
//                this.id = id;
//                this.title = title;
//                this.category = category;
//                this.brand = brand;
//                this.price = price;
//                this.images = images;
//        }
//
//        public Item() {
//        }
//
//        public Item(Items items) {
//                this.id = items.getId();
//                this.title = items.getTitle();
//                this.category = items.getCategory();
//                this.brand = items.getBrand();
//                this.price = items.getPrice();
//                this.images = items.getImages();
//        }
//}