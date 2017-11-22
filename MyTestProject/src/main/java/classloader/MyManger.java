package classloader;

/**
 * @ProjectName MyTest
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月21日 下午9:43:35 
 *
 * @UpdateTime 2017年11月21日 下午9:43:35 
 *
 * @Version 
 *
 * @desc  实现该接口的子类需要热加载
 *
 */
public class MyManger implements BaseManager {

	@Override
	public void logic() {
		System.out.println("Spring热加载看起来很有意思，我正在学习");
		System.out.println("我正在学习自定类加载器实现java的热加载，这个相当有意思");

	}

}
