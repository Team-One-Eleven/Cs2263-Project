package Cs2263.Project;

import java.io.Serializable;

public class Configuration implements Serializable {
    private double userIDseed;
    private String baseDirectory;
    private boolean logEnabled;
    private String logFilePath;
    private int defaultItemNum;
    private int currentItemNum;

    public double getNextUserIDseed() {
        userIDseed++;
        return userIDseed;
    }
    public String getBaseDirectory() {
        return baseDirectory;
    }
    public boolean isLogEnabled() {
        return logEnabled;
    }
    public String getLogFilePath() {
        return logFilePath;
    }
    public int getDefaultItemNum() {
        return defaultItemNum;
    }
    public int getCurrentItemNum() {
        return currentItemNum;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public void setCurrentItemNum(int currentItemNum) {
        this.currentItemNum = currentItemNum;
    }
}
