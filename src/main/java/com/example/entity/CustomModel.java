package com.example.entity;

import lombok.Data;

/**
 * Created by wuhao on 2017/10/17.
 */
@Data
public class CustomModel {
    private String brandimg;
    private String company;
    private Integer brandid ;

    public CustomModel(String img,String company,Integer id) {
        this.brandimg=img;
        this.company=company;
        this.brandid=id;
    }
}
