package com.identyum.project.dto;

import com.identyum.project.validator.PasswordMatches;
import com.identyum.project.validator.ValidPassword;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PasswordMatches
public class UserDTO implements Serializable {
    @NotNull
    @NotEmpty(message = "*Please provide a valid username")
    private String userName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;


    private boolean isUsing2FA;
    private Integer role;


    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [userName=").append(userName).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", isUsing2FA=")
                .append(isUsing2FA).append(", role=").append(role).append("]");
        return builder.toString();
    }
}
