package project.controller.tool;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.coobird.thumbnailator.Thumbnails;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import project.controller.MyController;
import project.util.FileUtil;
import project.util.JacksonJsonUtil;
import project.util.Uploader;


/**
 * @author http://www.javabysj.cn/ java毕业设计源码、论文学 习免费下载
 * 供大家下载 学习参考
 */
@Controller("toolFileController")
@RequestMapping(value = "/file")

public class FileController extends MyController {
	/**
	 * 上传文件
	 * @param request
	 * @param cmfile
	 * @param fsize
	 * @return
	 */
	@RequestMapping(value = "/upload")
	public ResponseEntity<String> upload(HttpServletRequest request,@RequestParam("cmfile") CommonsMultipartFile cmfile, Integer fsize) {
		Integer filesize = fsize;
		if(fsize != null) {
			filesize = filesize * 1024 * 1024;
		}else {
			fsize = 10;
			filesize = 1024*1024*10;
		}
		
		if(cmfile!=null && !cmfile.isEmpty()){
			try {
				if(cmfile.getSize() < filesize) {
					String filename = cmfile.getOriginalFilename();  
					String suffix = filename.substring(filename.lastIndexOf(".")); 
					String newPath="";
					Uploader up = new Uploader(request);
					String folder =  up.getFolder("upload");
					
					if ((".gif.png.jpg.jpeg.bmp".indexOf(suffix.toLowerCase()) >=0)) {
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}if ((".exe.bat".indexOf(suffix.toLowerCase()) >=0)) {
						return renderMsg(false,"格式不对");
					}else{
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}
					
					File file=new File(request.getServletContext().getRealPath("/")+newPath);
					if(!file.exists()){
						file.createNewFile();
					}
					cmfile.getFileItem().write(file);
					System.out.println("===============1========================");
					System.out.println(newPath);
					return renderData(true,"上传成功", new uploadFile(cmfile.getSize(),filename, newPath, suffix));
				} else {
					System.out.println("===================2====================");
					return renderMsg(false,"文件大小不能超过"+fsize+"M");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("======================3=================");
			}
		}
		System.out.println("=============================4==========");
		return renderMsg(false,"上传失败");
	}
	
	
	
	/**
	 * 上传文件,图片有缩略图
	 * @param request
	 * @param cmfile
	 * @param fsize
	 * @return
	 */
	@RequestMapping(value = "/uploadhavingThumbnails")
	public ResponseEntity<String> uploadhavingThumbnails(HttpServletRequest request,@RequestParam("cmfile") CommonsMultipartFile cmfile, Integer fsize) {
		Integer filesize = fsize;
		if(fsize != null) {
			filesize = filesize * 1024 * 1024;
		}else {
			fsize = 10;
			filesize = 1024*1024*10;
		}
		
		if(cmfile!=null && !cmfile.isEmpty()){
			try {
				if(cmfile.getSize() < filesize) {
					String filename = cmfile.getOriginalFilename();  
					String suffix = filename.substring(filename.lastIndexOf(".")); 
					String newPath="";
					Uploader up = new Uploader(request);
					String folder =  up.getFolder("upload");
					
					if ((".gif.png.jpg.jpeg.bmp".indexOf(suffix.toLowerCase()) >=0)) {  
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}else{
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}
					
					File file=new File(request.getServletContext().getRealPath("/")+newPath);
					if(!file.exists()){
						file.createNewFile();
					}
//					cmfile.getFileItem().write(file);
					//压缩
					InputStream iis = cmfile.getInputStream();
					BufferedImage bi = ImageIO.read(iis);
				    int width = bi.getWidth();
				    int height = bi.getHeight();
					Thumbnails.of(cmfile.getInputStream()).size(width,height).toFile(file);
					
					//保存缩略图
					String suoImgUrlBack="";
					if ((".gif.png.jpg.jpeg.bmp".indexOf(suffix.toLowerCase()) >=0)) {  
						suoImgUrlBack=up.getFolder("upload/thumbnail")+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
				        String suoImgUrl = request.getServletContext().getRealPath("/")+suoImgUrlBack;
						Thumbnails.of(cmfile.getInputStream()).size(86,92).toFile(suoImgUrl);
					}
					return renderData(true,"上传成功", new uploadFile(cmfile.getSize(),filename, newPath,suoImgUrlBack, suffix));
				} else {
					return renderMsg(false,"文件大小不能超过"+fsize+"M");
				}
			} catch (Exception e) {
				return renderMsg(false,"上传超时，请重新上传");
			}
		}
		
		return renderMsg(false,"上传失败");
	}
	
	
	
	
	/**
	 * fck编辑器上传图片
	 * @param request
	 * @param cmfile
	 * @param fsize
	 * @param CKEditor
	 * @param CKEditorFuncNum
	 * @return
	 */
	@RequestMapping(value = "/fckupload")
	public ResponseEntity<String> fckupload(HttpServletRequest request,@RequestParam("upload") CommonsMultipartFile cmfile, Integer fsize, String CKEditor, Integer CKEditorFuncNum) {
		Integer filesize = fsize;
		if(fsize != null) {
			filesize = filesize * 1024 * 1024;
		}else {
			fsize = 10;
			filesize = 1024*1024*10;
		}
		
		StringBuffer sb= new StringBuffer();
		
		if(cmfile!=null && !cmfile.isEmpty()){
			try {
				if(cmfile.getSize() < filesize) {
					String filename = cmfile.getOriginalFilename();  
					String suffix = filename.substring(filename.lastIndexOf(".")); 
					String newPath="";
					Uploader up = new Uploader(request);
					String folder =  up.getFolder("upload");
					
					if ((".gif.png.jpg.jpeg.bmp".indexOf(suffix.toLowerCase()) >=0)) {  
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}else{
						newPath=folder+"/"+FileUtil.newFileName(cmfile.getOriginalFilename());
					}
					
					File file=new File(request.getServletContext().getRealPath("/")+newPath);
					if(!file.exists()){
						file.createNewFile();
					}
//					String path = getWebSite(request)+"/"+ newPath;
					String path = request.getContextPath()+"/"+ newPath;
					//cmfile.getFileItem().write(file);
					InputStream iis = cmfile.getInputStream();
					BufferedImage bi = ImageIO.read(iis);
				    int width = bi.getWidth();
				    int height = bi.getHeight();
					Thumbnails.of(cmfile.getInputStream()).size(width,height).toFile(file);
					sb.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '" +path + "' , '" + "' , '" + ""+ "');</script>"); 
				} else {
				}
			} catch (Exception e) {
				sb.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '' , '" + "' , '" + "上传失败"+ "');</script>");
			}
		}
		
		ResponseEntity<String> responseEntity =new ResponseEntity<String>(sb.toString(),initHttpHeaders(),HttpStatus.OK);     
        return responseEntity;  
	}
	
	@RequestMapping(value = "/ueditorUpload")
	public ResponseEntity<String> ueditorUpload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
	    try {
	    	request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			Uploader up = new Uploader(request);
			up.setSavePath("upload");
			String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
			up.setAllowFiles(fileType);
			up.setMaxSize(10000); //单位KB
			up.upload();
			
			map.put("original", up.getOriginalName());
			map.put("url", up.getUrl());
			map.put("title", up.getTitle());
			map.put("state", up.getState());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		ResponseEntity<String> responseEntity =new ResponseEntity<String>(JacksonJsonUtil.toJson(map),initHttpHeaders(),HttpStatus.OK);     
	    return responseEntity;
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws IOException
	 */
	 @RequestMapping(value = "/download")  
	 public void download(HttpServletRequest request,HttpServletResponse response,String fileName) throws IOException { 
		 String nowPath = request.getSession().getServletContext().getRealPath("/")+File.separator+"upload"+File.separator+"pdf"+File.separator+fileName;
	     File file = new File(nowPath); 
         response.reset(); 
         response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
         response.addHeader("Content-Length", "" + file.length()); 
	     try{ 
	         //以流的形式下载文件 
	         InputStream fis = new BufferedInputStream(new FileInputStream(nowPath)); 
	         byte[] buffer = new byte[fis.available()]; 
	         fis.read(buffer); 
	         fis.close(); 
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
	         toClient.write(buffer); 
	         toClient.flush(); 
	         toClient.close(); 
	     }catch(Exception e){ 
	         e.printStackTrace(); 
	     } 
	 } 
	
	
	 class uploadFile {
			Long filesize;
			String filename;
			String suffix;
			String filepath;
			String suoImgUrl;
				
			public uploadFile(Long filesize, String filename,String filepath , String suffix) {
				super();
				this.filesize = filesize;
				this.filename = filename;
				this.filepath=filepath;
				this.suffix = suffix;
			}
			
			public uploadFile(Long filesize, String filename,String filepath,String suoImgUrl, String suffix) {
				super();
				this.filesize = filesize;
				this.filename = filename;
				this.filepath=filepath;
				this.suffix = suffix;
				this.suoImgUrl = suoImgUrl;
			}
			
			public String getFilepath() {
				return filepath;
			}
			public void setFilepath(String filepath) {
				this.filepath = filepath;
			}
			public Long getFilesize() {
				return filesize;
			}
			public void setFilesize(Long filesize) {
				this.filesize = filesize;
			}
			public String getFilename() {
				return filename;
			}
			public void setFilename(String filename) {
				this.filename = filename;
			}
			public String getSuffix() {
				return suffix;
			}
			public void setSuffix(String suffix) {
				this.suffix = suffix;
			}

			public String getSuoImgUrl() {
				return suoImgUrl;
			}

			public void setSuoImgUrl(String suoImgUrl) {
				this.suoImgUrl = suoImgUrl;
			}
			
		}
}
