package com.xp.aop.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

/**
 * Java规范里确实规定了外部类可以访问内部类的private/protected变量，
 * 就像访问自己的private/protected变量一样.........实际上，编译器实现的时候是这样的：
 * <p>
 * Outer类和Inner类不再是嵌套结构，而是变为一个包中的两个类，
 * 然后，对于private变量的访问，编译器会生成一个accessor函数.......
 *
 * @author xupan
 * @date 2021/07/06 16:04
 **/
public class Synthetic {

	class InnerSynthetic {
		/**
		 * 内部类有这样的一个私有属性，会自动提供一个access$000方法用来访问这个属性，叫做合成方法
		 * access$000
		 */
		private int n;
		private int s;

		/**
		 * 如果内部类提供了一个私有的构造方法，那么编译的时候就会多出一个合成的.class文件
		 */
		private InnerSynthetic() {

		}
	}

	public void a() {
		InnerSynthetic innerSynthetic = new InnerSynthetic();
		System.out.println(innerSynthetic.n);
		System.out.println(innerSynthetic.s);
	}

	public static void main(String[] args) {
		for (Method method : InnerSynthetic.class.getDeclaredMethods()) {
			System.out.println(method.getName());
		}

		for (Constructor<?> constructor : InnerSynthetic.class.getDeclaredConstructors()) {
			System.out.println("************************");
			for (Parameter parameter : constructor.getParameters()) {
				System.out.println(parameter.getType());
			}
		}

	}
}
