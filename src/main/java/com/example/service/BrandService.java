package com.example.service;

import com.example.dao.BrandRepository;
import com.example.dao.BrandSpec;
import com.example.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhao on 2017/11/20.
 */
@Service
@Slf4j
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    public List<Long> getAllIds() {
        return brandRepository.getAllIds();
    }

    public Long getIdByName(String brandname) {
        return brandRepository.getIdByName(brandname);
    }

    public String getImgByName(String brandname) {
        return brandRepository.getImgByName(brandname);
    }

    public List<CustomBrand> findCustomBrand() {
        return brandRepository.findCustomBrand();
    }

    public List<CustomBrand> findCustomBrandNative() {
        return brandRepository.findCustomBrandNative();
    }

    public List<CustomBrand2> findCustomBrandNative2() {
        return brandRepository.findCustomBrandNative2();
    }

    public List<CustomModel> findSurveyCount() {
        return brandRepository.findSurveyCount();
    }

    public List<CustomModel2> getCustomModel2(OrderParamVO orderParamVO) {
        return brandRepository.getCustomModel2(orderParamVO);
    }

    public List<Brand> findAllByOrderByBrandnameDesc() {
        return brandRepository.findAllByOrderByBrandnameDesc();
    }

    public void setFixedFirstnameFor(String name, Integer id) {
        brandRepository.setFixedFirstnameFor(name, id);
    }

    public void setFixedFirstnameNative(String name, Integer id) {
        brandRepository.setFixedFirstnameNative(name, id);
    }

    public void deleteBrandById(Integer id) {
        brandRepository.deleteBrandById(id);
    }

    public void deleteBrandByIdNative(Integer id) {
        brandRepository.deleteBrandByIdNative(id);
    }

    public void deleteBrandByBrandName(String brandName) {
        Long result=brandRepository.deleteByBrandname(brandName);
        log.info("delete result: ==========>>>>>>"+result);
    }

    public void deleteByBrandNameAndBrandImg(String brandName, String brandImg) {
        brandRepository.deleteByBrandnameAndBrandimg(brandName,brandImg);
    }

    public Page<Brand> findAllByOrderByBrandidDesc(PageRequest pageRequest) {
        return brandRepository.findAllByOrderByBrandidDesc(pageRequest);
    }

    public Page<Brand> findAllByBrandnameLikeOrderByBrandidDesc(String s, PageRequest pageRequest) {
        return brandRepository.findAllByBrandnameLikeOrderByBrandidDesc(s, pageRequest);
    }

    public List<String> findBrandImgByCriteria() {
        return brandRepository.findBrandImgByCriteria();
    }

    public List<Brand> findBrandLike(String name, String img) {
        if (name == null) {
            name = "";
        }
        if (img == null) {
            img = "";
        }
        return brandRepository.findByBrandnameLikeAndBrandimgLike("%" + name + "%", "%" + img + "%");
    }

    public List<Brand> findByNameAndImg(String name, String img) {
        return brandRepository.findByBrandnameAndBrandimg(name,img);
    }

    public List<CustomModel3> findDistinctByInfo2(){
        return brandRepository.findDistinctBrandnameByInfo2();
    }

    public List<Brand> findAllUseSpecification(Brand b){
        return brandRepository.findAll(BrandSpec.getSpec(b.getBrandid(),b.getBrandname(),b.getBrandimg()));
    }

    public List<Brand> findByCompany(String company) {
        return brandRepository.findByCompany(company);
    }
}
