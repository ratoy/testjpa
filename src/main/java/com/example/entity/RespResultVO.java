package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuhao on 2017/10/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespResultVO<T> implements Serializable {

    //操作结果提示码，0表示正常
    private int code;
    //操作结果提示信息
    private String message;
    //主要用于多行查询结果
    private List<T> dataList;
    //分页查询时存储所有记录数量
    private long totalCount;
    //分页查询时存储总页数
    private int totalPages;
    //分页查询时存储当前页编号, 从1开始
    private int pageIndex;
    //单行操作结果
    private T model;
}
