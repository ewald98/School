package ro.vvs.upt.config;

import java.util.Objects;

public class Configuration {

    private int port;
    private String webroot;
    private String maintenanceDirectory;
    private String errorFile;
    private String defaultFile;

    public Configuration(int port, String webroot, String maintenanceDirectory, String defaultFile, String errorFile) {
        this.port = port;
        this.webroot = webroot;
        this.maintenanceDirectory = maintenanceDirectory;
        this.defaultFile = defaultFile;
        this.errorFile = errorFile;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public String getMaintenanceDirectory() {
        return maintenanceDirectory;
    }

    public void setMaintenanceDirectory(String maintenanceDirectory) {
        this.maintenanceDirectory = maintenanceDirectory;
    }

    public String getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(String errorFile) {
        this.errorFile = errorFile;
    }

    public String getDefaultFile() {
        return defaultFile;
    }

    public void setDefaultFile(String defaultFile) {
        this.defaultFile = defaultFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return port == that.port &&
                Objects.equals(webroot, that.webroot) &&
                Objects.equals(maintenanceDirectory, that.maintenanceDirectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, webroot, maintenanceDirectory);
    }
}
