package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.ClientLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLocationRepository extends JpaRepository<ClientLocation , String> {
}
