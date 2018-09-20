package com.example.controller;

import com.example.entity.*;
import com.example.dao.BrandRepository;
import com.example.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wuhao on 2017/10/17.
 */

@Slf4j
@RestController
@Api(description = "测试jpa的API")
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @Autowired
    BrandService brandService;

    @ApiOperation(value = "查询单属性测试", notes = "查询单属性测试")
    @GetMapping(value = "/single/allid")
    public RespResultVO<Long> findSingle(@ApiParam(value = "品牌名称")
                                         @RequestParam(required = true) String brandname) {
        List<Long> brandIdList = null;
        Long brandId = null;
        if (brandname == null) {
            brandIdList = brandService.getAllIds();
            return RespResultVO.<Long>builder().code(0).dataList(brandIdList).build();
        } else {
            brandId = brandService.getIdByName(brandname);
            return RespResultVO.<Long>builder().code(0).model(brandId).build();
        }
    }

    @ApiOperation(value = "查询单属性测试2", notes = "查询单属性测试2")
    @GetMapping(value = "/single/all")
    public RespResultVO<String> findSingle2(@RequestParam(required = true) String brandname) {
        String brandId = brandService.getImgByName(brandname);
        return RespResultVO.<String>builder().code(0).model(brandId).build();
    }

    @ApiOperation(value = "接口连接查询测试", notes = "接口连接查询测试")
    @GetMapping(value = "/join/interface")
    public RespResultVO<CustomBrand> hello0() {
        List<CustomBrand> brandList = brandService.findCustomBrand();
        return RespResultVO.<CustomBrand>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "接口连接查询测试", notes = "原生SQL接口连接查询测试")
    @GetMapping(value = "/join/interface2")
    public RespResultVO<CustomBrand> hello02() {
        List<CustomBrand> brandList = brandService.findCustomBrandNative();
        return RespResultVO.<CustomBrand>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "接口连接查询测试", notes = "原生SQL接口连接查询测试2")
    @GetMapping(value = "/join/interface3")
    public RespResultVO<CustomBrand2> helloNative2() {
        List<CustomBrand2> brandList = brandService.findCustomBrandNative2();
        return RespResultVO.<CustomBrand2>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "类接口连接查询测试", notes = "类接口连接查询测试")
    @GetMapping(value = "/join/class")
    public RespResultVO<CustomModel> hello3() {
        List<CustomModel> brandList = Lists.newArrayList(brandService.findSurveyCount());
        return RespResultVO.<CustomModel>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "不定参数查询测试", notes = "不定参数查询测试")
    @PostMapping(value = "/custom")
    public RespResultVO<CustomModel2> customQuery(@RequestBody(required = false) OrderParamVO orderParamVO) {
        List<CustomModel2> brandList = brandService.getCustomModel2(orderParamVO);
        return RespResultVO.<CustomModel2>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "普通查询测试", notes = "普通查询测试")
    @GetMapping(value = "/all")
    public RespResultVO<Brand> hello2() {
        //List<Brand> brandList = Lists.newArrayList(brandRepository.findAllByOrderByBrandidDesc());
        List<Brand> brandList = Lists.newArrayList(brandService.findAllByOrderByBrandnameDesc());
        return RespResultVO.<Brand>builder().code(0).dataList(brandList).build();
    }

    @ApiOperation(value = "更新测试", notes = "更新测试")
    @PutMapping(value = "/update")
    public RespResultVO<Boolean> update(@RequestParam(required = false) Integer id, @RequestParam(required = true) String name) {
        brandService.setFixedFirstnameFor(name, id);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "原始更新测试", notes = "原始更新测试")
    @PutMapping(value = "/update2")
    public RespResultVO<Boolean> update2(@RequestParam(required = false) Integer id, @RequestParam(required = true) String name) {
        brandService.setFixedFirstnameNative(name, id);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "删除测试", notes = "删除测试")
    @DeleteMapping(value = "/delete")
    public RespResultVO<Boolean> delete(@RequestParam(required = false) Integer id) {
        brandService.deleteBrandById(id);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "删除测试", notes = "删除测试")
    @DeleteMapping(value = "/delete2")
    public RespResultVO<Boolean> delete2(@RequestParam(required = false) Integer id) {
        brandService.deleteBrandByIdNative(id);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "删除测试", notes = "删除测试")
    @DeleteMapping(value = "/delete3")
    public RespResultVO<Boolean> delete3(@RequestParam(required = false) String brandName) {
        brandService.deleteBrandByBrandName(brandName);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "删除测试", notes = "删除测试")
    @DeleteMapping(value = "/delete4")
    public RespResultVO<Boolean> delete4(String brandName, String brandImg) {
        brandService.deleteByBrandNameAndBrandImg(brandName, brandImg);
        return RespResultVO.<Boolean>builder().code(0).model(true).build();
    }

    @ApiOperation(value = "分页查询测试", notes = "分页查询测试")
    @GetMapping(value = "/all/page")
    public RespResultVO<Brand> hello4(@RequestParam String name, @RequestParam int pageindex, @RequestParam int pagesize) {
        Page<Brand> brands = null;
        //pageindex 要从1开始
        if (name.equals(null) || name.length() == 0) {
            brands = brandService.findAllByOrderByBrandidDesc(new PageRequest(pageindex - 1, pagesize));
        } else {
            brands = brandService.findAllByBrandnameLikeOrderByBrandidDesc(name + "%", new PageRequest(pageindex - 1, pagesize));
        }
        return RespResultVO.<Brand>builder()
                .code(0)
                .dataList(brands.getContent())
                .pageIndex(pageindex)
                .totalPages(brands.getTotalPages())
                .totalCount(brands.getTotalElements())
                .build();
    }

    @ApiOperation(value = "Criteria查询测试", notes = "Criteria查询测试")
    @GetMapping(value = "/criteria/test")
    public RespResultVO<String> criteriaTest() {
        List<String> brandimgList = brandService.findBrandImgByCriteria();
        return RespResultVO.<String>builder().code(0).dataList(brandimgList).build();
    }

    @ApiOperation(value = "Criteria查询测试", notes = "Criteria查询测试")
    @GetMapping(value = "/like/test")
    public RespResultVO<Brand> likeTest(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String img) {
        List<Brand> brandimgList = brandService.findBrandLike(name, img);
        return RespResultVO.<Brand>builder().code(0).dataList(brandimgList).build();
    }

    @ApiOperation(value = "简单查询测试", notes = "简单查询测试")
    @GetMapping(value = "/byfield")
    public RespResultVO<Brand> findByField(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String img) {
        List<Brand> brandimgList = brandService.findByNameAndImg(name, img);
        return RespResultVO.<Brand>builder().code(0).dataList(brandimgList).build();
    }

    @ApiOperation(value = "distinct查询测试", notes = "distinct查询测试")
    @GetMapping(value = "/distinct")
    public RespResultVO<CustomModel3> findDistinctByInfo2() {
        List<CustomModel3> brandimgList = brandService.findDistinctByInfo2();
        return RespResultVO.<CustomModel3>builder().code(0).dataList(brandimgList).build();
    }

    @ApiOperation(value = "specification查询测试", notes = "specification查询测试")
    @GetMapping(value = "/specification")
    public RespResultVO<Brand> findDistinctBySpec(Long id, String name, String img) {
        Brand brand = Brand.builder()
                .brandid(id)
                .brandname(name)
                .brandimg(img)
                .build();
        List<Brand> brandimgList = brandService.findAllUseSpecification(brand);
        return RespResultVO.<Brand>builder().code(0).dataList(brandimgList).build();
    }
}
