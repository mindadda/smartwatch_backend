package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.ClientDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientDetailRepository extends JpaRepository<ClientDetails,String> {

    //public UserDetails findByEmail(String email);

    public ClientDetails findByClientPhone(String clientPhone);



    @Modifying
    @Transactional
    @Query(value = "UPDATE client set client_name = :#{#clientName} AND client_phone = :#{#clientPhone} where client_id = :#{#clientId}", nativeQuery = true)
    public void updateName(String clientId,String clientName , String clientPhone);



    @Modifying
    @Transactional
    @Query(value = "UPDATE client set client_address = :#{#clientAddress} AND client_phone = :#{#clientPhone} where client_id = :#{#clientId}", nativeQuery = true)
    public void updateAddress(String clientId,String clientPhone , String clientAddress );



    @Modifying
    @Transactional
    @Query(value = "DELETE FROM client where client_id = :#{#clientId}", nativeQuery = true)
    public void deleteClient(String clientId);

    @Query(value = "SELECT country_name FROM country WHERE country_name= :#{#countryName}", nativeQuery = true)
    public String findClientByName(String countryName);

    @Query(value = "SELECT * FROM client WHERE client_name LIKE %:clientName%", nativeQuery = true)
    public List<ClientDetails> findClientName(String clientName);

    @Query(value = "SELECT client_id FROM client WHERE client_name= :#{#clientName}", nativeQuery = true)
    public String findByClientName(String clientName);
}