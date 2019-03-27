package org.alalgo.almvc;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.alalgo.almvc.annotation.Aspect;
import org.alalgo.almvc.annotation.Component;
import org.alalgo.almvc.annotation.Controller;
import org.alalgo.almvc.annotation.Repository;
import org.alalgo.almvc.annotation.Service;
import org.alalgo.almvc.util.ClassUtil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {
	// 单例模式生成BeanContainer实例，保证唯一
	private static class BeanContainerHolder {
		private static final BeanContainer beanContainer = new BeanContainer();
	}

	public static final BeanContainer getBeanContainer() {
		return BeanContainerHolder.beanContainer;
	}

	private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<Class<?>, Object>();

	public Object getBean(Class<?> clz) {
		if (null == clz) {
			return null;
		}
		return beanMap.get(clz);
	}

	public Set<Object> getBeans() {
		return (HashSet<Object>) beanMap.values();
	}

	public Set<Class<?>> getClasses() {
		return beanMap.keySet();
	}

	public Object addBean(Class<?> clz, Object bean) {
		return beanMap.put(clz, bean);
	}

	public void removeBean(Class<?> clz) {
		beanMap.remove(clz);
	}
	
	public int size() {
		return beanMap.size();
	}

	private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class,
			Controller.class, Service.class, Repository.class,Aspect.class);
	
	private boolean isLoadBean = false;

	public boolean isLoadBean() {
		return isLoadBean;
	}
	
	public void loadBeans(String basePackage) {
		if (isLoadBean()) {
			log.warn("bean已经加载");
			return;
		}
		Set<Class<?>> classSet = ClassUtil.getPackageClass(basePackage);
		classSet.stream().filter(clz -> {
			for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
				if (clz.isAnnotationPresent(annotation)) {
					return true;
				}
			}
			return false;
		}).forEach(clz -> beanMap.put(clz, ClassUtil.newInstance(clz)));
		isLoadBean = true;
	}
	/**
	 * 
	    * @Title: getClassesBySuper
	    * @Description: 获取beanMap中给定superclass的子类
	    * @param @param superClass
	    * @param @return
	    * @return Set<Class<?>>
	    * @throws
	 */
	public Set<Class<?>> getClassesBySuper(Class<?> superClass) {
		return beanMap.keySet().stream().filter(superClass::isAssignableFrom).filter(clz -> !clz.equals(superClass))
				.collect(Collectors.toSet());
	}	
	public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        return beanMap.keySet()
                .stream()
                .filter(clz -> clz.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
	}
}
