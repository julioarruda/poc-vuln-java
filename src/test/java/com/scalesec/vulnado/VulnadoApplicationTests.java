package com.scalesec.vulnado;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VulnadoApplicationTests {

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void contextLoads() {
        Assert.assertNotNull("The application context should have loaded.", ctx);
    }

}