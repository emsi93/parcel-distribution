package com.parcel.distribution.webapp.edit_profile.form;

import lombok.Data;

@Data
public class ChangePasswordForm {

    private String login;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
