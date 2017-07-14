package org.naturn.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月20日 - 上午10:20:35
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class FlatMapDemo {

	public static void main(String[] args) {
		
		
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		
		IntStream.generate(()->(int) (System.nanoTime()% 100)).limit(10).forEach(System.out::println);
	}
	
	public static class Person{
		
		int no;
		String name ;
		int age;
		
		public Person(int no,String name,int age){
			this.name = name;
			this.no = no;
			this.age = age;
		}
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
				
	}

}
