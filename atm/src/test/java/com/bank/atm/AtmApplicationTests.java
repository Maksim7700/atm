package com.bank.atm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AtmApplicationTests {

	@Test
	void testPasswordEncoder() {
		
		System.out.println(new BCryptPasswordEncoder().encode("max"));
	}
}
