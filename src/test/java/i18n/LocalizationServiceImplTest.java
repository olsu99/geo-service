package i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocalizationServiceImplTest {
    public static Stream<Arguments> sourceLocale() {
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome"));
    }

    @ParameterizedTest
    @MethodSource("sourceLocale")
    public void testLocale(Country country, String expected) {
        //given
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        //when
        //then
        assertThat(expected, equalTo(localizationService.locale(country)));
    }
}
