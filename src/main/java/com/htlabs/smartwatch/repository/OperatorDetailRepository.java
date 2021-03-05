package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.OperatorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OperatorDetailRepository extends JpaRepository<OperatorDetails,String> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operator where operator_id = :#{#operatorId}", nativeQuery = true)
    public void deleteOperator(String operatorId);

    @Query(value = "SELECT operator_name FROM operator WHERE operator_name= :#{#operatorName}", nativeQuery = true)
    public String findOperatorName(String operatorName);

    @Query(value = "SELECT * FROM operator WHERE operator_name LIKE %:operatorName%", nativeQuery = true)
    List<OperatorDetails> findByName(String operatorName);

}
