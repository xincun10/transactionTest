package com.threadLocal;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/*
 * ThreadLocal������÷�
 */
public class ThreadLocalTest {

	@Test
	public void fun1()
	{
		ThreadLocal<String> tl = new ThreadLocal<String>();
		tl.set("hello");
		String s = tl.get();
		tl.remove();
		System.out.println(s);
	}
}

//ThreadLocal��ʵ��
class TL<T>
{
	private Map<Thread, T> map = new HashMap<Thread, T>();
	
	public void set(T data)
	{
		map.put(Thread.currentThread(), data);
	}
	
	public T get()
	{
		return map.get(Thread.currentThread());
	}
	
	public void remove()
	{
		map.remove(Thread.currentThread());
	}
}

class User
{
	private ThreadLocal<String> usernameTl = new ThreadLocal<String>();
}