package classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName MyTest
 * 
 * @author GuoDeng
 * 
 * @CreateTime 2017年11月21日 下午9:57:31
 * 
 * @UpdateTime 2017年11月21日 下午9:57:31
 * 
 * @Version
 * 
 * @desc 加载manager的工厂
 * 
 */
public class ManagerFactory {

	// 记录热加载类的加载信息
	private static Map<String, LoderInfo> loderMap = new HashMap<String, LoderInfo>();

	// 加载类的路经  
	private static final String CLASS_PATH = "E:/MyEclipse/Workspaces/MyTestProject/src/main/java/";

	// 加载类的全路径
	public static final String MY_MANAGER = "classloader.MyManger";

	/**
	 * 通过文件名称获取需要加载的类信息
	 * 
	 * @param className
	 * @return
	 */
	public static BaseManager getManager(String className) {

		//本地修改的.class文件
		File file = new File(CLASS_PATH + className.replaceAll("\\.", "/")
				+ ".class");

		//获取文件最后一次加载的时间戳
		long lastModifiled = file.lastModified();

		// 如果这个类没有被加载过就加入该类的信息
		if (loderMap.get(className) == null) {
			
			load(className, lastModifiled);
			
		}// 如果该类加载过，并且被修改过就重新加载
		
		else if (loderMap.get(className).getLoadTime() != lastModifiled) {
			
			load(className, lastModifiled);
			
		}

		return loderMap.get(className).getBaseManager();
	}

	/**
	 * 
	 * @param className
	 * @param lastModifiled
	 */
	private static void load(String className, long lastModifiled) {
		// TODO 自动生成的方法存根
		ClassLoaderTest classLoaderTest = new ClassLoaderTest(CLASS_PATH);

		Class<?> classLoad = null;

		try {
			classLoad = classLoaderTest.loadClass(className);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		BaseManager baseManager = newInstance(classLoad);

		LoderInfo loderInfo = new LoderInfo(classLoaderTest, lastModifiled);

		loderInfo.setBaseManager(baseManager);

		loderMap.put(className, loderInfo);

	}

	// 以反射的方式生成BaseManager的子类对象
	private static BaseManager newInstance(Class<?> classLoad) {
		try {
			return (BaseManager) classLoad.getConstructor(new Class[] {})
					.newInstance(new Object[] {});
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

}
