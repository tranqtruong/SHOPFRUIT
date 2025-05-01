package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Orders;

public class DaoOrder {
	
	public static List<Orders> layDonChoXacNhan(SessionFactory factory, Integer userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.user_order.UserId = :userid AND ( o.Status = 0 OR o.Status = 10 )";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> layDonChoLayHang(SessionFactory factory, Integer userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.user_order.UserId = :userid AND ( o.Status = 3 OR o.Status = 13 )";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> layDonDangGiao(SessionFactory factory, Integer userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.user_order.UserId = :userid AND o.Status = 4";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> layDonDaGiao(SessionFactory factory, Integer userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.user_order.UserId = :userid AND o.Status = 5";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> layDonDaHuy(SessionFactory factory, Integer userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.user_order.UserId = :userid AND o.Status = 2";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> searchOrders(SessionFactory factory, String keyword){
		Integer id = null;
		try {
			id = Integer.valueOf(keyword);
		} catch (Exception e) {
			id = null;
		}
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders o WHERE o.id = :keyword1 "
				+ "OR o.user_order.Username LIKE '%' || :keyword || '%' "
				+ "OR o.FullName LIKE '%' || :keyword || '%'";
		Query query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
		query.setParameter("keyword1", id);
		List<Orders> list = query.list();
		return list;
	}
	
	public static Integer getOrderId(SessionFactory factory, Integer userid) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT MAX(o.id) FROM Orders o WHERE o.user_order.UserId = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		Integer orderid = (Integer) query.uniqueResult();
		return orderid;
	}
	
	public static List<Orders> readAllOrders(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders";
		Query query = session.createQuery(hql);
		List<Orders> list = query.list();
		return list;
	}
	
	public static Orders getOrder(SessionFactory factory, Integer orderid) {
		Session session = factory.getCurrentSession();
		Orders order = (Orders) session.get(Orders.class, orderid);
		session.refresh(order);
		order = (Orders) session.get(Orders.class, orderid);
		//session.close();
		return order;
	}
	
	public static Orders getOrder2(SessionFactory factory, Integer orderid) {
		Session session = factory.openSession();
		Orders order = (Orders) session.get(Orders.class, orderid);
		session.refresh(order);
		order = (Orders) session.get(Orders.class, orderid);
		session.close();
		return order;
	}
	
	
	public static Boolean insertOrder(SessionFactory factory, Orders order) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			resultCode = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultCode;
	}
	
	public static Boolean updateOrder(SessionFactory factory, Orders order) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(order);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			resultCode = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultCode;
	}
	
	public static Boolean deleteOrder(SessionFactory factory, Orders order) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(order);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			resultCode = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultCode;
	}
}
