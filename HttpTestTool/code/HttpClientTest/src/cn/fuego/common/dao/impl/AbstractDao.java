/**   
* @Title: AbstractDao.java 
* @Package cn.fuego.misp.dao 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午03:34:48 
* @version V1.0   
*/ 
package cn.fuego.common.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.corba.se.spi.copyobject.ObjectCopier;

import cn.fuego.common.contanst.ConditionTypeEnum;
import cn.fuego.common.dao.Dao;
import cn.fuego.common.dao.QueryCondition;
import cn.fuego.common.dao.hibernate.util.HibernateUtil;
import cn.fuego.common.domain.PersistenceObject;
import cn.fuego.common.util.meta.ReflectionUtil;

/** 
 * @ClassName: AbstractDao 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午03:34:48 
 *  
 */

public abstract  class AbstractDao extends AbstractViewDao implements Dao
{

	private Log log = LogFactory.getLog(AbstractDao.class);

	public void create(PersistenceObject object)
	{
		log.info("the object class is " + getFeaturedClass());
		if(null == object)
		{
			log.warn("the object is null");
			return;
		}
		log.info("the object is "+object);
		try
		{
			HibernateUtil.add(object);
		} catch (RuntimeException re)
		{
			log.error("database error",re);
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		
		log.info("the create success.");

	}

	public void update(PersistenceObject object)
	{
		log.info("the object class is " + getFeaturedClass());
		if(null == object)
		{
			log.warn("the object is null");
			return;
		}
		try
		{
			HibernateUtil.update(object);
		} catch (RuntimeException re)
		{
			log.error("update error",re);
			throw re;

		} finally
		{
			HibernateUtil.closeSession();
		}
		log.info("the update success.");
	}
	public void delete(List<QueryCondition> conditionList)
	{
		log.info("the object class is " + getFeaturedClass()+"the condition is " + conditionList);

        Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Criteria c = HibernateUtil.getCriteriaByCondition(this.getFeaturedClass(),conditionList, session);
			List<PersistenceObject> objectList =c.list();
			log.info("delete object is " + objectList.toString()); 
			for(PersistenceObject object : objectList)
			{
				session.delete(object);
			}


			tx.commit();
		} catch (RuntimeException re)
		{
			log.error("delete error",re);
			throw re;

		} finally
		{
			if (session != null)
			{
				session.close();
			}
		}
		log.info("the delete success.");
	}

	public void delete(QueryCondition condition)
	{
		log.info("the object class is " + getFeaturedClass()+"the condition is " + condition);
  
		List<QueryCondition> conditionList = new ArrayList<QueryCondition>();
		if(null != condition)
		{
			conditionList.add(condition);
		}
		this.delete(conditionList);
	}

}
