package com.mycrud.project.dao;

import com.mycrud.project.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;
    private static final int PAGE = 5;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User was successful added. User info: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User was successful updated. User info: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        if (user != null)
            session.delete(user);
        logger.info("User was successful removed. User info: " + user);
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        logger.info("User was successful loaded. User info: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(Long page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User");
        query.setFirstResult((int)(page - 1) * PAGE);
        query.setMaxResults(PAGE);
        List<User> usersList = query.list();
        for(User x : usersList)
            logger.info("User list. User info: " + x);
        return usersList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE USER_NAME = :name");
        query.setParameter("name", name);
        List<User> usersList = query.list();
        for(User x : usersList)
            logger.info("User list. User info: " + x);
        return usersList;
    }


}
