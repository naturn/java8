package org.naturn.java8.annotations.dependencycheck.plugincheck.plugins;

import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Module;
import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Require;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:53:48
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Require(value = Module.CLOCK, maxVersion = 3)
@Require(value = Module.THERMOMETER)
@Require(value = Module.HEATER)
@Require(value = Module.LED, optional = true)
public class BoilerPlugin {

	public void boil(){
		boil(100);
	}
	
	public void boil(int temperature){}
	
	public void keepwarm(int temperature, int seconds){}
}
