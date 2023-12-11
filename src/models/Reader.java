package models;

import models.db.Database;
import models.enums.Gender;
import models.generator.MyException;

import java.util.Objects;
import java.util.Scanner;

public class Reader {
   private long id;
   private String fullName;
   private String email;
   private String phoneNumber;
   private Gender gender;

    public Reader(long id, String fullName, String email, String phoneNumber, Gender gender) {
        this.id = id;
        this.fullName = fullName;
        this.email = chekEmail(email);
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Reader() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = chekEmail(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    private String chekEmail(String email) {
        while (true) {
            try {
                if (isValidEmail(email) && isEmailUnique(email)) {
                    return email;
                } else if (!isValidEmail(email)) {
                    throw new MyException("email должно заканчиваться на \"@gmail.com\"");
                } else {
                    throw new MyException("email уже используется.\n Пожалуйста, введите другой email : ");
                }
            } catch (MyException e) {
                System.out.println(e.getMessage());
                email = new Scanner(System.in).nextLine();
            }
        }
    }
    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") && email.length() > "@gmail.com".length();
    }

    private boolean isEmailUnique(String email) {
        for (Reader reader : Database.readers) {
                if (Objects.equals(reader.getEmail(), email)) {
                    return false;
                }
            }
        return true;
    }

    @Override
    public String toString() {
        return
                "\nid: " + id +
                "  fullName: " + fullName +
                "  email: " + email +
                "  phoneNumber: " + phoneNumber +
                "  gender: " + gender
                ;
    }
}
