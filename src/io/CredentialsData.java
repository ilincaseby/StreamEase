package io;

public class CredentialsData {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    /**
     * Getter
     * **/
    public String getName() {
        return name;
    }

    /**
     * Setter
     * **/
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter
     * **/
    public String getPassword() {
        return password;
    }

    /**
     * Setter
     * **/
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Getter
     * **/
    public String getAccountType() {
        return accountType;
    }

    /**
     * Setter
     * **/
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter
     * **/
    public String getCountry() {
        return country;
    }

    /**
     * Setter
     * **/
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Getter
     * **/
    public String getBalance() {
        return balance;
    }

    /**
     * Setter
     * **/
    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
