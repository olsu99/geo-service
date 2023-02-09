package sender;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    GeoServiceImpl gSMock = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl lSMock = Mockito.mock(LocalizationServiceImpl.class);

    MessageSenderImpl messageSender = new MessageSenderImpl(gSMock, lSMock);
    Map <String, String> headers = new HashMap<>();

    @Test
    public void testSendRussian(){
        //given
        Mockito.when(gSMock.byIp("172."))
                .thenReturn(new Location("", Country.RUSSIA, null, 0));

        Mockito.when(lSMock.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        String expected = "Добро пожаловать";

        //when
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");

        //then
        Assertions.assertEquals(expected, messageSender.send(headers));
    }

    @Test
    public void testSendEnglish(){
        //given
        Mockito.when(gSMock.byIp("96."))
                .thenReturn(new Location("", Country.USA, null, 0));

        Mockito.when(lSMock.locale(Country.USA))
                .thenReturn("Welcome");
        String expected = "Welcome";

        //when
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");

        //then
        Assertions.assertEquals(expected, messageSender.send(headers));
    }
}
