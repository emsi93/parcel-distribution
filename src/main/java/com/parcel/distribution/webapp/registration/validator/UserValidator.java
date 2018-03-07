package com.parcel.distribution.webapp.registration.validator;

import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.registration.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Component
public class UserValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserForm user = (UserForm) o;

        ValidationUtil.rejectIfEmpty(errors, "login",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "email",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "password",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        ValidationUtil.rejectIfEmpty(errors, "passwordConfirm",
                messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        if (userRepository.findByEmail(user.getEmail()) != null) {
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.used"));
        }

        if (userRepository.findByLogin(user.getLogin()) != null) {
            ValidationUtil.reject(errors, "login", messageSourceAccessor.getMessage("validator.login.is.used"));
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            ValidationUtil.reject(errors, "password", messageSourceAccessor.getMessage("validator.different.passwords"));
        }

        if(!isValidEmailAddress(user.getEmail())){
            ValidationUtil.reject(errors, "email", messageSourceAccessor.getMessage("validator.email.is.not.valid"));
        }

        ValidationUtil
                .rejectIfTooLong(errors, "login", user.getLogin(), 20, messageSourceAccessor.getMessage("validator.login.is.too.long"));

        ValidationUtil
                .rejectIfTooLong(errors, "email", user.getEmail(), 30, messageSourceAccessor.getMessage("validator.email.is.too.long"));
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}