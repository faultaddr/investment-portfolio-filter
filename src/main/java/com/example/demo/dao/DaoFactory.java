package com.example.demo.dao;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by panyunyi on 2017/5/3.
 */
public class DaoFactory<T> extends Dao<T> {
    @Autowired
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml").build();

    public DaoFactory() {
    }


    /*
* function：增加
* param：实体类对象
* */


    @Override
    public boolean save(T u) {
        String i;

        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            // 从会话工厂获取一个session
            t = session.beginTransaction();
            i = String.valueOf(session.save(u));
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();

        }


        //System.out.println(u.getCurrentLocation());
        return true;
    }

    /*
    * function：删除
    * param：实体类对象
    * */
    @Override
    public boolean delete(T u) {
        String info;

        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        Transaction t = null;
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session

        try {
            t = session.beginTransaction();

            session.delete(u);
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();

        }
        return true;

    }

    /*
    * function：更新
    * param：实体类对象
    * */
    @Override
    public boolean update(T u) {

        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        Transaction t = null;
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        try {
            t = session.beginTransaction();
            session.update(u);
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();
        }
        return true;
    }
    public boolean update(String sql){
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        Transaction t = null;
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        try {
            t = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            t.commit();
            return true;
        } catch (HibernateException e) {
            t.rollback();
        } finally{
            session.close();
        }
        return false;
    }

    @Override
    public List cursor(T u, String s, Class aclass) throws Exception {

        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();


        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery(s);
        List list = null;
        //if 做简单的查询
        //else 做统计
        if (aclass != null) {

                sqlQuery.addEntity(aclass);
                try {
                    //list=sqlQuery.getQueryReturns();

                    list = sqlQuery.getResultList();
                } catch (GenericJDBCException e) {
                    e.printStackTrace();
                    list = null;
                }
            }

       else {
//            int count = ((Number) sqlQuery.uniqueResult()).intValue();
//            list = new ArrayList<Integer>();
//            list.add(count);
            list = null;
            //sqlQuery.getQueryReturns();
            //sqlQuery.executeUpdate();

        }

        t.commit();
        session.close();
        return list;
    }
/*    public boolean generateSn(){
        Date date=Date.valueOf(LocalDate.now());
    }*/

}
