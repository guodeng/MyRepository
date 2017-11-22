/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @ProjectName MyTestProject
 *
 * @author GuoDeng
 *
 * @CreateTime 2017年11月22日 上午11:12:31 
 *
 * @UpdateTime 2017年11月22日 上午11:12:31 
 *
 * @Version 
 *
 * @desc  集合类处理工具类
 *
 */
public class CollectionUtils {
	
	/**
	 * 将字符串按照spliter分割后将不为<code>null</code>的值存放在list中
	 * @param str String 需要处理的字符串
	 * @param spliter String 分割字符
	 * @return List<String> 当str或者spliter其中之一为<code>null</code>时, 返回一个空的<code>ArrayList</code>
	 */
	public static final List<String> str2List(String str, String spliter) {
		if ( StringUtils.isEmpty(str) || StringUtils.isEmpty(spliter) )
			return new ArrayList<String>();
		String [] strs = str.split(spliter);
		return Arrays.asList(strs);
	}
	
	/**
	 * 数组转换为List
	 * @param array
	 * @return
	 */
	public static final List<Object> arrayToList(Object[] array) {
		if ( null == array )
			return new ArrayList<Object>();
		return Arrays.asList(array);
	}
}
