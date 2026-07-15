package com.ajay.productivity;

import org.junit.jupiter.api.Test;

import java.util.TimeZone;

class TimeZoneTest {

    @Test
    void printTimeZone() {
        System.out.println("user.timezone = " + System.getProperty("user.timezone"));
        System.out.println("default = " + TimeZone.getDefault().getID());
    }
}