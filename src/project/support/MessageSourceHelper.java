
package project.support;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免 费下载
 * 供大家下 载学习参考
 */
public class MessageSourceHelper extends StringHttpMessageConverter {
	private ReloadableResourceBundleMessageSource messageSource;

	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		String msg = messageSource.getMessage(code, args, defaultMessage,
				locale);

		return msg != null ? msg.trim() : msg;
	}

	public void setMessageSource(
			ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
