package org.alalgo.almvc.test;

import java.lang.reflect.Method;

import org.alalgo.almvc.annotation.Aspect;
import org.alalgo.almvc.aop.AroundAdvice;
@Aspect(target = AlmvcController.class)
public class AspectImpl implements AroundAdvice {

	@Override
	public void before(Class<?> clz, Method method, Object[] args) throws Throwable {
		System.out.println("before mehod is invoked ,created by AspectImpl.");
	}

	@Override
	public void afterReturning(Class<?> clz, Object returnValue, Method method, Object[] args) throws Throwable {
		System.out.println("after mehod is invoked ,created by AspectImpl.");
	}

	@Override
	public void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e) {
		System.out.println("exception throw mehod is invoked ,created by AspectImpl.");
	}

}
