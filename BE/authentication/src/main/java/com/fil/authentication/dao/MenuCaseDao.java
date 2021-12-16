package com.fil.authentication.dao;

import com.fil.authentication.commons.Utils;
import com.fil.authentication.models.MenuCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuCaseDao {
    @Autowired
    private EntityManager entityManager;

    public List<MenuCase> getAll(String searchData, String sortData, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        Map<String, String> searchMap = new HashMap<>();
        String clientId = null;
        sql.append(" SELECT c FROM MenuCase c ");
        sql.append(" WHERE c.id is not null ");

        if (searchData != null) {
            searchMap = Utils.getSearchMap(searchData);
            clientId = searchMap.get("clientId");
            if (clientId != null) {
                sql.append(" AND c.clientDetails.clientId = :clientId ");
            }
        }

        if (sortData != null) {
            sql.append(" ORDER BY ").append(Utils.setAliasForEntity("c", sortData));
        } else {
            sql.append(" ORDER BY c.name asc ");
        }
        TypedQuery<MenuCase> query = entityManager.createQuery(sql.toString(), MenuCase.class);
        if (clientId != null) {
            query.setParameter("clientId", clientId);
        }
        if (pageable != null) {
            return query.setMaxResults(pageable.getPageSize()).setFirstResult(new Long(pageable.getOffset()).intValue()).getResultList();
        }
        return query.getResultList();
    }

    public Integer count(String searchData) {
        StringBuilder sql = new StringBuilder();
        Map<String, String> searchMap = new HashMap<>();
        String clientId = null;
        sql.append(" SELECT count(c) FROM MenuCase c ");
        sql.append(" WHERE c.isActivated = true ");

        if (searchData != null) {
            searchMap = Utils.getSearchMap(searchData);
            clientId = searchMap.get("clientId");
            if (clientId != null) {
                sql.append(" AND c.clientDetails.clientId = :clientId ");
            }
        }
        TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
        if (clientId != null) {
            query.setParameter("clientId", clientId);
        }
        return query.getSingleResult().intValue();
    }
}
