package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Products;
import entity.Users;

public class DaoProduct {
	
	public static List<Products> searchProducts(SessionFactory factory, String keyword){
		Session session = factory.getCurrentSession();
		String hql = "FROM Products WHERE ProductCode LIKE '%' || :keyword || '%' "
				+ "OR ProductName LIKE '%' || :keyword || '%' ";
		Query query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
		List<Products> list = query.list();
		return list;
	}
	
	public static List<Products> readAllProducts(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM Products";
		Query query = session.createQuery(hql);
		List<Products> list = query.list();
		return list;
	}
	
	public static List<Products> readAllProductsActive(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM Products WHERE Active = 1";
		Query query = session.createQuery(hql);
		List<Products> list = query.list();
		return list;
	}
	
	public static List<Products> searchProductsActive(SessionFactory factory, String keyword){
		Session session = factory.getCurrentSession();
		String hql = "FROM Products WHERE Active = 1 AND (ProductCode LIKE '%' || :keyword || '%' "
				+ "OR ProductName LIKE '%' || :keyword || '%')";
		Query query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
		List<Products> list = query.list();
		return list;
	}
	
	public static Products readOneProduct(SessionFactory factory, String productid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Products WHERE ProductId = :productid";
		Query query = session.createQuery(hql);
		query.setParameter("productid", Integer.valueOf(productid));
		Products product = (Products) query.uniqueResult();
		return product;
	}

	public static Boolean insertProduct(SessionFactory factory, Products product) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(product);
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
	
	public static Boolean updateProduct(SessionFactory factory, Products product) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
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
	
	public static Boolean deleteProduct(SessionFactory factory, String productid) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Products product = (Products) session.load(Products.class, Integer.valueOf(productid));
			session.delete(product);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			resultCode = false;
		} finally {
			session.close();
		}
		return resultCode;
	}
}
