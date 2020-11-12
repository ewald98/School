package ro.vvs.upt.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ro.vvs.upt.config.ConfigurationManager;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class WebServerPathTest {

    @BeforeAll
    static void init() {
        ConfigurationManager.getInstance().loadConfigurationFile("src/test/resources/testConfig.txt");
    }

    @Test
    void assemble() {



    }

    @Test
    void testAddWebRootPrefix() {

        Class c = WebServerPath.class;
        Class[] cArg = new Class[1];
        cArg[0] = String.class;
        try {
            Method method = c.getDeclaredMethod("addWebRootPrefix", cArg);
            method.setAccessible(true);
            assertAll(
                    () -> assertEquals("www/a.html", method.invoke(null, "a.html")),
                    () -> assertEquals("www/default.html", method.invoke(null, "default.html"))
            );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testGetErrorFileLocalPath() {
        assertEquals("www/404.html", WebServerPath.getErrorWebServerPath().getLocalRequestedPath());
    }

}