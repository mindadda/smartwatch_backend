package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;

import java.util.List;

public interface OperatorService {

    public Integer createOperator(String operatorName);

    public void deleteOperator(String operatorId);

    public List<OperatorDetailsDTO> getAllOperators();

    public OperatorDetailsDTO getOperatorById(String operatorId);

    public Integer updateOperator(String operatorId, String operatorName);

    public List<OperatorDetailsDTO> getOperatorByName(String operatorName);

}
