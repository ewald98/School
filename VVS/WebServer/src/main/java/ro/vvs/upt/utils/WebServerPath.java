package ro.vvs.upt.utils;

import ro.vvs.upt.config.Configuration;
import ro.vvs.upt.config.ConfigurationManager;

import java.util.Objects;

public class WebServerPath {

    private String rawPath;

    private String requestedPath;
    private String localRequestedPath;

    private Configuration config;

    public WebServerPath(String requestedPath) {
        config = ConfigurationManager.getInstance().getCurrentConfig();

        this.rawPath = requestedPath.replaceAll("%20", " ");
        this.requestedPath = assemblePathRequested(rawPath);
        this.localRequestedPath = assembleLocalRequestedPath(this.requestedPath);
    }

    public String getRawPath() {
        return rawPath;
    }

    public String getRequestedPath() {
        return requestedPath;
    }

    public String getLocalRequestedPath() {
        return localRequestedPath;
    }

    /**
     * The parameter is a String that ends with '/', it adds the prefix of server folder and the suffix of the default
     * page because no resource was specified. If the String
     */
    private String assemblePathRequested(String rawPath) {

        if (!rawPath.endsWith("/"))
            return rawPath;
        else
            return rawPath + config.getDefaultFile();
    }

    private String assembleLocalRequestedPath(String requestedPath) {
        return addWebRootPrefix(requestedPath);
    }


    private static String addWebRootPrefix(String path) {
        return ConfigurationManager.getInstance().getCurrentConfig().getWebroot() +
                (path.startsWith("/") ? path : ("/" + path));
    }

    public static WebServerPath getErrorWebServerPath() {
        return new WebServerPath(ConfigurationManager.getInstance().getCurrentConfig().getErrorFile());
    }

    public static String getDefaultFilePath() {
        return addWebRootPrefix(ConfigurationManager.getInstance().getCurrentConfig().getDefaultFile());
    }

}
