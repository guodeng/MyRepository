package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ProjectName classLoaderTest
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月21日 上午9:58:54 
 *
 * @UpdateTime 2017年11月21日 上午9:58:54 
 *
 * @Version 1.0
 *
 * @desc  自定义类加载器
 *
 */
public class ClassLoaderTest extends ClassLoader{
	
	//定义读取文件的路径
	private String classPath;
	
	public ClassLoaderTest(String classPath){
		super(ClassLoader.getSystemClassLoader());
		this.classPath = classPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		byte[] b = classLoderDate(name);
		
		return this.defineClass(name, b, 0, b.length);
	}

	/**
	 * 读取需要加载类的class文件的字节码文件
	 * @param name
	 * @return
	 */
	private byte[] classLoderDate(String name) {
		
		name.replace(".", "//");
		
		try {
			
			FileInputStream fis = new FileInputStream(new File(classPath + name+".class"));
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int b = 0;
			
			while((b = fis.read()) != -1){
				baos.write(b);
			}
			baos.close();
			fis.close();
			
			
			baos.toByteArray();
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

}
