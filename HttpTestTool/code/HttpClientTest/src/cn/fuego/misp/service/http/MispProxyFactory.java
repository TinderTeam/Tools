/**   
* @Title: MispProxyFactory.java 
* @Package cn.fuego.smart.home 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-13 上午12:34:29 
* @version V1.0   
*/ 
package cn.fuego.misp.service.http;

import java.lang.reflect.Proxy;

import org.apache.http.client.HttpClient;

 /** 
 * @ClassName: MispProxyFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 上午12:34:29 
 *  
 */
public class MispProxyFactory
{
	public static <T> T create(String base,Class<T> clazz, HttpClient httpClient)
	{
	 
	   
		 MispHttpClientProxy clientProxy = new MispHttpClientProxy(base,clazz,httpClient);
		 
		 Class<?>[] intfs = {clazz};
		 
		 return (T) Proxy.newProxyInstance(clazz.getClassLoader(), intfs, clientProxy);
	}
	

}
