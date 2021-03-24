package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    public Integer createClient(String name, String phoneNo, String address);

    public String deleteClient(String clientId);

    public ClientDTO getClientById(String clientId);

    public List<ClientDTO> getClientByName(String clientName);

    public List<ClientDTO> getAllClients();

    public Integer updateClient(String clientId, String clientName, String clientPhone, String clientAddress);

}
