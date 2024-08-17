<<<<<<<< HEAD:src/main/java/models/Objects/User.java
package models.Objects;
========
package models;
>>>>>>>> 54ace25 (lm04):src/main/java/models/User.java

public class User {
    private String email;
    private String password;
    private String confPass;
    private String name;
    private String company;
    private String phone;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String confPass, String name, String company, String phone) {
        this.email = email;
        this.password = password;
        this.confPass = confPass;
        this.name = name;
        this.company = company;
        this.phone = phone;
    }

    public User(String name, String company, String phone) {
        this.name = name;
        this.company = company;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
