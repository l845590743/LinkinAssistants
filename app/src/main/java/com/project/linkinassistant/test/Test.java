package com.project.linkinassistant.test;

import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) {
		//传统的调用方式
		
		//反射调用方式
		//test1();
		//test2();
		//test3();
		test4();
	}

	//反射执行-公有+静态+无参数
	private static void test1() {
		try {
			//获取Heima类的class信息
			Class<?> cls = Class.forName("com.project.linkinassistant.test.Zhimin");
			//得到Heima类定义的hello方法
			//(name 方法名, parameterTypes 参数列表)
			Method method = cls.getDeclaredMethod("hello", null);
			//执行方法
			method.invoke(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射执行-公有+静态+有参数+有返回值
	private static void test2() {
		try {
			//获取Heima类的class信息
			Class<?> cls = Class.forName("com.project.linkinassistant.test.Zhimin");
			//得到Heima类定义的hello方法
			//(name 方法名, parameterTypes 参数列表),此处int.class不能写为Integer.class
			Method method = cls.getDeclaredMethod("hello2", String.class, int.class);
			//执行方法(null, null 传入的参数值)
			Object result = method.invoke(null, "李四", 18);
			System.out.println("返回值: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射执行-公有+非静态+有参数+有返回值
	private static void test3() {
		try {
			//获取Heima类的class信息
			Class<?> cls = Class.forName("com.project.linkinassistant.test.Zhimin");
			//得到一个类的实例
			Object instance = cls.newInstance();
			//得到Heima类定义的hello方法
			//(name 方法名, parameterTypes 参数列表),此处int.class不能写为Integer.class
			Method method = cls.getDeclaredMethod("hello3", String.class, int.class);
			//执行方法(obj 执行此方法的实例, null 传入的参数值)
			Object result = method.invoke(instance, "李四", 18);
			System.out.println("返回值: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//反射执行-私有+静态+无参数
	private static void test4() {
		try {
			//获取Heima类的class信息
			Class<?> cls = Class.forName("com.project.linkinassistant.test.Zhimin");
			//得到Heima类定义的hello方法
			//(name 方法名, parameterTypes 参数列表)
			Method method = cls.getDeclaredMethod("hello4", null);
			//暴力反射，修改访问权限
			method.setAccessible(true);
			//执行方法
			method.invoke(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
