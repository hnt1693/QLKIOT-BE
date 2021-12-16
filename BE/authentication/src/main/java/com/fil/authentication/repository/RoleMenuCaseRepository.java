package com.fil.authentication.repository;

import com.fil.authentication.models.RoleMenuCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuCaseRepository extends JpaRepository<RoleMenuCase, Long> {
}
