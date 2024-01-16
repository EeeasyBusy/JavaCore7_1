package org.example.sender;

import org.example.entity.Country;
import org.example.entity.Location;
import org.example.geo.GeoService;
import org.example.i18n.LocalizationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void send() {
        var geoService = Mockito.mock(GeoService.class);
        var localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        var messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");

        var expected = "Добро пожаловать";

        var result = messageSender.send(headers);

        Assertions.assertEquals(expected, result);
    }
}