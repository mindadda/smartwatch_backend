package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.*;
import com.htlabs.smartwatch.service.ClientService;
import com.htlabs.smartwatch.service.PanelService;
import com.htlabs.smartwatch.service.ScreenService;
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
                                    @RequestParam String departmentName,
                                    @RequestParam Integer rowNo,
                                    @RequestParam Integer colNo) {
        screenService.createScreen(screenName ,departmentName , rowNo, colNo);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.SCREEN_CREATED, screenName));
    }

    @ApiOperation(value = "We can update details of the Screen.")
    @PostMapping(path = "/updateScreen" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO updateScreen(@RequestParam String screenId ,
                                    @RequestParam(required = false) String screenName ,
                                    @RequestParam(required = false) String departmentName){
        screenService.updateScreen(screenId ,screenName , departmentName);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.SCREEN_UPDATED, screenName));
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
    public List<ScreenDTO> getScreenByDepartment(@RequestParam String departmentName) {
        return screenService.getScreenByDepartment(departmentName);
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
    public List<PanelDTO> getPanelsByScreen(@RequestParam String screenName){
        return panelService.getPanelsByScreen(screenName);
    }

    @ApiOperation(value = "Attaching Sensor Id with the panel")
    @PostMapping(path = "/pairSenorIdWithPanel" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO pairSenorIdWithPanel(@RequestParam String panelId,
                                            @RequestParam String sensorId){
        panelService.pairSenorIdWithPanel(panelId , sensorId);
        return new ResponseDTO(HttpStatus.OK.value(), String.format(SuccessMessages.SENSOR_ID_ATTACHED));
    }
}
