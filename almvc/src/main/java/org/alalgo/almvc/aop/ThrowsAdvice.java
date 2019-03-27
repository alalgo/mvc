package org.alalgo.almvc.aop;

import java.lang.reflect.Method;

public interface ThrowsAdvice extends Advice {
	 void afterThrowing(Class<?> clz, Method method, Object[] args, Throwable e);
}
