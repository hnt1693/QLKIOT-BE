package com.fil.authentication.repository;

import com.fil.authentication.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Customer c set c.daXoa = true where c.id in ?1 ")
    void deletes(List<Long> ids);

    Customer findByNameAndNguoiTao(String name, String nguoiTao);
}
