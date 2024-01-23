package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojo.Countries;

@Repository
@Transactional
public class CountryRepoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Countries> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Countries c WHERE LOWER(c.name) = LOWER(:name)";
            Query<Countries> query = session.createQuery(hql, Countries.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Countries> findByState(String state) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Countries c WHERE LOWER(c.state) = LOWER(:state)";
            Query<Countries> query = session.createQuery(hql, Countries.class);
            query.setParameter("state", state);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Countries> findByNameOrState(String name, String state) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Countries c WHERE LOWER(c.name) = LOWER(:name) OR LOWER(c.state) = LOWER(:state)";
            Query<Countries> query = session.createQuery(hql, Countries.class);
            query.setParameter("name", name);
            query.setParameter("state", state);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveAll(List<Countries> country) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (Countries countries : country) {
                session.save(countries);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Countries> findByNameOrStateLimit(String name, String state) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Countries c WHERE LOWER(c.name) = LOWER(:name) OR LOWER(c.state) = LOWER(:state)";
            Query<Countries> query = session.createQuery(hql, Countries.class);
            query.setParameter("name", name);
            query.setParameter("state", state);
            query.setMaxResults(5);
            return query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        }
    }
}