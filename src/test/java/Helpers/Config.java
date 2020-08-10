package Helpers;

public class Config {
    private String iOSversion;
    private String appPath;
    private String noReset;
    private String platform;
    private String deviceName;
    private String appiumServer;

    public String getiOSversion() {
        return iOSversion;
    }

    public void setiOSversion(String iOSversion) {
        this.iOSversion = iOSversion;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getNoReset() {
        return noReset;
    }

    public void setNoReset(String noReset) {
        this.noReset = noReset;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppiumServer() {
        return appiumServer;
    }

    public void setAppiumServer(String appiumServer) {
        this.appiumServer = appiumServer;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
