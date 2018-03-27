package com.parcel.distribution.webapp.edit_profile.validator;

import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.edit_profile.form.AddressForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddressFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddressForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        AddressForm addressForm = (AddressForm) o;

        ValidationUtil.rejectIfEmpty(errors, "city", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "postCode", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "street", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "streetNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "city", addressForm.getCity(), 30, messageSourceAccessor.getMessage("validator.city.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "street", addressForm.getCity(), 50, messageSourceAccessor.getMessage("validator.street.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "streetNumber", addressForm.getStreetNumber(), 5, messageSourceAccessor.getMessage("validator.streetNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "flatNumber", addressForm.getFlatNumber(), 5, messageSourceAccessor.getMessage("validator.flatNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "postCode", addressForm.getPostCode(), 6, messageSourceAccessor.getMessage("validator.postCode.is.too.long"));


        String regex = "^[0-9]{2}-[0-9]{3}$";
        if (!addressForm.getPostCode().matches(regex)) {
            ValidationUtil.
                    reject(errors, "postCode", messageSourceAccessor.getMessage("validator.postCode.is.not.valid"));
        }

    }
}
