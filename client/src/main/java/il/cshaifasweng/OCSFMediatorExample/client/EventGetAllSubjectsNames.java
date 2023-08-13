package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Subject;

import java.util.List;

public class EventGetAllSubjectsNames
{
    private List<String> allSubjectsNames;

    public EventGetAllSubjectsNames(List<String> allSubjects) {
        this.allSubjectsNames = allSubjects;
    }

    public List<String> getAllSubjectsNames() {
        return allSubjectsNames;
    }

    public void setAllSubjectsNames(List<String> allSubjects) {
        this.allSubjectsNames = allSubjects;
    }
}

