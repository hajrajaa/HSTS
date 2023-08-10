package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Subject;

import java.util.List;

public class EventGetAllSubjects
{
    List<Subject> allSubjects;

    public EventGetAllSubjects(List<Subject> allSubjects) {
        this.allSubjects = allSubjects;
    }

    public List<Subject> getAllSubjects() {
        return allSubjects;
    }

    public void setAllSubjects(List<Subject> allSubjects) {
        this.allSubjects = allSubjects;
    }
}
