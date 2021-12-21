package com.fil.authentication.repository;

import com.fil.authentication.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByNameAndNguoiTao(String name, String nguoiTao);

    @Query(nativeQuery = true, value = " select *\n" +
            " from auth_groups\n" +
            " inner join auth_groups_roles agr on auth_groups.id = agr.group_id\n" +
            " inner join auth_roles r on agr.roles_id = r.id\n" +
            " where r.menu_id in ?1")
    List<Group> findAllByRoleIds(List<Long> ids);
}
