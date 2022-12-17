package com.example.qgame;

import com.example.qgame.services.test.TestProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

@SpringBootTest
@ActiveProfiles("test")
public class DemoMockitoTest {

    @Autowired
    private TestProductService productService;

    @Test
    public void TestProductServiceName(){
        Long id = 12L;

        Mockito
                .when(productService.getProductNameById(id)) // must be an instance of Mockito -- Mockito.mock(TestProductService.class)
                .thenReturn("Mock-Product-Name");

        String productName = productService.getProductNameById(id);
        Assert.assertEquals(productName, "Mock-Product-Name");

    }
}
