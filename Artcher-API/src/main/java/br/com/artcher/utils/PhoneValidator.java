package br.com.artcher.utils;

import br.com.artcher.annotations.Phone;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // permite valores nulos ou vazios
        }
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(value, "BR");
            return phoneNumberUtil.isValidNumberForRegion(phoneNumber, "BR");
        } catch (NumberParseException e) {
            return false;
        }
    }
}
