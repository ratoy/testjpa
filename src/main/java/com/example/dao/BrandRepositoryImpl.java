/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entity.*;
import com.example.entity.mapper.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 */
@Slf4j
public class BrandRepositoryImpl implements BrandRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    final String FIND_OUT_VO = "select bi.company as brandname,bi.brandid as id,b.brand_img as brandimg," +
            "bi.company as company from brand b, brandinfo bi where b.brand_id=bi.brandid and";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CustomModel2> getCustomModel2(OrderParamVO orderParamVO) {
        //查询条件：
        String querySql = FIND_OUT_VO;
        List<Object> paraList = new ArrayList<>();
        if (orderParamVO.getBrandid() != null) {
            querySql += " b.brand_id =? and";
            paraList.add(orderParamVO.getBrandid());
        }
        if (orderParamVO.getBrandimg() != null) {
            querySql += " b.brand_img =? and";
            paraList.add(orderParamVO.getBrandimg());
        }
        if (orderParamVO.getCompany() != null) {
            querySql += " bi.company =? and";
            paraList.add(orderParamVO.getCompany());
        }

        //trim last and
        querySql = querySql.substring(0,querySql.length()-3);

        //排序标准
        querySql += " order by bi.company = 'ccc' and b.brand_id";
        log.info("sql:   "+querySql);
        List<CustomModel2> orderOutVOList = jdbcTemplate.query(querySql,
                paraList.toArray(), new CustomModelMapper());

        return orderOutVOList;
    }

    @Override
    public List<String> findBrandImgByCriteria(){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
        Root<Brand> brandRoot= criteriaQuery.from(Brand.class);
        criteriaQuery.select(brandRoot.get("brandimg").as(String.class));
        criteriaQuery.where(builder.equal(brandRoot.get("brandid"),3));
        List<String> nameList = em.createQuery(criteriaQuery).getResultList();

        return nameList;
    }
}
