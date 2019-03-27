package org.alalgo.almvc.aop;

import java.lang.reflect.Method;

public interface AfterAdvice extends Advice {
	 void afterReturning(Class<?> clz, Object returnValue, Method method, Object[] args) throws Throwable;
}
