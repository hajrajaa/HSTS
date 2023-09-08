package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.StatisticsInfo;

import java.util.ArrayList;

public class EventAllStatisticsInfo
{
    ArrayList<StatisticsInfo> list;

    public EventAllStatisticsInfo(ArrayList<StatisticsInfo> list) {
        this.list = list;
    }

    public ArrayList<StatisticsInfo> getList() {
        return list;
    }

    public void setList(ArrayList<StatisticsInfo> list) {
        this.list = list;
    }
}
