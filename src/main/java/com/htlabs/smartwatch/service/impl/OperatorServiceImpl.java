package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;
import com.htlabs.smartwatch.entity.OperatorDetails;
import com.htlabs.smartwatch.entity.converter.OperatorConverter;
import com.htlabs.smartwatch.repository.OperatorDetailRepository;
import com.htlabs.smartwatch.service.OperatorService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDetailRepository operatorDetailRepository;

    @Override
    public void createOperator(String operatorName) {
        String opertorname = operatorDetailRepository.findOperatorName(operatorName);
        if (opertorname == null){
            log.info("Creating Operator:  {}", operatorName);
            String operatorId = UUID.randomUUID().toString();
            OperatorDetails operatorDetails = new OperatorDetails(operatorId, operatorName);
            operatorDetails.setCreatedAt(new Date());
            operatorDetails.setUpdatedAt(new Date());
            operatorDetailRepository.save(operatorDetails);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.OPERATOR_EXIST);
        }
    }

    @Override
    public void updateOperator(String operatorId, String operatorName) {
        OperatorDetails operatorDetails = operatorDetailRepository.findById(operatorId).orElse(null);
        if (operatorDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_OPERATOR);
        } else {
            String operatorname = operatorDetailRepository.findOperatorName(operatorName);
            if (operatorname == null) {
                log.info("Updating Operator:  {}", operatorName);
                operatorDetails.setOperatorName(operatorName);
                operatorDetails.setUpdatedAt(new Date());
                operatorDetailRepository.save(operatorDetails);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.COUNTRY_EXIST);
            }

        }
    }

    @Override
    public List<OperatorDetailsDTO> getOperatorByName(String operatorName) {
        List<OperatorDetails> operatorDetails = operatorDetailRepository.findByName(operatorName);
        if (operatorDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_OPERATOR);
        }
        return OperatorConverter.getOperatorDetailsDTOListFromEntityList(operatorDetails);
    }

    @Override
    public void deleteOperator(String operatorId){
        log.info("Deleting Operator : {}",operatorId);
        OperatorDetails operatorDetails = operatorDetailRepository.findById(operatorId).orElse(null);
        if (operatorDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_OPERATOR);
        }
        operatorDetailRepository.deleteOperator(operatorId);
    }


    @Override
    public List<OperatorDetailsDTO> getAllOperators(){
        log.info("Retrieving all the Operators.");
        List<OperatorDetails> operatorDetails = operatorDetailRepository.findAll();
        return OperatorConverter.getOperatorDetailsDTOListFromEntityList(operatorDetails);
    }


    @Override
    public OperatorDetailsDTO getOperatorById(String operatorId){
        log.info("Retrieving the Operator Details : {}", operatorId);
        OperatorDetails operatorDetails = operatorDetailRepository.findById(operatorId).orElse(null);
        return OperatorConverter.getOperatorDetailsDtoFromEntity(operatorDetails);
    }

}
