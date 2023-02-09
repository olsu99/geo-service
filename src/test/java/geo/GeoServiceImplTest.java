package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    public static Stream<Arguments> sourceLocationByIp() {
        return Stream.of(Arguments.of("127.0.0.1", null),
                Arguments.of("172.0.32.11", "Moscow"),
                Arguments.of("96.44.183.149", "New York"),
                Arguments.of("172.44.183.149", "Moscow"),
                Arguments.of("96.0.32.11", "New York"));
    }

    @ParameterizedTest
    @MethodSource("sourceLocationByIp")
    public void testLocationByIp(String ip, String expected) {
        //given
        GeoServiceImpl geoService = new GeoServiceImpl();
        //when
        String result = geoService.byIp(ip).getCity();
        //then
        Assertions.assertEquals(expected, result);
    }
}
