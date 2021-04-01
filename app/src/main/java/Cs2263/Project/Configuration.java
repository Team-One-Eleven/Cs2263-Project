package Cs2263.Project;

import Cs2263.Project.listable.UserCredentials;

import java.io.Serializable;
import java.util.LinkedList;

public class Configuration implements Serializable {

    // NOT IN USE
    //public static final String LOG_PATH = "/log.json";
    //private boolean logEnabled;
    //private String baseDirectory;

    private double userIDseed;

    public Configuration(){
        userIDseed = 0;
    }

    public double getNextUserIDseed() {
        userIDseed++;
        return userIDseed;
    }

    public void recoverUserIDseed(LinkedList<UserCredentials> userList){
        double highest = 0;
        for (UserCredentials info : userList){
            String id = info.getUserId();
            id.replaceFirst("User", "");
            if (Double.valueOf(id) > highest){
                highest = Double.valueOf(id);
            }
        }
    }


//    public String getBaseDirectory() {
//        return baseDirectory;
//    }
//    public boolean isLogEnabled() {
//        return logEnabled;
//    }
//    public String getLogFilePath() {
//        return logFilePath;
//    }
//    public int getDefaultItemNum() {
//        return defaultItemNum;
//    }
//    public int getCurrentItemNum() {
//        return currentItemNum;
//    }
//    public void setBaseDirectory(String baseDirectory) {
//        this.baseDirectory = baseDirectory;
//    }
//    public void setLogEnabled(boolean logEnabled) {
//        this.logEnabled = logEnabled;
//    }
//    public void setLogFilePath(String logFilePath) {
//        this.logFilePath = logFilePath;
//    }
//    public void setCurrentItemNum(int currentItemNum) {
//        this.currentItemNum = currentItemNum;
//    }
}
