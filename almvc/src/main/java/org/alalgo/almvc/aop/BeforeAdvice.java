package org.alalgo.almvc.aop;

import java.lang.reflect.Method;

public interface BeforeAdvice extends Advice {
	void before(Class<?> clz, Method method, Object[] args) throws Throwable;
}
