package com.parcel.distribution.webapp.edit_profile.validator;

import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.edit_profile.form.EditProfileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EditFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return EditProfileForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        EditProfileForm editProfileForm = (EditProfileForm) o;

        ValidationUtil.rejectIfEmpty(errors, "name", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "surname", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "phoneNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "name", editProfileForm.getName(), 20, messageSourceAccessor.getMessage("validator.name.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "surname", editProfileForm.getName(), 50, messageSourceAccessor.getMessage("validator.surname.is.too.long"));

        if(editProfileForm.getPhoneNumber().contains("[a-zA-Z]+")){
            ValidationUtil.reject(errors, "phoneNumber", messageSourceAccessor.getMessage("validator.phoneNumber.is.not.valid"));
        }

        if(editProfileForm.getName().matches("[0-9]+")){
            ValidationUtil.reject(errors, "name", messageSourceAccessor.getMessage("validator.name.is.not.valid"));
        }

        if(editProfileForm.getSurname().matches("[0-9]+")){
            ValidationUtil.reject(errors, "surname", messageSourceAccessor.getMessage("validator.surname.is.not.valid"));
        }

        ValidationUtil.rejectIfEmpty(errors, "city", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "postCode", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "street", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "streetNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "city", editProfileForm.getCity(), 30, messageSourceAccessor.getMessage("validator.city.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "street", editProfileForm.getCity(), 50, messageSourceAccessor.getMessage("validator.street.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "streetNumber", editProfileForm.getStreetNumber(), 5, messageSourceAccessor.getMessage("validator.streetNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "flatNumber", editProfileForm.getFlatNumber(), 5, messageSourceAccessor.getMessage("validator.flatNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "postCode", editProfileForm.getPostCode(), 6, messageSourceAccessor.getMessage("validator.postCode.is.too.long"));


        String regex = "^[0-9]{2}-[0-9]{3}$";
        if (!editProfileForm.getPostCode().matches(regex)) {
            ValidationUtil.
                    reject(errors, "postCode", messageSourceAccessor.getMessage("validator.postCode.is.not.valid"));
        }

    }
}
