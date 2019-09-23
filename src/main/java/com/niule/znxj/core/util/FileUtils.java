package com.niule.znxj.core.util;

import com.niule.znxj.core.common.Resources;
import com.niule.znxj.core.entity.JSONResult;
import com.niule.znxj.core.entity.Result;
import com.niule.znxj.web.security.Resource;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.alibaba.druid.util.StringUtils.isEmpty;

/**
 * 文件处理共通类
 * 
 * @author guods
 *
 */
public class FileUtils {

	/**
	 * 将Base64转化成图片文件
	 * 
	 * @param val
	 * @param imgname
	 * @throws IOException
	 */
	public static Result saveImageFile(String val, String imgname) throws IOException {
		if (isEmpty(val)) {
			return new JSONResult<>("图片上传失败");
		}
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] b = decoder.decodeBuffer(val);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		String uploadFilePath =  Resources.ApplicationResources.getString("imagefile.path") + imgname;
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(uploadFilePath);
		out.write(b);
		out.flush();
		out.close();
		Runtime.getRuntime().exec("chmod 644 -R " + uploadFilePath);
		return new JSONResult<>(imgname,"");
	}
	
	/**
	 * 删除文件
	 * @param name
	 * @throws IOException
	 */
	public static void deleteImageFile(String name) throws IOException {
		File file = new File(Resources.ApplicationResources.getString("imagefile.path") + name );
		if(file.exists()){
			file.delete();
		}
	}
}
