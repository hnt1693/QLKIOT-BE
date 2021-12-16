package com.fil.authentication.repository;

import com.fil.authentication.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByNameAndNguoiTao(String name, String nguoiTao);
}
