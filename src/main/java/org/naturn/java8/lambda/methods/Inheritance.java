package org.naturn.java8.lambda.methods;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午3:21:28
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class Inheritance {

	public interface Swimable{
		
		default String swim(){
			return "I can swim";
		}
	}
	
	public static class Fish implements Swimable{
		
		@Override
		public String swim(){
			return this.getClass().getSimpleName()+ " swims under water. ";
		}
	}
	
	public static class Tuna extends Fish implements Swimable{}
	
	public interface Diveable extends Swimable{
		
		default String swim(){
			return "I can swim on the surface of the water. ";
		}
		
		default String dive(){
			return "I can dive. ";
		}
	}
	
	public static class Duck implements Swimable,Diveable{
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(new Tuna().swim());
		
		System.out.println(new Duck().swim());

	}

}
