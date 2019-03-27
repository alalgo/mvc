package org.alalgo.almvc.test;

import org.alalgo.almvc.BeanContainer;
import org.alalgo.almvc.Ioc;
import org.alalgo.almvc.aop.Aop;
import org.junit.Test;

public class AopTest {
	@Test
	public void AopRun() {
		BeanContainer beanContainer = BeanContainer.getBeanContainer();
		beanContainer.loadBeans("org.alalgo.almvc");
		new Aop().doAop();
		new Ioc().doIoc();
		AlmvcController almvcController = (org.alalgo.almvc.test.AlmvcController) beanContainer.getBean(AlmvcController.class);
		almvcController.hi();
	}
}
