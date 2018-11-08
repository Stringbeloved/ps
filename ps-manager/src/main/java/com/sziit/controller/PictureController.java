package com.sziit.controller;

import com.sziit.utils.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/*
 *  @项目名：  ps-parent
 *  @包名：    com.sziit.controller
 *  @文件名:   PictureController
 *  @创建者:   dzy
 *  @创建时间:  2018/10/15 10:43
 *  @描述：    用于图片上传
 */
@RestController
public class PictureController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;



	//商品的图片上传

	@RequestMapping("/rest/pic/upload")
	public   Map<String, Object> upload(@RequestParam(value="uploadFile") MultipartFile file) throws Exception{

		//获取文件后缀
		String subfix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

		//资源的路径地址
		//String path = System.getProperty("user.dir")+"/src/main/resources/";
		String path = System.getProperty("user.dir")+"\\ps-manager"+"\\src\\main\\resources\\";

		//执行文件上传
		String[] uploadinfos = UploadUtil.upload(path+"tracker.conf", file.getBytes(), subfix);

		/*for (String string : uploadinfos) {
			//System.out.println(string);
		}*/
		/****
		 * error   	0标识成功，1失败
		 * url		成功后文件访问地址
		 * height	高度
		 * width	宽度
		 *
		 *   group1
		 M00/00/00/wKjjg1u7fxeABGSgAABff0_4hIY949.jpg
		 http://192.168.227.131/group1/M00/00/00/wKjjg1u7fxeABGSgAABff0_4hIY949.jpg
		 */
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("error", 0);
		//map.put("url", "http://192.168.227.131/"+uploadinfos[0]+"/"+uploadinfos[1]); //这里就是图片的路径地址，其实就是ip +返回值
		//map.put("url", "http://image.taotao.com/"+uploadinfos[0]+"/"+uploadinfos[1]); //这里就是图片的路径地址，其实就是ip +返回值
		map.put("url", IMAGE_SERVER_URL+uploadinfos[0]+"/"+uploadinfos[1]);
		map.put("height", 100);
		map.put("width", 100);

		return map;
	}


	//宜立方版
//	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String uploadFile(MultipartFile uploadFile){
//		try {
//			//资源的路径地址
//			String path = System.getProperty("user.dir")+"\\ps-manager"+"\\src\\main\\resources\\";
//			//把图片上传到图片服务器
//			//System.out.println(path);
//			FastDFSClient fastDFSClient=new FastDFSClient(path+"client.conf");
//			//取文件扩展名
//			String originalFilename = uploadFile.getOriginalFilename();
//			String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
//			//得到一个图片的地址和文件名
//			String url=fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
//			//补充为完整的url
//			url=IMAGE_SERVER_URL+url;
//			//封装到map中返回
//			Map<String,Object> result=new HashMap<String,Object>();
//			result.put("error", 0);
//			result.put("url", url);
//			return JsonUtils.objectToJson(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Map<String,Object> result=new HashMap<String,Object>();
//			result.put("error", 1);
//			result.put("message", "图片上传失败");
//			return JsonUtils.objectToJson(result);
//		}
//
//	}
}
