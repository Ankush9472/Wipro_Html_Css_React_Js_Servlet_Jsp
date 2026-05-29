package com.company.hibernateorm;

import com.company.hibernateorm.config.HibernateUtil;
import com.company.hibernateorm.dao.ProductDao;
import com.company.hibernateorm.entity.Category;
import com.company.hibernateorm.entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * All these 3 queries doing the same thing fetching the data from the database but the ways are different :-

 *  HQL: "from Product"
 *  Criteria API:  cb.createQuery(Product.class)...
 *  Native SQL:    "select * from products"
 */
public class App {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            // ─── FIRST RUN ONLY: Uncomment to seed data ─────────────────
//            Category category1 = new Category("Electronics");
//            Category category2 = new Category("Stationary");
//            Category category3 = new Category("PoojaItems");
//
//            session.persist(category1);
//            session.persist(category2);
//            session.persist(category3);
//
//            session.persist(new Product("Laptop", 50000, category1));
//            session.persist(new Product("Tablet", 50000, category1));
//            session.persist(new Product("Mouse", 50000, category1));
//
//            session.persist(new Product("Book", 150, category2));
//            session.persist(new Product("Pencil", 10, category2));
//            session.persist(new Product("Pen", 150, category2));

            ProductDao dao = new ProductDao();

            /* ═══════════ PART 1: HQL Queries ═══════════ */

           System.out.println("1. To list out all the products");
            dao.getAllProducts().forEach(p -> System.out.println(p.getName()));

            System.out.println("\n2. To list out all the products based on Price");
            dao.getByPrice(50000).forEach(System.out::println);

           System.out.println("\n3. To list out all the products based on SearchByKeyword");
            dao.searchByKeyword("Lap").forEach(System.out::println);


            /* ═══════════ PART 2: Criteria API ═══════════ */
//
//           System.out.println("Output Criteria Based Queries");
//            System.out.println("To display all products");
//           dao.findAll().forEach(System.out::println);
//
//           System.out.println("\nTo display all products based on Price");
//            dao.findByPrice(50000).forEach(System.out::println);
//
//            System.out.println("\nTo display all products based on Price Range (Min & Max)");
//          dao.getBetweenPrice(50, 50000).forEach(System.out::println);

//
//            /* ═══════════ PART 3: Native SQL Queries (ACTIVE) ═══════════ */
//
//            System.out.println("Native SQL Based Queries");
//
//            System.out.println("\nTo display all products by Object");
//            dao.findAll().forEach(p -> System.out.println(p[0] + " " + p[1]));
//
//            System.out.println("\nTo display products by an entity class (Generic type)");
//            dao.getAll().forEach(System.out::println);
//
//            System.out.println("\nTo display all products by id");
//            dao.getById(2).forEach(System.out::println);
//
//            tx.commit();
        }
    }
}