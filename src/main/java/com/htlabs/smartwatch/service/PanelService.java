package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.PanelDTO;

import java.util.List;

public interface PanelService {

    public List<PanelDTO> getPanelValue();

    public List<PanelDTO> getPanelsByScreen(String screenName);

    public void pairSenorIdWithPanel(String panelId , String sensorId);
}
