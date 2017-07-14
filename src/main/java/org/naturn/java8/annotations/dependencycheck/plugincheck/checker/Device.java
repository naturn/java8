package org.naturn.java8.annotations.dependencycheck.plugincheck.checker;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午9:34:39
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@XmlRootElement
public class Device {

	@XmlElement
	public Map<Module,Integer> supportedModule = new EnumMap<>(Module.class);
	
	public Map<Module,Integer> getSupportedMoudle(){
		
		return Collections.unmodifiableMap(supportedModule);
	}
	
}
