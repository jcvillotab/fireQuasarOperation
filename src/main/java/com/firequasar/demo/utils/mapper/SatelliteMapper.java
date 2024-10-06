package com.firequasar.demo.utils.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firequasar.demo.entities.SatelliteData;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.entities.dto.TopSecretSplitRequest;
import com.firequasar.demo.exceptions.MessageException;

@Mapper
public interface SatelliteMapper {
  @Mapping(target = "name", source = "satelliteName")
  @Mapping(target = "distance", source = "satellite.distance")
  @Mapping(target = "userId", source = "userId")
  @Mapping(target = "message", source = "satellite.message", qualifiedByName = "stringifyMessage")
  SatelliteData toSatelliteData(TopSecretSplitRequest satellite, String satelliteName, String userId);

  @Mapping(target = "name", source = "name")
  @Mapping(target = "distance", source = "distance")
  @Mapping(target = "message", source = "message", qualifiedByName = "parseMessage")
  SatelliteMessageDto toDto(SatelliteData satellite);

  @Named("parseMessage")
  default List<String> parseMessage(String message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(message, new TypeReference<List<String>>() {});
    } catch (JsonProcessingException e) {
      throw new MessageException("Error converting JSON string to message list");
    }
  }

  @Named("stringifyMessage")
  default String stringifyMessage(List<String> message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(message);
    } catch (JsonProcessingException e) {
      throw new MessageException("Error converting message list to JSON string");
    }
  }
}
