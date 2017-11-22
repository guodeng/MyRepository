package classloader;

/**
 * @ProjectName MyTest
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月21日 下午10:36:00 
 *
 * @UpdateTime 2017年11月21日 下午10:36:00 
 *
 * @Version 
 *
 * @desc 测试类  测试自定义类加载器
 *
 */
public class Test {
	public static void main(String[] args) {
		new Thread(new MsgHandler()).start();
	}
}
