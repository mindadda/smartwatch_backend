package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.*;
import com.htlabs.smartwatch.service.ClientService;
import com.htlabs.smartwatch.service.PanelService;
import com.htlabs.smartwatch.service.ScreenService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.SuccessMessages;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screen")
@Validated
@Slf4j
@Component
public class ScreenController extends BaseController{

    @Autowired
    private ScreenService screenService;


    @Autowired
    private PanelService panelService;

    @ApiOperation(value = "We can create a new Screen.")
    @PostMapping(path = "/createScreen", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO createScreen(@RequestParam String screenName ,
                                    @RequestParam String departmentId,
                                    @RequestParam Integer rowNo,
                                    @RequestParam Integer colNo) {
        Integer status = screenService.createScreen(screenName ,departmentId , rowNo, colNo);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.SCREEN_CREATED, screenName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.SCREEN_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "We can update details of the Screen.")
    @PostMapping(path = "/updateScreen" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateScreen(@RequestParam String screenId ,
                                    @RequestParam(required = false) String screenName ,
                                    @RequestParam(required = false) String departmentId){
        Integer status = screenService.updateScreen(screenId ,screenName , departmentId);
        String message = null;
        if (status == 200){
            message = String.format(SuccessMessages.SCREEN_UPDATED, screenName);
        }
        else if (status == 401){
            message = String.format(ErrorMessages.SCREEN_EXIST);
        }
        return new ResponseDTO(status, message);
    }

    @ApiOperation(value = "Get details of Screen")
    @GetMapping(path = "/findAllScreens", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ScreenDTO> getAllScreens() {
        return screenService.getAllScreens();
    }

    @ApiOperation(value = "We can find details of the Screen.")
    @GetMapping(path = "/findScreenById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ScreenDTO getScreenById(@RequestParam String screenId) {
        return screenService.getScreenById(screenId);
    }

    @ApiOperation(value = "Fetching screen by screenId")
    @GetMapping(path = "/findScreenByDepartment",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ScreenDTO> getScreenByDepartment(@RequestParam String departmentId) {
        return screenService.getScreenByDepartment(departmentId);
    }

    @ApiOperation(value = "We can find details of the Screen.")
    @GetMapping(path = "/findScreenByName", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<ScreenDTO> getScreenByName(@RequestParam String screenName) {
        return screenService.getScreenByName(screenName);
    }

    @ApiOperation(value = "Delete Screen screenId")
    @GetMapping(path = "/deleteScreen",produces ={MediaType.APPLICATION_JSON_VALUE} )
    public ResponseDTO deleteScreen(@RequestParam String screenId ){
        screenService.deleteScreen(screenId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.CLIENT_REMOVED));
    }


    @ApiOperation(value = "Get values of Panel")
    @GetMapping(path = "/findPanelValue", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<PanelDTO> getPanelValue() {
        return panelService.getPanelValue();
    }

    @ApiOperation(value = "Get values of panel by screen")
    @GetMapping(path = "/findPanelsByScreen" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<PanelDTO> getPanelsByScreen(@RequestParam String screenId){
        return panelService.getPanelsByScreen(screenId);
    }

    @ApiOperation(value = "Attaching Sensor Id with the panel")
    @PostMapping(path = "/pairSenorIdWithPanel" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO pairSenorIdWithPanel(@RequestParam String panelId,
                                            @RequestParam String sensorId){
        panelService.pairSenorIdWithPanel(panelId , sensorId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.SENSOR_ID_ATTACHED));
    }

    @ApiOperation(value = "We can find details of the Panel.")
    @GetMapping(path = "/findPanelById", produces = { MediaType.APPLICATION_JSON_VALUE })
    public PanelDTO getPanelById(@RequestParam String panelId) {
        return panelService.getPanelById(panelId);
    }

}
