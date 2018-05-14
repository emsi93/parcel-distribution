package com.parcel.distribution.webapp.contacts.validator;

import com.parcel.distribution.utils.EmailValidatorUtil;
import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.contacts.form.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContactFormValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;


    @Override
    public boolean supports(Class<?> aClass) {
        return ContactForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ContactForm contactForm = (ContactForm) o;

        ValidationUtil.rejectIfEmpty(errors, "name", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "surname", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "phoneNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "email", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "name", contactForm.getName(), 20, messageSourceAccessor.getMessage("validator.name.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "surname", contactForm.getName(), 50, messageSourceAccessor.getMessage("validator.surname.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "email", contactForm.getEmail(), 30, messageSourceAccessor.getMessage("validator.email.is.too.long"));

        if(contactForm.getPhoneNumber().contains("[a-zA-Z]+")){
            ValidationUtil.reject(errors, "phoneNumber", messageSourceAccessor.getMessage("validator.phoneNumber.is.not.valid"));
        }

        if(contactForm.getName().matches("[0-9]+")){
            ValidationUtil.reject(errors, "name", messageSourceAccessor.getMessage("validator.name.is.not.valid"));
        }

        if(contactForm.getSurname().matches("[0-9]+")){
            ValidationUtil.reject(errors, "surname", messageSourceAccessor.getMessage("validator.surname.is.not.valid"));
        }

        if (!EmailValidatorUtil.isValidEmailAddress(contactForm.getEmail())) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.not.valid"));
        }

        //adres

        ValidationUtil.rejectIfEmpty(errors, "city", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "postCode", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "street", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "streetNumber", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfTooLong(errors, "city", contactForm.getCity(), 30, messageSourceAccessor.getMessage("validator.city.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "street", contactForm.getCity(), 50, messageSourceAccessor.getMessage("validator.street.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "streetNumber", contactForm.getStreetNumber(), 5, messageSourceAccessor.getMessage("validator.streetNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "flatNumber", contactForm.getFlatNumber(), 5, messageSourceAccessor.getMessage("validator.flatNumber.is.too.long"));
        ValidationUtil.rejectIfTooLong(errors, "postCode", contactForm.getPostCode(), 6, messageSourceAccessor.getMessage("validator.postCode.is.too.long"));


        String regex = "^[0-9]{2}-[0-9]{3}$";
        if (!contactForm.getPostCode().matches(regex)) {
            ValidationUtil.
                    reject(errors, "postCode", messageSourceAccessor.getMessage("validator.postCode.is.not.valid"));
        }

    }
}
