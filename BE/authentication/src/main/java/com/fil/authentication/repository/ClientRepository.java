package com.fil.authentication.repository;

import com.fil.authentication.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientDetails, String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "delete from oauth_client_details where client_id in ?")
    void deletes(List<String> ids);

    @Query(value = " select cl from ClientDetails cl where cl.show =?1")
    List<ClientDetails> findAllByShow(boolean show);

}
