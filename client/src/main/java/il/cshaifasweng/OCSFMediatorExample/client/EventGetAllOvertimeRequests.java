package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.OvertimeRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class EventGetAllOvertimeRequests
{
    private ArrayList<OvertimeRequest> allRequests;
    private boolean switchPage;
    public EventGetAllOvertimeRequests(ArrayList<OvertimeRequest> allRequests, boolean switchPage) {
        this.allRequests = allRequests;
        this.switchPage = switchPage;
    }

    public ArrayList<OvertimeRequest> getAllRequests() {
        return allRequests;
    }

    public void setAllRequests(ArrayList<OvertimeRequest> allRequests) {
        this.allRequests = allRequests;
    }

    public boolean isSwitchPage() {
        return switchPage;
    }

    public void setSwitchPage(boolean switchPage) {
        this.switchPage = switchPage;
    }
}
