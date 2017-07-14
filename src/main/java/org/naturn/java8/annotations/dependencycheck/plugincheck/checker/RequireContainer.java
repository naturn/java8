package org.naturn.java8.annotations.dependencycheck.plugincheck.checker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:26:03
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface RequireContainer {

	Require[] value();
}
