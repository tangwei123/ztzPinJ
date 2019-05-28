package tw.com.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class uploadFile {

	public DiskFileItemFactory factory = null;
	public File tmpFilePath = null;
	public File saveFilePath = null;
	public int sizeThreshold = 0;//上传文件的大小，超出部分写入临时文件，未超出部分放入内存
	public String tmpPath = "";
	public String savePath = "";
	ServletFileUpload servletfileupload = null;
	public long totalSize = 0;//上传文件总大小
	public long fileSize = 0;//单个上传文件大小
	
	
	public Map<String, String> doUpload(HttpServletRequest req) {
		HashMap<String, String> mapData = new HashMap<String, String>();
		factory = new DiskFileItemFactory();
		factory.setSizeThreshold(sizeThreshold);//设置上传文件的大小,如果超过该部分，就会写入临时文件，没超过的部分写入内存
		tmpFilePath = new File(tmpPath);
		if(!tmpFilePath.exists()) {//如果临时文件不存在，则尝试创建！
			tmpFilePath.mkdir();
		}
		saveFilePath = new File(savePath);
		if(!saveFilePath.exists()) {
			saveFilePath.mkdir();//如果文件存储文件夹不存在，则尝试创建！
		}
		factory.setRepository(tmpFilePath);
		servletfileupload = new ServletFileUpload(factory);
		servletfileupload.setProgressListener(new ProgressListener() {
			@Override
			public void update(long pBytesRead, long pContentLength, int pItems) {
				System.out.println("文件总大小："+pContentLength+",已上传文件大小："+pBytesRead);
				
			}
		});
		servletfileupload.setSizeMax(totalSize);
		servletfileupload.setFileSizeMax(fileSize);
		servletfileupload.setHeaderEncoding("utf-8");

		try {
			String uploadFileString = "";
			List<FileItem> list = servletfileupload.parseRequest(req);
			for(FileItem fileitem:list) {
				if(fileitem.isFormField()) {//这儿为普通字段
					String itemName = fileitem.getFieldName();
					String itemValue = "";
					try {
						itemValue = fileitem.getString("utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mapData.put(itemName, itemValue);
				}else {//图片信息在这儿
					String filenameString = fileitem.getName();
//					System.out.println(filenameString);
					if(filenameString.equals("")) {
						continue;
					}
					String[] extArr = filenameString.split("\\.");
					String extensionString = extArr[1];
					String filname = UUID.randomUUID().toString() +"."+ extensionString;
					savePath = savePath + "/" + filname;
					try {
						InputStream in = fileitem.getInputStream();
						OutputStream out = new FileOutputStream(savePath);
						byte[] buffer = new byte[1024];
						int len = 0;
						while((len =  in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						uploadFileString = uploadFileString + savePath + ";";
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(!uploadFileString.equals("")) {
				mapData.put("indexImg", uploadFileString);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapData;
	}
	
}
