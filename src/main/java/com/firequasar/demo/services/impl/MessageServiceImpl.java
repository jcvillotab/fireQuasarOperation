package com.firequasar.demo.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

  private String[] trimLeadingEmptyStrings(String[] message) {
    int firstNonEmptyIndex = (int) Arrays.stream(message)
        .takeWhile(String::isEmpty)
        .count();

    return Arrays.copyOfRange(message, firstNonEmptyIndex, message.length);
}

  public String getMessage(List<String[]> satelliteMessages) {
    List<String[]> trimmedMessages = satelliteMessages.stream()
      .map(this::trimLeadingEmptyStrings)
      .collect(Collectors.toList());

    int maxMessageLength = trimmedMessages.stream()
        .mapToInt(message -> message.length)
        .max()
        .orElse(0);

    String[] finalMessage = new String[maxMessageLength];

    for (int i = 0; i < maxMessageLength; i++) {
      for (String[] message : trimmedMessages) {
        if (i < message.length && !message[i].isEmpty()) {
          finalMessage[i] = message[i];
          break;
        }
      }
    }
    return String.join(" ", finalMessage).trim();
  }

  public List<String[]> convertMessages(List<SatelliteMessageDto> satellites) {
    return satellites.stream()
        .map(satellite -> satellite.getMessage().toArray(new String[0]))
        .toList();
  }

}
