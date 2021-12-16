package com.fil.authentication.dao;

import com.fil.authentication.models.DanhMucDiaBan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DanhMucDiaBanDao {

    @Autowired
    private EntityManagerFactory emf;

    public DanhMucDiaBan getById(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select dn from DanhMucDiaBan dn where dn.id = :id", DanhMucDiaBan.class);
            query.setParameter("id", id);
            return (DanhMucDiaBan) query.getSingleResult();
        } catch (Exception e) {
            em.close();
            return null;
        } finally {
            em.getTransaction().commit();
            em.close();
        }


    }

    public void saveAll(List<DanhMucDiaBan> list) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for (DanhMucDiaBan danhMucDiaBan : list) {
                em.persist(danhMucDiaBan);
                em.flush();
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            em.close();

        }
    }


}
