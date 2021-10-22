package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void testAVERSIESTRU() {
		Assert.isTrue(true, "Tiene que ser tru porke está harcodeado we :v");
	}

}
