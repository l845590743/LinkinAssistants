package com.project.linkinassistant.gzip;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipException;

public class Test {
	public static void main(String[] args) {
		//压缩测试
		//testZip();
		
		//解压测试
		//testUnzip();
		// 压缩文件和原始文件放在根目录下即可
		GzipUtil.zip(new File("address.db"), new File("abc.zip"));
		try {
			GzipUtil.upzip(new File("address360.zip"), new File("360.db"));
		} catch (ZipException e) {
			e.printStackTrace();
			//android里可以在这里弹出Toast
			System.out.println("解压失败！");
		}
	}
	
	//解压测试
	private static void testUnzip() {
		GZIPInputStream gis = null;
		OutputStream os = null;
		try {
			InputStream in = new FileInputStream(new File("address360.zip"));
			//压缩输入流，用来读取压缩包文件流
			gis = new GZIPInputStream(in);
			
			//把压缩流写到普通文件里
			os = new FileOutputStream(new File("address360.db"));
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = gis.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(gis);
			close(os);
		}

	}

	private static void testZip() {
		InputStream in = null;
		GZIPOutputStream gos = null;
		try {
			//得到文件输入流
			in = new FileInputStream(new File("address.db"));
			
			//要将原始文件压缩到那个文件里
			OutputStream out = new FileOutputStream(new File("address.zip"));
			//压缩
			gos = new GZIPOutputStream(out);
			//将输入流写到压缩文件里
			int len = -1;
			byte[] buffer = new byte[1024]; 
			while ((len = in.read(buffer)) != -1) {
				gos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(in);
			close(gos);
		}
	}

	//关闭流
	public static void close(Closeable c){
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
