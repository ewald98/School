package ro.vvs.upt.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {


    @Test
    void testGetInstance() {
        ConfigurationManager configManager = ConfigurationManager.getInstance();

        assertTrue(configManager == ConfigurationManager.getInstance());
    }

    @Test
    void testLoadConfigurationFile() {
        ConfigurationManager.getInstance().loadConfigurationFile("src/test/resources/testConfig.txt");

        Configuration config = ConfigurationManager.getInstance().getCurrentConfig();
        assertAll(
                () -> assertEquals(10008, config.getPort()),
                () -> assertEquals("www", config.getWebroot()),
                () -> assertEquals("test_", config.getMaintenanceDirectory()),
                () -> assertEquals("index.html", config.getDefaultFile()),
                () -> assertEquals("404.html", config.getErrorFile())
        );
    }

    @Test
    void testLoadConfigurationFileException() {
        assertThrows(ConfigurationFileException.class,
                () -> ConfigurationManager.getInstance().loadConfigurationFile("src/test/resources/testInavlidafasd.txt")
        );
    }
}