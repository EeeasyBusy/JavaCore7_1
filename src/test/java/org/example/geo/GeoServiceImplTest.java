package org.example.geo;

import org.example.entity.Country;
import org.example.entity.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.example.geo.GeoServiceImpl.*;

class GeoServiceImplTest {

    public Location choiceLocation(String ip){
        Location expected;
        if (LOCALHOST.equals(ip)) {
           return expected = new Location(null, null, null, 0);
        } else if (MOSCOW_IP.equals(ip)) {
            return expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        } else if (NEW_YORK_IP.equals(ip)) {
             return expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        } else if (ip.startsWith("172.")) {
            return expected = new Location("Moscow", Country.RUSSIA, null, 0);
        } else if (ip.startsWith("96.")) {
             return expected = new Location("New York", Country.USA, null,  0);
        }
        return null;
    }

    @ParameterizedTest
    @CsvSource({"127.0.0.1" +
            "172.0.32" +
            "96.44.183.149" +
            "156.455.455"
    })
    public void byIpTest(String ip) {
        final Location expected = choiceLocation(ip);
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected, result);
    }
}