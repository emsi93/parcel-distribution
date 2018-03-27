package com.parcel.distribution.webapp.edit_profile.validator;

import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.edit_profile.form.FirstForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FirstFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return FirstForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        FirstForm firstForm = (FirstForm) o;

        ValidationUtil.rejectIfEmpty(errors, "name", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "surname", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "phoneNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "name", firstForm.getName(), 20, messageSourceAccessor.getMessage("validator.name.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "surname", firstForm.getName(), 50, messageSourceAccessor.getMessage("validator.surname.is.too.long"));

        if(firstForm.getPhoneNumber().contains("[a-zA-Z]+")){
            ValidationUtil.reject(errors, "phoneNumber", messageSourceAccessor.getMessage("validator.phoneNumber.is.not.valid"));
        }

        if(firstForm.getName().matches("[0-9]+")){
            ValidationUtil.reject(errors, "name", messageSourceAccessor.getMessage("validator.name.is.not.valid"));
        }

        if(firstForm.getSurname().matches("[0-9]+")){
            ValidationUtil.reject(errors, "surname", messageSourceAccessor.getMessage("validator.surname.is.not.valid"));
        }

    }
}
