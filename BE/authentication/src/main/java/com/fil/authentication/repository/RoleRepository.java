package com.fil.authentication.repository;

import com.fil.authentication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(nativeQuery = true, value = "select * from auth_roles where menu_id in ?1")
    List<Role> getAllByMenuCaseIdIn(List<Long> ids);
}
