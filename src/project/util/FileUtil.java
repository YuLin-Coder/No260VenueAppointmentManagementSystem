
package project.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.*;

/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学习免费 下载
 * 供大家下 载学习参考
 */
public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private FileUtil() {
	}
	/**
	 * 文件上传 
	 * @param request
	 * @param response
	 * @param newFile 自行组装新文件地址
	 * @return Boolean 返回是否上传成功
	 * @throws Exception
	 */
	public static Boolean upload(HttpServletRequest request,String filepath, String newFile) throws Exception {   
       try {
    	   // 转型为MultipartHttpRequest：   
           MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
           // 获得文件：   
           MultipartFile file = multipartRequest.getFile(filepath);   
           if(!file.isEmpty()){
//        	   String fileName=  file.getOriginalFilename();
//        	   String ext= fileName.substring(fileName.lastIndexOf("."));
//        	   File source = new File(newFile+ext);   
        	   File source = new File(newFileName(newFile));   
               file.transferTo(source);
           }
           return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
         
    }
	public static byte[] readFile(File file) {
		if (file.isDirectory()) {
			return null;
		}
		try {
			return FileCopyUtils.copyToByteArray(file);
		} catch (FileNotFoundException e) {
			logger.error("FileUtil readFile FileNotFoundException "+file.getName(), e);
		} catch (IOException e) {
			logger.error("FileUtil readFile IOException "+file.getName(), e);
		}
		return null;
	}

	public static void copytoFile(byte[] in, File out) {
		if (in == null) {
			return;
		}
		try {
			FileCopyUtils.copy(in, out);
		} catch (IOException e) {
			logger.error("FileUtil readFile IOException "+out.getName(), e);
		}
	}
	/**
	 * 文件重命名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 根据日期重命名后的文件名
	 */
	public static String newFileName(String fileName) {
		Assert.notNull(fileName);
		String name =DateUtil.getNow().getTime()+""+(int)(Math.random()*99);
		if (fileName.indexOf(".") == -1) {
			return name;
		} else {
			return name+ fileName.substring(fileName.lastIndexOf("."));
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<1;i++){
			System.out.println(FileUtil.newFileName("aaa.jsp"));
		}
	}
}
