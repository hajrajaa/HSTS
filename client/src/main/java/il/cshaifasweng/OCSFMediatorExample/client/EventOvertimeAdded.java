package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Subject;

import java.util.List;

public class EventOvertimeAdded
{
    private int infoID;
    private int overtimeDuration;

    public EventOvertimeAdded(int id, int duration)
    {
        this.infoID = id;
        this.overtimeDuration = duration;
    }

    public int getInfoID() {
        return infoID;
    }

    public void setInfoID(int infoID) {
        this.infoID = infoID;
    }

    public int getOvertimeDuration() {
        return overtimeDuration;
    }

    public void setOvertimeDuration(int duration) {
        this.overtimeDuration = duration;
    }
}
