package org.alalgo.almvc.test;

import org.alalgo.almvc.BeanContainer;
import org.alalgo.almvc.Ioc;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IocTest {
	@Test
	public void doIoc() {
		BeanContainer beanContainer = BeanContainer.getBeanContainer();
		beanContainer.loadBeans("org.alalgo.almvc");
		new Ioc().doIoc();
		AlmvcController controller = (AlmvcController) beanContainer.getBean(AlmvcController.class);
		controller.hi();
	}
}
