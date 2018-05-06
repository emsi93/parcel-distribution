package com.parcel.distribution.webapp.edit_profile.validator;

import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.utils.ValidationUtil;
import com.parcel.distribution.webapp.edit_profile.form.ChangePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordValidator implements Validator {

    private static final String VALIDATOR_OBLIGATORY_FIELD = "validator.obligatory.field";

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePasswordForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChangePasswordForm changePasswordForm = (ChangePasswordForm) o;

        ValidationUtil.rejectIfEmpty(errors, "oldPassword", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "newPassword", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));
        ValidationUtil.rejectIfEmpty(errors, "confirmPassword", messageSourceAccessor.getMessage(VALIDATOR_OBLIGATORY_FIELD));

        if(!userRepository.findByLogin(changePasswordForm.getLogin()).getPassword().equals(changePasswordForm.getOldPassword())){
            ValidationUtil.reject(errors,"oldPassword", messageSourceAccessor.getMessage("validator.invalid.old.password"));
        }

        if(!changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmPassword())){
            ValidationUtil.reject(errors,"newPassword", messageSourceAccessor.getMessage("validator.different.passwords"));
            ValidationUtil.reject(errors,"confirmPassword", messageSourceAccessor.getMessage("validator.different.passwords"));
        }
    }
}
