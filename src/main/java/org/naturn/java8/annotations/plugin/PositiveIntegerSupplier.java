package org.naturn.java8.annotations.plugin;

import java.util.function.Supplier;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月17日 - 下午7:41:08
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Validate(value = Validator.INTEGER_NUMBER, description = "It's not as integer.")
@Validate(value = Validator.POSITIVE_NUMBER, description = "It's not a positive Number.")
public class PositiveIntegerSupplier implements Supplier<String> {

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return "20012";
	}

}
