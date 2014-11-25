/**   
* @Title: UserManageRestTest.java 
* @Package cn.fuego.eshoping.webservice.up.rest 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-25 下午2:45:50 
* @version V1.0   
*/ 
package cn.fuego.eshoping.webservice.up.rest;

import static org.junit.Assert.fail;

import org.junit.Test;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.eshoping.webservice.up.model.LoginReq;
import cn.fuego.eshoping.webservice.up.model.LoginRsp;

 /** 
 * @ClassName: UserManageRestTest 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-25 下午2:45:50 
 *  
 */
public class UserManageRestTest
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	/**
	 * Test method for {@link cn.fuego.eshoping.webservice.up.rest.UserManageRest#login(cn.fuego.eshoping.webservice.up.model.LoginReq)}.
	 */
	@Test
	public void testLogin()
	{
		LoginReq req = new LoginReq(); 
		
		LoginRsp rsp = WebServiceContext.getInstance().getUserManageRest().login(req);
		
		log.info("the request is " + req);
		log.info("the response is " + rsp);
	}

	/**
	 * Test method for {@link cn.fuego.eshoping.webservice.up.rest.UserManageRest#logout(cn.fuego.eshoping.webservice.up.model.LoginReq)}.
	 */
	@Test
	public void testLogout()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cn.fuego.eshoping.webservice.up.rest.UserManageRest#modifyPassword(cn.fuego.eshoping.webservice.up.model.ModifyPwdReq)}.
	 */
	@Test
	public void testModifyPassword()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cn.fuego.eshoping.webservice.up.rest.UserManageRest#registerMemeber(cn.fuego.eshoping.webservice.up.model.RegisterReq)}.
	 */
	@Test
	public void testRegisterMemeber()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link cn.fuego.eshoping.webservice.up.rest.UserManageRest#getCityList(cn.fuego.eshoping.webservice.up.model.GetCityListReq)}.
	 */
	@Test
	public void testGetCityList()
	{
		fail("Not yet implemented");
	}

}
