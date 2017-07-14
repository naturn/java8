package org.naturn.java8.annotations.plugin;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月17日 - 下午7:46:13
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class ValidateTest {

	@Test
	public void test() {
		
		PositiveIntegerSupplier pis = new PositiveIntegerSupplier();
		
		assertTrue(SupplierValidator.validate(pis));
	}

}
