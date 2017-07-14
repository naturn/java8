package org.naturn.java8.annotations.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月17日 - 下午7:14:22
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidateContainer.class)
@Target(ElementType.TYPE)
public @interface Validate {

	Validator value();
	
	String description() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ValidateContainer{
	
	Validate[] value();
}
