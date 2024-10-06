package com.firequasar.demo.services;

import java.util.List;

import com.firequasar.demo.entities.dto.SatelliteMessageDto;

public interface MessageService {
  String getMessage(List<String[]> satelliteMessages);
  List<String[]> convertMessages(List<SatelliteMessageDto> satellites);
}
