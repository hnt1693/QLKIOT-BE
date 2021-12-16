package com.fil.authentication.dao;

import com.fil.authentication.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    EntityManager entityManager;

    public List<Account> getAll(String searchData, String sortData, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct(ac) FROM Account ac ");
        sql.append(" JOIN ac.groups gr ");
        sql.append(" JOIN gr.customer cs ");
        sql.append(" WHERE ac.daXoa = false ");
        sql.append(" AND cs.id in  ");
        sql.append(" ( ");
        sql.append(" SELECT distinct cs2.id FROM Account ac2 ");
        sql.append(" JOIN ac2.groups gr2 ");
        sql.append(" JOIN gr2.customer cs2 ");
        sql.append(" WHERE ac2.username = :authenticationName OR ac2.nguoiTao = :authenticationName  ");
        sql.append(" ) ");
        sql.append(" ORDER BY ac.username asc ");
        TypedQuery<Account> query = entityManager.createQuery(sql.toString(), Account.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("authenticationName", authentication.getName());
        if (pageable != null) {
            return query.setMaxResults(pageable.getPageSize()).setFirstResult(new Long(pageable.getOffset()).intValue()).getResultList();
        }
        return query.getResultList();
    }

    public Integer count(String searchData, String sortData) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(distinct ac.id) FROM Account ac ");
        sql.append(" JOIN ac.groups gr ");
        sql.append(" JOIN gr.customer cs ");
        sql.append(" WHERE ac.daXoa = false ");
        sql.append(" AND cs.id in  ");
        sql.append(" ( ");
        sql.append(" SELECT distinct cs2.id FROM Account ac2 ");
        sql.append(" JOIN ac2.groups gr2 ");
        sql.append(" JOIN gr2.customer cs2 ");
        sql.append(" WHERE ac2.username = :authenticationName OR ac2.nguoiTao = :authenticationName  ");
        sql.append(" ) ");
        TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        query.setParameter("authenticationName", authentication.getName());
        return query.getSingleResult().intValue();
    }
}
