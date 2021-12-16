package com.fil.authentication.repository;

import com.fil.authentication.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Customer c set c.daXoa = true where c.id in ?1")
    void deletes(List<Long> ids);
}
