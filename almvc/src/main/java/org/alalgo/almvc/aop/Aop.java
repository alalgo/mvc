package org.alalgo.almvc.aop;

import org.alalgo.almvc.BeanContainer;
import org.alalgo.almvc.annotation.Aspect;

public class Aop {
	private BeanContainer beanContainer;

	public Aop() {
		beanContainer = BeanContainer.getBeanContainer();
	}
	public void doAop() {
		beanContainer.getClassesBySuper(Advice.class)
			.stream()
			.filter(clz -> clz.isAnnotationPresent(Aspect.class))
			.forEach(clz -> {
				Advice advice =  (Advice) beanContainer.getBean(clz);
				Aspect aspect = clz.getAnnotation(Aspect.class);
				Class<?> target = aspect.target();
				Object proxyBean = ProxyCreator.createProxy(target, new ProxyAdvisor(advice));
				beanContainer.addBean(target, proxyBean);
			});

	}
}
