package com.fil.authentication.repository;

import com.fil.authentication.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
