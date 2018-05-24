/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author wuhao
 */
public interface CustomBrand {
    @JsonIgnore
    Integer getBrandid();
    String getBrandname();
    String getBrandimg();
    String getCompany();

}

