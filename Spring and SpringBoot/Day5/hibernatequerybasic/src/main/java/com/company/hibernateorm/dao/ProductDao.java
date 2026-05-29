package com.company.hibernateorm.dao;

import com.company.hibernateorm.config.HibernateUtil;
import com.company.hibernateorm.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class ProductDao {

    /* ═══════════════════════════════════════════════════════════
       PART 1: HQL (Hibernate Query Language) Based Queries
       ═══════════════════════════════════════════════════════════ */
//
//    public List<Product> getAllProducts() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "from Product";
//            return session.createQuery(hql, Product.class).list();
//        }
//    }
//
//    public List<Product> getByPrice(double price) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "from Product p where p.price = :price";
//            return session.createQuery(hql, Product.class)
//                    .setParameter("price", price)
//                    .list();
//        }
//    }
//
//    public List<Product> searchByKeyword(String keyword) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from Product p where p.name like :key", Product.class)
//                    .setParameter("key", "%" + keyword + "%")
//                    .list();
//        }
//    }


    /* ═══════════════════════════════════════════════════════════
       PART 2: Criteria API Based Queries
       ═══════════════════════════════════════════════════════════ */
//
//    public List<Product> findAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
//        cq.from(Product.class);
//        return session.createQuery(cq).getResultList();
//    }
//
//    public List<Product> findByPrice(double price) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
//        Root<Product> root = cq.from(Product.class);
//        cq.where(cb.equal(root.get("price"), price));
//        return session.createQuery(cq).getResultList();
//    }
//
//    public List<Product> getBetweenPrice(double minPrice, double maxPrice) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
//        Root<Product> root = cq.from(Product.class);
//        cq.where(cb.between(root.get("price"), minPrice, maxPrice));
//        return session.createQuery(cq).getResultList();
//    }


    /* ═══════════════════════════════════════════════════════════
       PART 3: Native SQL Based Queries (CURRENTLY ACTIVE)
       ═══════════════════════════════════════════════════════════ */

//    // Scalar query — returns raw row data as Object arrays
//    public List<Object[]> findAll() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String sql = "select * from products";
//            return session.createNativeQuery(sql).list();
//        }
//    }
//
//    // Entity-based query — maps rows directly to Product objects
//    public List<Product> getAll() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String sql = "select * from products";
//            return session.createNativeQuery(sql, Product.class).list();
//        }
//    }
//
//    // Parameterized query — fetch product(s) by ID
//    public List<Product> getById(int id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String sql = "select * from products where id = :id";
//            return session.createNativeQuery(sql, Product.class)
//                    .setParameter("id", id)
//                    .list();
//        }
//    }
}