package com.scalesec.vulnado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VulnadoApplicationTests {

    @Autowired
    private VulnadoApplication vulnadoApplication; // Incluido por GFT AI Impact Bot

	@Test
	public void contextLoads() {
        assertThat(vulnadoApplication).isNotNull(); // Incluido por GFT AI Impact Bot
	}

}