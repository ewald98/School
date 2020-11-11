package ro.vvs.upt;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private static final int SP = 0x20;
    private static final int CR = 0x0D;
    private static final int LF = 0x0A;

    public static void parseHttpRequest(InputStream is) {

        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.US_ASCII);

//        parseRequestLine(inputStream);
//        parseHeader(inputStream);
//        parseBody(inputSream);
    }


}
