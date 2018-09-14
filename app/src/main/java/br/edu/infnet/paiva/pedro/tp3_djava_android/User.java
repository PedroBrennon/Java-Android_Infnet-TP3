package br.edu.infnet.paiva.pedro.tp3_djava_android;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class User implements Serializable {

    private String name, email, password, phone, cellphone, cpf, city;

    public void setString(int position, String field){
        switch (position){
            case 0:
                setName(field);
                break;
            case 1:
                setEmail(field);
                break;
            case 2:
                setPassword(field);
                break;
            case 3:
                setPhone(field);
                break;
            case 4:
                setCellphone(field);
                break;
            case 5:
                setCpf(field);
                break;
            case 6:
                setCity(field);
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
