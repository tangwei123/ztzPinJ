package tw.com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tw.com.utils.c3p0Utils;

class c3p0UtilsTest {

	@Test
	void testGetConnection() {
		System.out.println(c3p0Utils.getConnection());
	}

}
