package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.ClientDTO;

import com.htlabs.smartwatch.entity.ClientDetails;


import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.converter.ClientConverter;


import com.htlabs.smartwatch.entity.converter.CountryConverter;
import com.htlabs.smartwatch.repository.ClientDetailRepository;
import com.htlabs.smartwatch.service.ClientService;

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
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientDetailRepository clientDetailRepository;


    @Override
    public Integer createClient(String name, String phoneNo, String address){
        Integer status ;
        String clientName = clientDetailRepository.findByClientName(name);
        if (clientName == null){
            log.info("Creating Client:  {}", clientName);
            String clientId = UUID.randomUUID().toString();
            ClientDetails clientDetails = new ClientDetails(clientId , name , phoneNo , address);
            clientDetails.setCreatedAt(new Date());
            clientDetails.setUpdatedAt(new Date());
            clientDetailRepository.save(clientDetails);
            status = HttpStatus.OK.value();
            return status;
        }
        else{
            status = HttpStatus.UNAUTHORIZED.value();
            return status;
        }
    }

    @Override
    public Integer updateClient(String clientId, String clientName, String clientPhone ,String clientAddress){

        Integer status ;
        ClientDetails clientDetails = clientDetailRepository.findById(clientId).orElse(null);
        if (clientDetails == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_CLIENT);
        }
        else {
            String clientname = clientDetailRepository.findClientByName(clientName);
            if (clientname == null){
                log.info("Updating Client:  {}", clientName);
                clientDetails.setClientName(clientName);
                clientDetails.setClientPhone(clientPhone);
                clientDetails.setClientAddress(clientAddress);
                clientDetails.setUpdatedAt(new Date());
                clientDetailRepository.save(clientDetails);
                status = HttpStatus.OK.value();
                return status;
            }
            else{
                status = HttpStatus.UNAUTHORIZED.value();
                return status;
            }

        }

    }


    @Override
    public String deleteClient(String clientId){

        ClientDetails clientDetails=new ClientDetails();

        clientDetails=clientDetailRepository.findById(clientId).orElse(null);

        if(clientDetails==null)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_CLIENT);
        }

        else{
            clientDetailRepository.deleteClient(clientId);
            return "client deleted successfully !";
        }

   }



   @Override
    public ClientDTO getClientById(String clientId){
       log.info("Retrieving the client Details ");

      ClientDetails clientDetails=clientDetailRepository.findById(clientId).orElse(null);


          return ClientConverter.getClientDtoFromEntity(clientDetails);

   }



   @Override
    public List<ClientDTO> getClientByName(String clientName){

       log.info("Retrieving the client Details ");

       List<ClientDetails> clientDetails=  clientDetailRepository.findClientName(clientName);

         return ClientConverter.getClientDTOListFromEntityList(clientDetails);


   }

    @Override
    public List<ClientDTO> getAllClients() {
        log.info("Retrieving all the Clients.");
        List<ClientDetails> clients = clientDetailRepository.findAll();
        return ClientConverter.getClientDTOListFromEntityList(clients);
    }
}

