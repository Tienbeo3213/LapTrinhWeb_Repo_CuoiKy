package EntityDB;

import DBUtil.DBUtil;
import Entity.Account;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDB {
    public static void insert(Product product) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }


    public static void update(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void delete(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(product));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    // Lấy ra sản phẩm mới nhất
    public static Product getLastProduct(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p ORDER BY p.id DESC";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setMaxResults(1);
        try {
            Product lastProduct = q.getSingleResult();
            return lastProduct;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static List<Product> getAllProduct() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        try {
            List<Product> products = q.getResultList();
            return products;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static List<Product> getProductsByName(String pname) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p WHERE p.productName like :name ";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("name","%" + pname + "%");// Thêm dấu % vào giá trị của tham số
        try {
            List<Product> products = q.getResultList();
            return products;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static List<Product> getProductsByCID(int cid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p WHERE p.category.id = :categoryId";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("categoryId",cid);
        try {
            List<Product> products = q.getResultList();
            return products;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static Product getProductByID(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Product p WHERE p.id = :id";
        TypedQuery<Product> q = em.createQuery(qString, Product.class);
        q.setParameter("id",id);
        try {
            Product products = q.getSingleResult();
            return products;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
