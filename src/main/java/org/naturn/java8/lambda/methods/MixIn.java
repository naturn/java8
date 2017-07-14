package org.naturn.java8.lambda.methods;

import java.lang.reflect.Field;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午3:35:21
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class MixIn {

	public interface Debuggable{
		
		default String toDebugString(){
			StringBuilder sb = new StringBuilder();
			sb.append("State of the:").append(this.getClass().getSimpleName()).append("\n");
			
			for(Class<?> cls = this.getClass();cls != null;cls = cls.getSuperclass()){
				for(Field f : cls.getFields()){
					try{
						f.setAccessible(true);
						sb.append(f.getName()).append(" : ").append(f.get(this)).append("\n");
					}catch(IllegalAccessException e){}
				}
			}
			return sb.toString();
		}
	}
	
	public static enum BuildType implements Debuggable{
		BUILD(0,"-build"),
		PLAN(0,"-plan"),
		EXCLUDE(1,"-exclude"),
		TOTAL(2,"-total");
		
		private final int compareOrder;
		private final String pathSuffix;
		
		private BuildType(int compareOrder,String pathSuffix){
			this.compareOrder = compareOrder;
			this.pathSuffix = pathSuffix;
		}
		
		public int getCompareOrder(){
			return this.compareOrder;
		}
		
		public String getPathSuffix(){
			return this.pathSuffix;
		}
	}
			
	public static void main(String[] args) {
	
		System.out.println(BuildType.PLAN.toDebugString());

	}

}
