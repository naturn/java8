package org.naturn.java8.lambda.methods;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午2:42:36
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class SimplestUsage {

	public interface Animal{
		
		default String eat(){
			
			return this.getClass().getSimpleName()+" eats like an ordinary animal. ";
		}		
	}
	
	public static class Dog implements Animal{
		
	}
	
	public static class Mosquito implements Animal{
		
		@Override
		public String eat(){
			return this.getClass().getSimpleName()+" consumes blood. ";
		}
	}
	
	public static void main(String[] args){
		
		System.out.println(new Dog().eat());
		
		System.out.println(new Mosquito().eat());
	}
}
