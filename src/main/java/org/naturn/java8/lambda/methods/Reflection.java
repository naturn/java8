package org.naturn.java8.lambda.methods;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午2:23:46
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class Reflection {

	public interface Animal{
		default String eat(){
			return this.getClass().getSimpleName()
                    + " eats like an ordinary animal";
		}
		
		default String sleep(){
			return this.getClass().getSimpleName()
                    + " sleeps like an ordinary animal";
		}
		
		String go();
	}
	
	public static class Dog implements Animal{

		@Override
		public String go() {
			// TODO Auto-generated method stub
			return "Dog walks on four legs";
		}
		
		@Override
		public String sleep(){
			return "Dog sleeps";
		}
				
	}
	
	public static void main(String[] args)throws Exception{
		
		Dog dog = new Dog();
		Stream.of(Dog.class.getMethod("eat"),Dog.class.getMethod("sleep"),Dog.class.getMethod("go")).forEach((m) -> {
			System.out.println("Method name: " + m.getName());
			System.out.println("   isDefault:" + m.isDefault());
			System.out.println("   invoke:    ");
			try{
				m.invoke(dog);
			}catch(IllegalArgumentException | IllegalAccessException | InvocationTargetException ex){
				
			}
			System.out.println("");
		});
	}
}
