package org.alalgo.almvc.aop;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodProxy;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProxyAdvisor {
	
	private Advice advice;
	
	public Object doProxy(Object target, Class<?> targetClass, Method method, Object[] args, MethodProxy proxy) throws Throwable { 
		Object result = null;
		if(advice instanceof BeforeAdvice) {
			((BeforeAdvice) advice).before(targetClass, method, args);
		}
		try {
			result = proxy.invokeSuper(target, args);
		}catch(Exception e) {
			if (advice instanceof ThrowsAdvice) {
				((ThrowsAdvice) advice).afterThrowing(targetClass, method, args, e);
			}
			throw new Exception();
		}
		if (advice instanceof AfterAdvice){
			((AfterAdvice) advice).afterReturning(targetClass, result, method, args);
		}
		return result;
	}

}
