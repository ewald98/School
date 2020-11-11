package ro.vvs.upt;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class HttpParserTest {

    @Test
    void parseHttpRequest() {
        HttpParser.parseHttpRequest(generateValidTestCase());
    }


    private InputStream generateValidTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: 127.0.0.1:10008\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:82.0) Gecko/20100101 Firefox/82.0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Cache-Control: max-age=0\r\n\r\n";

        InputStream is = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));

        return is;
    }
}