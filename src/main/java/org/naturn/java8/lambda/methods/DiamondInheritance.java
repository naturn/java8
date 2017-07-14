package org.naturn.java8.lambda.methods;

/**
 * @Author Naturn
 * 
 * @Date 2017年5月19日 - 下午3:09:12
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class DiamondInheritance {

	public interface Animal {

		String go();
	}

	public interface Horse extends Animal {

		@Override
		default String go() {
			return this.getClass().getSimpleName() + " walks on four legs. ";
		}
	}

	public interface Bird extends Animal {

		@Override
		default String go() {
			return this.getClass().getSimpleName() + " walks on two legs. ";
		}

		default String fly() {
			return this.getClass().getSimpleName() + " I can fly. ";
		}
	}

	public static class Pegasus implements Horse, Bird {

		@Override
		public String go() {
			return Horse.super.go();
		}
	}

	public static void main(String[] args) {
		System.out.println(new Pegasus().go());
	}
}
