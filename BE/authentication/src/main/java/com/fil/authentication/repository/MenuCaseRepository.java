package com.fil.authentication.repository;

import com.fil.authentication.models.MenuCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MenuCaseRepository extends JpaRepository<MenuCase, Long> {

    @Query(nativeQuery = true, value = "select * from auth_menu")
    List<MenuCase> getAll();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from auth_menu where id in :ids")
    void deletes(List<Long> ids);

    @Query(nativeQuery = true, value = "select * from auth_menu where id in :ids")
    List<MenuCase> findAllByIds(List<Long> ids);

    @Query(nativeQuery = true, value = "select * from auth_menu where parent_id in :ids")
    List<MenuCase> findAllChildByIds(List<Long> ids);

}
