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

    public String createClient(ClientDTO dto){


            ClientDetails clientDetails= new ClientDetails();


            log.info("Creating Client");
             if (clientDetailRepository.findByClientPhone(dto.getClientPhone())!=null)
                throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessages.PHONE_ALREADY_EXISTS);

             ClientConverter.getClientDetailsEntityFromDto(dto,clientDetails);
              clientDetails.setClientId(UUID.randomUUID().toString());


        clientDetails.setCreatedAt(new Date());
        clientDetails.setUpdatedAt(new Date());
            clientDetailRepository.save(clientDetails);
            return clientDetails.getClientName();

    }

    @Override
    public void updateClient(String clientId, String clientName, String clientPhone ,String clientAddress){

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
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.COUNTRY_EXIST);
            }

        }

//        ClientDetails clientDetails=new ClientDetails();
//
//        clientDetails=clientDetailRepository.findById(clientId).orElse(null);
//
//        if(clientDetails==null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_CLIENT);
//        }
//        else{
//
//            log.info("Updating Client");
//            if(clientName!=null) {
//                clientDetailRepository.updateName(clientId, clientName , clientPhone);
//
//                return clientName;
//            }
//            else {
//
//                clientDetailRepository.updateAddress(clientId, clientPhone , clientAddress );
//
//                return clientAddress;
//
//            }
//        }


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

