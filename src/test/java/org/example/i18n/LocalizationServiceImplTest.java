package org.example.i18n;
import org.example.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    void localeTest(Country country) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected;
        String result = localizationService.locale(country);
        if(!country.equals(Country.RUSSIA)){
            expected = "Welcome";
        } else {
            expected = "Добро пожаловать";
        }
        Assertions.assertEquals(expected, result);
    }

}