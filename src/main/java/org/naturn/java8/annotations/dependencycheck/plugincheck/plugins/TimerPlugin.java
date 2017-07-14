package org.naturn.java8.annotations.dependencycheck.plugincheck.plugins;

import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Module;
import org.naturn.java8.annotations.dependencycheck.plugincheck.checker.Require;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:49:51
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@Require(Module.DISPLAY)
@Require(value = Module.CLOCK,maxVersion = 3)
public class TimerPlugin {

	public void timer(long time){}
	
	public void alarm(long time){}
}
