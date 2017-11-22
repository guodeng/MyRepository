package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 上午11:16:42 
 *
 * @UpdateTime 2017年11月22日 上午11:16:42 
 *
 * @Version 
 *
 * @desc  文件类处理工具
 *
 */
public abstract class FileUtils {
	
	/**
	 * 获取文件扩展名
	 * @param file File 文件
	 * @return 当文件无扩张名时返回<code>null</code>
	 * @see #getFileExtension(String)
	 */
	public static final String getFileExtension(File file) {
		if ( null == file )
			return null;
		return FileUtils.getFileExtension(file.getName());
	}

	/**
	 * 根据文件名称获取文件的扩展类型名
	 * @param fileName String 文件名称
	 * @return 当文件名中不含有扩展类型名称时返回<code>null</code>
	 * @see #getFileExtension(File)
	 */
	public static final String getFileExtension(String fileName) {
		if ( StringUtils.isEmpty(fileName) )
			return null;
		int index = fileName.lastIndexOf(".");
		if ( -1 == index )
			return null;
		return fileName.substring(index+1, fileName.length());
	}
	
	/**
	 * 拷贝文件内容, 支持目录存目录
	 * @param src File 源文件
	 * @param target File 目标文件
	 * @throws IOException 
	 * @see {@link #copy(InputStream, OutputStream)}
	 */
	public static final void copy(File src, File target) throws IOException {
		Assert.notNull(src);
		Assert.notNull(target);
		if ( !src.exists() ) {
			return;
		}
		if ( src.isDirectory() ) {
			if ( !target.isDirectory() ) {
				target.mkdirs();
			}
			File[] files = src.listFiles();
			for ( File file : files ) {
				File targetFile = new File(target.getAbsolutePath() + File.separator + file.getName());
				FileUtils.copy(file, targetFile);
			}
		} else {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(src);
				fos = new FileOutputStream(target);
				FileUtils.copy(fis, fos);
			} catch ( IOException e ) {
				throw e;
			} finally {
				if ( null != fis )
					try{fis.close();}catch(Exception e){}finally{fis=null;}
				if ( null != fos )
					try{fos.close();}catch(Exception e){}finally{fos=null;}
			}
		}
	}
	
	/**
	 * 拷贝文件内容, 拷贝完成后方法不会主动关闭流.
	 * @param src InputStream 输入流
	 * @param target OutputStream 输出流
	 * @throws IOException 
	 * @see {@link #copy(File, File)}
	 */
	public static final void copy(InputStream src, OutputStream target) throws IOException {
		Assert.notNull(src);
		Assert.notNull(target);
		byte[] bytes = new byte[1048576];
		int readed = src.read(bytes);
		while( -1 != readed ) {
			target.write(bytes, 0, readed);
			readed = src.read(bytes);
		}
	}
	
}
