package com.parcel.distribution.webapp.admin.validator;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.utils.EmailValidatorUtil;
import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.admin.form.CourierForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Component
public class CourierValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return Courier.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        CourierForm courier = (CourierForm) o;

        ValidationUtil.rejectIfEmpty(errors, "login",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "password",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "passwordConfirm",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "name",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "surname",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "phoneNumber",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        if (courierRepository.findByEmail(courier.getEmail()) != null) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.used"));
        }

        if (courierRepository.findByLogin(courier.getLogin()) != null) {
            ValidationUtil.reject(errors, "login", messageSourceAccessor.getMessage("validator.login.is.used"));
        }

        if (userRepository.findByEmail(courier.getEmail()) != null) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.used"));
        }

        if (userRepository.findByLogin(courier.getLogin()) != null) {
            ValidationUtil.reject(errors, "login", messageSourceAccessor.getMessage("validator.login.is.used"));
        }

        if (!courier.getPassword().equals(courier.getPasswordConfirm())) {
            ValidationUtil.reject(errors, "password", messageSourceAccessor.getMessage("validator.different.passwords"));
        }

        if (!EmailValidatorUtil.isValidEmailAddress(courier.getEmail())) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.not.valid"));
        }

    }

}
