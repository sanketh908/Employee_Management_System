package com.Sanketh.EmployeeManagementSystem.UtiltyClass;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

import java.util.Random;

@UtilityClass
public class RandomPasswordGenerator {

    //this was a method in the admin class before i made it a utility class
    static final String  upper ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lower="abcdefghijklmnopqrstuvwxyz";
    static final String number="0123456789";
    static final String spchar="!@#$%^&*()_+";
    static final String combin=upper+lower+number+spchar;

    private String geneateRandomPassword(int lenght) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        stringBuilder.append(upper.charAt(random.nextInt(upper.length())));
        stringBuilder.append(lower.charAt(random.nextInt(lower.length())));
        stringBuilder.append(number.charAt(random.nextInt(number.length())));
        stringBuilder.append(spchar.charAt(random.nextInt(spchar.length())));
        for (int i = 4; i < lenght; i++) {
            stringBuilder.append(combin.charAt(random.nextInt(combin.length())));
        }
        return stringBuilder.toString();
    }
}
