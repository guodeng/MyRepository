package classloader;

/**
 * @ProjectName MyTest
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月21日 下午10:32:58 
 *
 * @UpdateTime 2017年11月21日 下午10:32:58 
 *
 * @Version 
 *
 * @desc 后台启动一个线程不断的加载修改的类
 *
 */
public class MsgHandler implements Runnable {

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
		
		while (true) {
			try {
				manager.logic();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
