package com.tsa.tsa;

/*
 * Short Example of using the SpringBootTest
 */


import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsaApplicationTests {

	@Test
	public void contextLoads() {
		assertThat(true).isTrue();
	}

}
