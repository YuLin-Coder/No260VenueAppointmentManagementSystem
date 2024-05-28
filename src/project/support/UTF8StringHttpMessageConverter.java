
package project.support;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文 学习免费下载
 * 供大家下 载学习参考
 */
public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {
	private static final MediaType utf8 = new MediaType("text", "plain",
			Charset.forName("UTF-8"));
	private boolean writeAcceptCharset = true;

	@Override
	protected MediaType getDefaultContentType(String dumy) {
		return utf8;
	}

	@Override
	protected List<Charset> getAcceptedCharsets() {
		return Arrays.asList(utf8.getCharSet());
	}

	@Override
	protected void writeInternal(String s, HttpOutputMessage outputMessage)
			throws IOException {
		if (this.writeAcceptCharset) {
			outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
		}
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html",
				Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		Charset charset = utf8.getCharSet();
		FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(),
				charset));
	}

	public boolean isWriteAcceptCharset() {
		return writeAcceptCharset;
	}

	@Override
	public void setWriteAcceptCharset(boolean writeAcceptCharset) {
		this.writeAcceptCharset = writeAcceptCharset;
	}
}
