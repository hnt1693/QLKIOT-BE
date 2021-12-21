package com.fil.authentication.dao;

import com.fil.authentication.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroupDao {
    @Autowired
    private EntityManager entityManager;

    public List<Group> getGroup(String searchData, String sortData, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct(gr) FROM Group gr ");
        sql.append(" LEFT JOIN gr.roles r ");
        sql.append(" LEFT JOIN gr.customer c ");
        sql.append(" WHERE gr.daXoa = false ");
        sql.append(" AND gr.id in  ");
        sql.append(" ( ");
        sql.append(" SELECT distinct gr2.id FROM Group gr2 ");
        sql.append(" WHERE gr2.nguoiTao = :authenticationName  ");
        sql.append(" ) ");
        TypedQuery<Group> query = entityManager.createQuery(sql.toString(), Group.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("authenticationName", authentication.getName());
        return query.getResultList();
    }

    public Integer count(String searchData, String sortData) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(distinct gr) FROM Group gr ");
        sql.append(" LEFT JOIN gr.roles r ");
        sql.append(" LEFT JOIN gr.customer c ");
        sql.append(" WHERE gr.daXoa = false ");
        sql.append(" AND gr.id in  ");
        sql.append(" ( ");
        sql.append(" SELECT distinct gr2.id FROM Group gr2 ");
        sql.append(" WHERE gr2.nguoiTao = :authenticationName  ");
        sql.append(" ) ");
        TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("authenticationName", authentication.getName());
        return query.getSingleResult().intValue();
    }

    public Group findById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct gr FROM Group gr ");
        sql.append(" LEFT JOIN gr.roles r ");
        sql.append(" LEFT JOIN gr.customer c ");
        sql.append(" WHERE gr.daXoa = false ");
        sql.append(" AND gr.id in  ");
        sql.append(" ( ");
        sql.append(" SELECT distinct gr2.id FROM Group gr2 ");
        sql.append(" WHERE gr2.nguoiTao = :authenticationName  ");
        sql.append(" ) ");
        if (null != id) sql.append(" AND gr.id =:id");
        TypedQuery<Group> query = entityManager.createQuery(sql.toString(), Group.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("authenticationName", authentication.getName());
        if (null != id) query.setParameter("id", id);
        query.setParameter("authenticationName", authentication.getName());
        return query.getSingleResult();
    }

}
