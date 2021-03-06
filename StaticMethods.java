package de.materna.game;

public class StaticMethods {

    private static double calculateCityDistance(double lat1, double lat2, double lon1, double lon2) {

        // This code is contributed by Prasad Kshirsagar
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    private static int calulateTravelPrice(double distance){
        return (int)(0.4448*distance+3.125);
    }


    public static int calculateTravelPrice(Player player, String destination) {
        double currentLatitude, currentLongitude, destinationLatitude, destinationLongitude;
        double distance;
        {
            currentLatitude = CityEnum.valueOf(player.city.name).latitude;
            currentLongitude = CityEnum.valueOf(player.city.name).longitude;
            destinationLatitude = CityEnum.valueOf(destination).latitude;
            destinationLongitude = CityEnum.valueOf(destination).longitude;
        }
        distance= calculateCityDistance(currentLatitude, destinationLatitude, currentLongitude, destinationLongitude);
        return calulateTravelPrice(distance);

    }

}
