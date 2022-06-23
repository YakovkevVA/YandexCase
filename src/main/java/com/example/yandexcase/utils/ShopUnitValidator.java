package com.example.yandexcase.utils;

import com.example.yandexcase.ShopUnitType;
import com.example.yandexcase.entity.ShopUnitDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ShopUnitValidator implements ConstraintValidator<ShopUnitValidation, ShopUnitDTO> {


    @Override
    public boolean isValid(ShopUnitDTO shopUnitDTO, ConstraintValidatorContext constraintValidatorContext) {

        switch (shopUnitDTO.getType()){
            case OFFER:
                if(shopUnitDTO.getPrice()==null || shopUnitDTO.getPrice()<0) {
                    return false;
                }
                if(shopUnitDTO.getChildrens()!= null){
                    return false;
                }
                break;
            case CATEGORY:

                if (shopUnitDTO.getPrice()!=null) {
                    if (shopUnitDTO.getChildrens() == null || shopUnitDTO.getChildrens().isEmpty()) {
                        return false;
                    }
                }
                break;
        }

        return true;
    }
}
