package com.htlabs.smartwatch.repository;
;
import com.htlabs.smartwatch.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, String> {

    public UserDetails findByEmail(String email);

    public UserDetails findByPhoneNo(String phoneNo);
}
