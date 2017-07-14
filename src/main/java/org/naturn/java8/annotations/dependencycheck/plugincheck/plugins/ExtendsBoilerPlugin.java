package org.naturn.java8.annotations.dependencycheck.plugincheck.plugins;

import java.util.Calendar;

import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Module;
import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Require;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:58:41
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Require(Module.SPEAKER)
@Require(Module.DISPLAY)
@Require(value = Module.GSM, minVersion = 3)
public class ExtendsBoilerPlugin extends BoilerPlugin {

	public void boildAndWakeUp(Calendar calendar, int phoneNumber) {

	}
	
	public void boilBySMS(String sms){
		
	}
}
