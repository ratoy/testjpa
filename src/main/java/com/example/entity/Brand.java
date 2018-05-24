/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wuhao
 */
@Entity
@Table(name = "brand")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand implements Serializable {
    @Id
    @Column(name = "brand_id")
    Long brandid;
    @Column(name = "brand_name")
    String brandname;
    @Column(name = "brand_img")
    String brandimg;

    @Transient
    String info;
    String info2;
}
