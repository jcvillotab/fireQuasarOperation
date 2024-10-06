package com.firequasar.demo.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

  public String getMessage(List<String[]> satelliteMessages) {

    int maxLength = satelliteMessages.stream()
        .mapToInt(arr -> arr.length)
        .max()
        .orElse(0);

    StringBuilder finalMessage = new StringBuilder();

    for (int i = 0; i < maxLength; i++) {
      String word = "";

      for (String[] message : satelliteMessages) {
        if (i < message.length && !message[i].isEmpty()) {
          word = message[i];
          break;
        }
      }

      if (!word.isEmpty()) {
        finalMessage.append(word).append(" ");
      }
    }

    return finalMessage.toString().trim();

  }

  public List<String[]> convertMessages(List<SatelliteMessageDto> satellites) {
    return satellites.stream()
        .map(satellite -> satellite.getMessage().toArray(new String[0]))
        .toList();
  }

}
