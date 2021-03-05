package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.*;
import com.htlabs.smartwatch.exceptions.UserException;
import com.htlabs.smartwatch.service.ClientService;
import com.htlabs.smartwatch.service.DepartmentService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.Roles;
import com.htlabs.smartwatch.utils.SuccessMessages;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Validated
@Slf4j
@Component
public class DepartmentController extends BaseController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("creating client")
    @PostMapping(path = "/createClient", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseClientDTO createClient(@RequestParam String name,
                                          @RequestParam String phoneNo,
                                          @RequestParam String address) {

        ClientDTO dto=new ClientDTO();
        dto.setClientName(name);
        dto.setClientPhone(phoneNo);
        dto.setClientAddress(address);

        String clientName = clientService.createClient(dto);
        return new ResponseClientDTO(HttpStatus.OK.value(),String.format(SuccessMessages.CLIENT_CREATED), clientName);

    }

    @ApiOperation(value = "We can update details of the Client.")
    @PostMapping(path = "/updateClient" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateClient( @RequestParam String clientId ,
                                            @RequestParam(required = false) String clientName,
                                           @RequestParam(required = false) String clientPhone,
                                            @RequestParam(required = false) String clientAddress){

        clientService.updateClient(clientId,clientName,clientPhone,clientAddress);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.CLIENT_UPDATED, clientName));
    }


    @ApiOperation(value = "We can delete the Client.")
    @GetMapping(path = "/deleteClient" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO deleteClient( @RequestParam String clientId ){


        String value=clientService.deleteClient(clientId);
        return new ResponseDTO(HttpStatus.OK.value(), value);
    }

    @ApiOperation(value = "Get details of Client")
    @GetMapping(path = "/findAllClients", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }


    @ApiOperation(value = "fetching client by clientId")
    @GetMapping(path = "/findClientById",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClientDTO getClientById(@RequestParam String clientId) {

        return clientService.getClientById(clientId);
    }


    @ApiOperation(value = "fetching client by clientName")
    @GetMapping(path = "/findClientByName",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ClientDTO> getClientByName(@RequestParam String clientName) {

        return clientService.getClientByName(clientName);
    }



    @ApiOperation(value = "We can create a new Department.")
    @PostMapping(path = "/createDepartment", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO createDepartment(@RequestParam String clientName ,
                                        @RequestParam String locationName,
                                        @RequestParam String departmentName) {
        departmentService.createDepartment(clientName ,locationName, departmentName);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.DEPARTMENT_CREATED, departmentName));
    }

    @ApiOperation(value = "We can update details of the Department.")
    @PostMapping(path = "/updateDepartment" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateDepartment(@RequestParam String departmentId ,
                                     @RequestParam String departmentName){
        departmentService.updateDepartment(departmentId , departmentName);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.DEPARTMENT_UPDATED, departmentName));
    }

    @ApiOperation(value = "Get details of Department")
    @GetMapping(path = "/findAllDepartments", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation(value = "We can find details of the Department.")
    @GetMapping(path = "/findDepartmentById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public DepartmentDTO getDepartmentById(@RequestParam String departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @ApiOperation(value = "We can find details of the Department.")
    @GetMapping(path = "/findDepartmentByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<DepartmentDTO> getCountryByName(@RequestParam String departmentName) {
        return departmentService.getDepartmentByName(departmentName);
    }

    @ApiOperation(value = "Delete department countryId")
    @GetMapping(path = "/deleteDepartment",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO deleteDepartment(@RequestParam String departmentId ){
        departmentService.deleteDepartment(departmentId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.DEPARTMENT_REMOVED));

    }

}
