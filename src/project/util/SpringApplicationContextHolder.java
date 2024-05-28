package project.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;




/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大家下载学 习参考
 */
public class SpringApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringApplicationContextHolder.context = context;
	}

	
	public static Object getSpringBean(String beanName) {
		if(beanName!=null&&!"".equals(beanName)){
			return context.getBean(beanName);
		}else{
			return null;
		}
	}

	public static String[] getBeanDefinitionNames() {
		return context.getBeanDefinitionNames();
	}

}
