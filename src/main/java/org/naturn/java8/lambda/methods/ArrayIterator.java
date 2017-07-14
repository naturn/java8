package org.naturn.java8.lambda.methods;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午2:50:04
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class ArrayIterator {

	protected ArrayIterator(){
		throw new UnsupportedOperationException();
	}
	
	public static <E> Iterator<E> iterator(final E[] array){
		
		return new Iterator<E>() {

			private int index = 0;
			
			@Override
			public boolean hasNext() {

				return (index < array.length);
			}

			@Override
			public E next() {

				if(!hasNext()){
					throw new NoSuchElementException();
				}
				return array[index++];
			}
		};
	}
	
	public static void main(String[] args) {
		
		Iterator<String> it = ArrayIterator.iterator(new String[]{"one","two","three"});
		
		while(it.hasNext()){
			System.out.println(it.next());
		}

		System.gc();
		System.exit(0);
	}

}
