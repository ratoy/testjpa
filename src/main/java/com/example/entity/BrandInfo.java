/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wuhao
 */
@Entity
@Table(name = "brandinfo")
@Data
public class BrandInfo implements Serializable {
    @Id
    Integer id;
    String company;
    Integer brandid;
}
