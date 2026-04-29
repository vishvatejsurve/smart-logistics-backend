package com.logistics_system.util;

public class ETAUtil {

    private static final double AVG_SPEED = 40.0;

    public static double calculateETA(double distanceKm){
        return (distanceKm /AVG_SPEED) * 60;
    }
}
