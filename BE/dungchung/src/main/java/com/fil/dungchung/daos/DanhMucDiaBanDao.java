package com.fil.dungchung.daos;

import com.fil.dungchung.models.DanhMucDiaBan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DanhMucDiaBanDao {
    @Autowired
    private EntityManager entityManager;

    public List<DanhMucDiaBan> getAll(Pageable pageable) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT db FROM DanhMucDiaBan db ");
        sql.append(" ORDER BY db.id asc ");
        TypedQuery<DanhMucDiaBan> query = entityManager.createQuery(sql.toString(), DanhMucDiaBan.class);
        return query.setMaxResults(pageable.getPageSize()).setFirstResult(new Long(pageable.getOffset()).intValue()).getResultList();

    }

    public Integer count() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(db) FROM DanhMucDiaBan db ");
        TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
        return query.getSingleResult().intValue();

    }

}
