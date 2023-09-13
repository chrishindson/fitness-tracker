package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.util.HelperUtils;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BodyMeasurementDTO {

  private String bodyMeasurementArea;
  private DateDTO measurementDate;
  private BigDecimal measurementSize;
  private Map<Long, String> bodyMeasurementAreaList;

  public LinkedHashMap<Long, String> getSortedMap() {
    return HelperUtils.getSortedMap(this.bodyMeasurementAreaList);
  }
}
