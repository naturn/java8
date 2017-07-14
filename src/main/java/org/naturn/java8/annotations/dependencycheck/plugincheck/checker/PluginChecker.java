package org.naturn.java8.annotations.dependencycheck.plugincheck.checker;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月18日 - 上午10:08:14
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

@SupportedAnnotationTypes("checker.RequireContainer")
@SupportedSourceVersion(SourceVersion.RELEASE_0)
public class PluginChecker extends AbstractProcessor {

	public static final String DEVICE_OPTION = "device";
	
	private Device device;
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		for(Element el: roundEnv.getElementsAnnotatedWith(RequireContainer.class)){
			for(Require req : el.getAnnotationsByType(Require.class)){
				Integer version = device.getSupportedMoudle().get(req.value());
				if(version == null || version < req.minVersion() || version > req.maxVersion()){
					if(req.optional()){
						processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.WARNING,
                                "Plugin [" + el + "] requires " + req
                                + "\n but device " + (version == null
                                ? "doesn't have such module."
                                + " This module is optional."
                                + " So plugin will work but miss"
                                + " some functionality"
                                : "has " + version
                                + " version of that module"));
					}else{
						processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.ERROR,
                                "Plugin [" + el + "] requires " + req
                                + "\n but device "
                                + (version == null
                                ? "doesn't have such module"
                                : "has " + version
                                + " version of that module"));
						
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public Set<String> getSupportedOptions(){
		return new HashSet<>(Arrays.asList(DEVICE_OPTION));
	}
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv){
		super.init(processingEnv);
		
		String deviceOption = processingEnv.getOptions().get(DEVICE_OPTION);
		try{
			device = (Device) JAXBContext.newInstance(Device.class).createUnmarshaller().unmarshal(new File(deviceOption));
		}catch(JAXBException e){
			throw new RuntimeException("Please specify device by -Adevice = device.xml \n"+e.toString(),e);
		}
		
	}

}
