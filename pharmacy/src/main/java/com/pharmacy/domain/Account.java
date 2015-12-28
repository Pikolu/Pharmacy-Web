package com.pharmacy.domain;

import javax.persistence.Transient;

/**
 * Created by apopow on 27.12.2015.
 */
public class Account extends BaseUUID {

    private String email;
    private String password;
    @Transient
    private String passwordConfirm;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;//DigestUtils.sha256Hex(password);
    }

    /**
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;//DigestUtils.sha256Hex(passwordConfirm);;
    }

    @Override
    public String toString() {
        return "Account{" + "email=" + email + ", password=" + "******" + ", passwordConfirm=" + "******" + '}';
    }
}
