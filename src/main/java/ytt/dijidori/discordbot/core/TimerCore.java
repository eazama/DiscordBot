/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytt.dijidori.discordbot.core;

import java.sql.ResultSet;
import ytt.dijidori.discordbot.core.Core;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Eric
 */
public class TimerCore {

    private static TimerCore core;

    private TimerCore() {

    }

    public static TimerCore getCore() {
        if (core == null) {
            core = new TimerCore();
        }
        return core;
    }

    public long getTimerValue(String userID, String timername) {
        String ret = UserCore.getCore().getProperty(userID, timername, "0");
        try {
            return Long.parseLong(ret);
        } catch (Exception ex) {
            System.out.println("Error in PermissionsInfo.getRoleValue");
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public void setTimer(long time, String timerName, String userID) {
        UserCore.getCore().setProperty(userID, timerName, time);
    }

    public boolean hasTimerExpired(String timerName, String userID) {
        return getTimerValue(timerName, userID) < new DateTime().getMillis();
    }
}
