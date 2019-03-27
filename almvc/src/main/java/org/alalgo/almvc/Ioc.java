package org.alalgo.almvc;

import java.lang.reflect.Field;
import java.util.Optional;

import org.alalgo.almvc.annotation.Autowired;
import org.alalgo.almvc.util.ClassUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ioc {
	private BeanContainer beanContainer;

	public Ioc() {
		beanContainer = BeanContainer.getBeanContainer();
	}
	

	private Class<?> getImplementClass(final Class<?> interfaceClass) {
		return beanContainer.getClassesBySuper(interfaceClass).stream().findFirst().orElse(null);
	}	

	private Object getClassInstance(final Class<?> clz) {
		return Optional.ofNullable(beanContainer.getBean(clz)).orElseGet(() -> {
			Class<?> implementClass = getImplementClass(clz);
			if (null != implementClass) {
				return beanContainer.getBean(implementClass);
			}
			return null;
		});
	}

	public void doIoc() {
		for (Class<?> clz : beanContainer.getClasses()) { // 遍历Bean容器中所有的Bean
			final Object targetBean = beanContainer.getBean(clz);
			Field[] fields = clz.getDeclaredFields();
			for (Field field : fields) { // 遍历Bean中的所有属性
				if (field.isAnnotationPresent(Autowired.class)) {// 如果该属性被Autowired注解，则对其注入
					final Class<?> fieldClass = field.getType();
					Object fieldValue = getClassInstance(fieldClass);
					if (null != fieldValue) {
						ClassUtil.setField(field, targetBean, fieldValue);
					} else {
						throw new RuntimeException("无法注入对应的类，目标类型:" + fieldClass.getName());
					}
				}
			}
		}
	}
}
