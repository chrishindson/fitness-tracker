package com.chhin.fitnesstracker.mapper;

import com.chhin.fitnesstracker.entity.FoodType;
import com.chhin.fitnesstracker.model.FoodTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodTypeMapper {
  FoodTypeDTO toFoodTypeDTO(FoodType foodType);

  FoodType toFoodType(FoodTypeDTO dto);
}
