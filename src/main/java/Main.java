import org.example.geo.GeoService;
import org.example.geo.GeoServiceImpl;
import org.example.i18n.LocalizationService;
import org.example.i18n.LocalizationServiceImpl;
import org.example.sender.MessageSender;
import org.example.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {

    //Тестовый пример
    public static void main(String[] args) {
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        messageSender.send(headers);
    }
}