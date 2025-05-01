package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Users;

@Transactional
public class DaoUser {
	
	public static Users checkEmailExists(SessionFactory factory, String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		Users user = (Users) query.uniqueResult();
		
		return user;
	}
	
	public static Users checkEmailExists2(SessionFactory factory, String email, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Email = :email AND Username != :username";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		
		return user;
	}
	
	public static Users checkPhoneExists(SessionFactory factory, String phone, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Phone = :phone AND Username != :username";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone);
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		
		return user;
	}
	
	public static Users verifyUser(SessionFactory factory, String username, String password) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE (Username = :username OR Email = :username OR Phone = :username) AND Password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<Users> list = query.list();
		if((list==null) || (list.size()<=0)) {
			return null;
		}
		Users user = list.get(0);
		
		return user;
	}
	
	public static List<Users> readAllUsers(SessionFactory factory){
		Session session = factory.getCurrentSession();
		String hql = "FROM Users";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		return list;
	}
	
	public static List<Users> searchUsers(SessionFactory factory, String keyword){
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Username LIKE '%' || :keyword || '%' "
				+ "OR FullName LIKE '%' || :keyword || '%' "
				+ "OR Email LIKE '%' || :keyword || '%' "
				+ "OR Phone LIKE '%' || :keyword || '%' ";
		Query query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
		List<Users> list = query.list();
		return list;
	}
	
	public static Users readOneUsers(SessionFactory factory, String userid){
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE UserId = :userid";
		Query query = session.createQuery(hql);
		query.setParameter("userid", Integer.valueOf(userid));
		Users user = (Users) query.uniqueResult();
		return user;
	}
	
	public static Users getUser(SessionFactory factory, String username){
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		return user;
	}
	
	public static Users getUserbyPhone(SessionFactory factory, String phone){
		Session session = factory.getCurrentSession();
		String hql = "FROM Users WHERE Phone = :phone";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone);
		Users user = (Users) query.uniqueResult();
		return user;
	}
	
	
	public static Boolean insertUser(Users user, SessionFactory factory) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
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
	
	public static Boolean updateUser(Users user, SessionFactory factory) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
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
	
	public static Boolean deleteUser(SessionFactory factory, String userid) {
		Boolean resultCode = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Users user = (Users) session.load(Users.class, Integer.valueOf(userid));
			session.delete(user);
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
