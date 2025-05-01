package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Carts;

public class DaoCarts {
	
	public static Carts getCart(SessionFactory factory, Integer productid, Integer userid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Carts WHERE UserId = :userid AND ProductId = :productid AND Status = 1";
		Query query = session.createQuery(hql);
		query.setParameter("userid", userid);
		query.setParameter("productid", productid);
		Carts cart = (Carts) query.uniqueResult();
		return cart;
	}
	
	public static Carts getCart(SessionFactory factory, Integer cartid) {
		Session session = factory.getCurrentSession();
		Carts cart = (Carts) session.get(Carts.class, cartid);
		return cart;
	}
	
	public static Long countCart(SessionFactory factory, String username) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(c.product.ProductId) FROM Carts c WHERE c.user_cart.Username = :username AND c.Status = 1 GROUP BY c.user_cart.Username";
		
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	public static Boolean insertCart(SessionFactory factory, Carts cart) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cart);
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
	
	public static Boolean updateCart(SessionFactory factory, Carts cart) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(cart);
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
	
	public static Boolean deleteCart(SessionFactory factory, Carts cart) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cart);
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
