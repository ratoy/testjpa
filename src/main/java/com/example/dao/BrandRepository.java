/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

import java.util.List;

import com.example.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuhao
 */
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long>, BrandRepositoryCustom,
        JpaSpecificationExecutor<Brand> {
    @Query(value = "select bi.company as brandname,bi.brandid as id,b.brand_img as brandimg," +
            "bi.company as company from brand b, brandinfo bi where b.brand_id=bi.brandid", nativeQuery = true)
    List<CustomBrand2> findCustomBrandNative2();

    @Query(value = "select bi.company as brandname,bi.brandid as brandid,b.brand_img as brandimg," +
            "bi.company as company from brand b, brandinfo bi where b.brand_id=bi.brandid order by bi.id<2", nativeQuery = true)
    List<CustomBrand> findCustomBrandNative();

    @Query(value = "select bi.company as brandname,bi.brandid as brandid,b.brandimg as brandimg," +
            "bi.company as company from Brand b, BrandInfo bi where b.brandid=bi.brandid")
    List<CustomBrand> findCustomBrand();

    @Query(value = "select new com.example.entity.CustomModel(b.brandimg ,bi.company,bi.brandid )" +
            " from Brand b, BrandInfo bi where b.brandid=bi.brandid")
    List<CustomModel> findSurveyCount();

    Page<Brand> findByBrandnameLike(String brandname, Pageable pageable);

    List<Brand> findByBrandname(String brandname);

    @Query(value = "select brand_id from brand limit 0,1", nativeQuery = true)
    List<Long> getAllIds();

    @Query(value = "select brand_img from brand where brand_name=?1 limit 0,1", nativeQuery = true)
    String getImgByName(String brandname);

    @Query(value = "select brand_id from brand where brand_name=?1 limit 0,1", nativeQuery = true)
    Long getIdByName(String brandname);

    @Modifying
    @Transactional
    @Query("update Brand b set b.brandname= ?1 where b.brandid = ?2")
    int setFixedFirstnameFor(String brandname, Integer brandid);

    @Modifying
    @Transactional
    @Query(value = "update brand b set b.brand_name= ?1 where b.brand_id = ?2", nativeQuery = true)
    int setFixedFirstnameNative(String brandname, Integer brandid);

    @Modifying
    @Transactional
    @Query("delete from Brand b where b.brandid = ?1")
    void deleteBrandById(Integer brandid);

    @Modifying
    @Transactional
    Long deleteByBrandname(String brandname);

    @Modifying
    @Transactional
    void deleteByBrandnameAndBrandimg(String brandname, String brandimg);

    @Modifying
    @Transactional
    @Query(value = "delete from brand where brand_id = ?1", nativeQuery = true)
    void deleteBrandByIdNative(Integer brandid);

    List<Brand> findAllByOrderByBrandidDesc();

    List<Brand> findAllByOrderByBrandnameDesc();

    Page<Brand> findAllByOrderByBrandidDesc(Pageable pageable);

    Page<Brand> findAllByBrandnameLikeOrderByBrandidDesc(String brandname, Pageable pageable);

    List<Brand> findByBrandnameLikeAndBrandimgLike(String name, String img);

    List<Brand> findByBrandnameAndBrandimg(String name, String img);

    @Query("SELECT distinct new com.example.entity.CustomModel3(b.brandname,b.brandimg) FROM Brand b")
    List<CustomModel3> findDistinctBrandnameByInfo2();

    @Query("select b from Brand b, BrandInfo bi where b.brandid=bi.brandid and bi.company=?1")
    List<Brand> findByCompany(String company);
}

