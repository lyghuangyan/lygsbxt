package com.tom.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tom.util.ApiResponse;
import com.tom.util.RotateImage;

/**
 * 上传文件接口
 * @author tom
 * @since 2018-10-21 12:54:12
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
	
    /**
     * 上传单文件
     * @param file
     * @param request
     * @return
     */
	@RequestMapping("/uploadFile")
	public String uploadFile(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		logger.info("uploadFile start...");
		String filesUrl = "";
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID().toString()+suffixName;
		String filePath = uploadFolder;
		String realPath = filePath + newFileName;
		File dest = new File(realPath);
		File directory = new File(uploadFolder);
		if(!directory.isDirectory()) {
			directory.mkdir();
		}
		try {
			file.transferTo(dest);
			filesUrl = staticAccessPath.substring(0, staticAccessPath.length()-2)
					+newFileName;
			//处理ios图片旋转
			RotateImage.fixPicture(realPath);
			//success(staticAccessPath.substring(0, staticAccessPath.length()-2)
			//		+newFileName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			//successFailCode(false,null,"500","上传失败");
		}
		return filesUrl;
	}
	
	/**
	 * 上传多文件
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadFiles")
	public List<String> uploadFiles(@RequestParam("file") MultipartFile[] files) {
		List<String> filesUrl = new ArrayList<String>();
		for(MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString()+suffixName;
			String filePath = uploadFolder;
			String realPath = filePath + newFileName;
			File dest = new File(realPath);
			File directory = new File(uploadFolder);
			if(!directory.isDirectory()) {
				directory.mkdir();
			}
			try {
				file.transferTo(dest);
				filesUrl.add(staticAccessPath.substring(0, staticAccessPath.length()-2)
						+newFileName);
				//处理ios图片旋转
				RotateImage.fixPicture(realPath);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		//success(filesUrl);
		return filesUrl;
	}
	
}
