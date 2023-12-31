package com.chhin.fitnesstracker.mapper;

import com.chhin.fitnesstracker.entity.Activity;
import com.chhin.fitnesstracker.model.ActivityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

  //  @Mapping(source = "activityDate", target = "activityDate")
  //  ActivityDTO toActivityDTO(Activity activity);

  //  @Mapping(source = "activityDate",target = "activityDate")
  //  Activity toActivity(ActivityDTO activityDTO);
}
