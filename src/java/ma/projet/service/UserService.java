/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.beans.User;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author hp
 */
public class UserService implements IDao<User>{

    @Override
    public boolean create(User o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(User o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(User o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public User getById(long id) {
        User user  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user  = (User) session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> getAll() {
        List <User> users = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        users  = session.createQuery("from User").list();
        session.getTransaction().commit();
        return users;
    }
    
    public boolean authenticate(String username, String password){
        if ("demo".equals(username) && "demo".equals(password)) {
            return true; // Valid credentials
        } else {
            return false; // Invalid credentials
        }
    }
    
}
