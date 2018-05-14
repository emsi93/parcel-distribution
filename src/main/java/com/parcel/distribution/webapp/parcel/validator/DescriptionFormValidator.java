package com.parcel.distribution.webapp.parcel.validator;

import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.parcel.form.DescriptionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DescriptionFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return DescriptionForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DescriptionForm descriptionForm = (DescriptionForm) o;

        ValidationUtil.rejectIfEmpty(errors, "x", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "y", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "z", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "weight", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "description", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
    }
}
