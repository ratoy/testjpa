package com.example.service;

import com.example.dao.BrandRepository;
import com.example.entity.Brand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wuhao on 2017/11/20.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BrandRepositoryTest {
    @Autowired
    BrandRepository brandRepository;

    @Before
    public void setUp() {
        Brand brand=Brand.builder()
                .brandid(1l)
                .brandimg("img")
                .brandname("name")
                .build();

        brandRepository.save(brand);
    }

    @Test
    public void testFindOne() throws Exception {
        Brand brand=brandRepository.findOne(1l);
        assertTrue(brand.getBrandimg().equals("img"));
    }

}