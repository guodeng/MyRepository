package classloader;

/**
 * @ProjectName MyTest
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月21日 下午9:45:35 
 *
 * @UpdateTime 2017年11月21日 下午9:45:35 
 *
 * @Version 
 *
 * @desc 封装加载类的信息
 *
 */
public class LoderInfo {
	
	private ClassLoaderTest classLoaderTest;
	
	//记录类加载器加载的时间
	private long loadTime;
	
	private BaseManager baseManager;

	
	
	public LoderInfo(ClassLoaderTest classLoaderTest, long loadTime) {
		super();
		this.classLoaderTest = classLoaderTest;
		this.loadTime = loadTime;
	}

	
	
	public ClassLoaderTest getClassLoaderTest() {
		return classLoaderTest;
	}

	public void setClassLoaderTest(ClassLoaderTest classLoaderTest) {
		this.classLoaderTest = classLoaderTest;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}

	public BaseManager getBaseManager() {
		return baseManager;
	}

	public void setBaseManager(BaseManager baseManager) {
		this.baseManager = baseManager;
	}
	
	

}
