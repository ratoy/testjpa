/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import com.example.entity.Brand;
import com.example.entity.CustomModel2;
import com.example.entity.OrderParamVO;
import com.example.entity.mapper.CustomModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 */
@Slf4j
public class BrandSpec {
    public static Specification<Brand> getSpec(final Long id, final String name, final String img) {
        return new Specification<Brand>() {

            Long brandid;
            @Column(name = "brand_name")
            String brandname;
            @Column(name = "brand_img")
            String brandimg;

            @SuppressWarnings("unused")
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (id > 0) {
                    predicate.getExpressions().add(
                            cb.and(root.<Brand>get("brandid").in(id)));
                }

                if (!StringUtils.isEmpty(name)) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("brandname").in(name)));
                }

                if (!StringUtils.isEmpty(img)) {
                    predicate.getExpressions().add(
                            cb.and(root.<String>get("brandimg").in(img)));
                }

                return predicate;
            }
        };
    }
}
