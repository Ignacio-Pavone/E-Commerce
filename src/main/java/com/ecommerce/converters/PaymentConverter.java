package com.ecommerce.converters;

import com.ecommerce.model.PaymentMethod;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentConverter implements AttributeConverter<PaymentMethod, String> {
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        String payment;
        switch (paymentMethod) {
            case CASH:
                payment = "cash";
                break;
            case CREDIT_CARD:
                payment = "credit_card";
                break;
            case DEBIT_CARD:
                payment = "debit_card";
                break;
            default:
                throw new IllegalArgumentException("Unknown Payment " + paymentMethod);
        }
        return payment;
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        PaymentMethod paymentMethod;
        switch (s) {
            case "cash":
                paymentMethod = PaymentMethod.CASH;
                break;
            case "credit_card":
                paymentMethod = PaymentMethod.CREDIT_CARD;
                break;
            case "debit_card":
                paymentMethod = PaymentMethod.DEBIT_CARD;
                break;
            default:
                throw new IllegalArgumentException("Unknown Payment " + s);
        }
        return paymentMethod;
    }

}

