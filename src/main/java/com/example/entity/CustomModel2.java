package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wuhao on 2017/10/17.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomModel2 {
    private String brandimg;
    private String company;
    private Integer brandid ;

}
