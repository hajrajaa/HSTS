package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.OvertimeRequest;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class EventGetAllOvertimeRequests
{
    private ArrayList<OvertimeRequest> allRequests;
    public EventGetAllOvertimeRequests(ArrayList<OvertimeRequest> allRequests) {
        this.allRequests = allRequests;
    }

    public ArrayList<OvertimeRequest> getAllRequests() {
        return allRequests;
    }

    public void setAllRequests(ArrayList<OvertimeRequest> allRequests) {
        this.allRequests = allRequests;
    }
}
