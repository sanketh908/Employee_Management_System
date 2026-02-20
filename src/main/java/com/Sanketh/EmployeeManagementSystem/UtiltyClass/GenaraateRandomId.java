package com.Sanketh.EmployeeManagementSystem.UtiltyClass;

import lombok.experimental.UtilityClass;

import java.util.Random;
@UtilityClass
public final class GenaraateRandomId {
    private int generateRandomManagerId()
    {
        Random random = new Random();
        return random.nextInt(1000,9999);
    }
}
