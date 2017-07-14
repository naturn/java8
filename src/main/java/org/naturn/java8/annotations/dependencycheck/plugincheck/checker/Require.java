package org.naturn.java8.annotations.dependencycheck.plugincheck.checker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:24:40
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */
@Retention(RetentionPolicy.CLASS)
@Repeatable(RequireContainer.class)
@Target(ElementType.TYPE)
public @interface Require {

	Module value();
	
	int minVersion() default 1;
	
	int maxVersion() default Integer.MAX_VALUE;
	
	boolean optional() default false;
}
