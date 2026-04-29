package com.logistics_system.util;

public class DistanceUtil {

    public static final double EARTH_RADIUS = 6371;

    public static double calculateDistance(double lat1,double lng1,double lat2,double lng2){
                double dLat=Math.toRadians(lat2-lat1);
                double dLng=Math.toRadians(lng2-lng1);

                lat1=Math.toRadians(lat1);
                lat2=Math.toRadians(lat2);

                double a=Math.pow(Math.sin((dLat/2)),2)+
                        Math.cos(lat1)*Math.cos(lat2)* Math.pow(Math.sin(dLng/2),2);
                double c=2 * Math.asin(Math.sqrt(a));

                return EARTH_RADIUS * c;
    }
}
