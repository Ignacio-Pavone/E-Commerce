package com.ecommerce.converters;

import com.ecommerce.model.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter <RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType roleType) {
        String role;
        switch (roleType) {
            case manager:
                role = "manager";
                break;
            case seller:
                role = "seller";
                break;
            case buyer:
                role = "buyer";
                break;
            default:
                throw new IllegalArgumentException("Unknown Role " + roleType);
        }
        return role;
    }

    @Override
    public RoleType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        RoleType roleType;
        switch (s) {
            case "manager":
                roleType = RoleType.manager;
                break;
            case "seller":
                roleType = RoleType.seller;
                break;
            case "buyer":
                roleType = RoleType.buyer;
                break;
            default:
                throw new IllegalArgumentException("Unknown Role " + s);
        }
        return roleType;
    }
}
