package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.util.HelperUtils;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BodyMeasurementDTO {

  private Long bodyMeasurementArea;
  private DateDTO measurementDate;
  private BigDecimal measurementSize;
  private Map<Long, String> bodyMeasurementAreaList;

  public LinkedHashMap<Long, String> getSortedMap() {
    return HelperUtils.getSortedMap(this.bodyMeasurementAreaList);
  }

  public String getBodyMeasurementDisplay() {
    return this.bodyMeasurementAreaList.get(this.bodyMeasurementArea);
  }
}
