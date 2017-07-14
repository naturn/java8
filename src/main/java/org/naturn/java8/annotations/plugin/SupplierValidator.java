package org.naturn.java8.annotations.plugin;

import java.util.function.Supplier;

import javax.xml.bind.ValidationException;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月17日 - 下午7:30:21
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class SupplierValidator {

	public static boolean validate(Supplier<?> supplier) {
		for(Validate annotation : supplier.getClass().getAnnotationsByType(Validate.class)){
			try {
				annotation.value().validate(supplier);
			} catch (ValidationException e) {
				System.out.println(annotation.description());
								e.printStackTrace();
								return false;
			}
		}
		return true;
	}
}
