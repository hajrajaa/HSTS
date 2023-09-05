package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="OvertimeRequests")
public class OvertimeRequest implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String examName;
    private String teacherName;
    private int infoID;
    private String reason;
    private int timeToAdd;

    public OvertimeRequest(String examName, String teacherName, int infoID, String reason, int time) {
        this.examName = examName;
        this.teacherName = teacherName;
        this.infoID = infoID;
        this.reason = reason;
        this.timeToAdd = time;
    }

    public OvertimeRequest(OvertimeRequest request) {
        this.examName = request.getExamName();
        this.teacherName = request.getTeacherName();
        this.infoID = request.getInfoID();
        this.reason = request.getReason();
        this.timeToAdd = request.getTimeToAdd();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getInfoID() {
        return infoID;
    }

    public void setInfoID(int infoID) {
        this.infoID = infoID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTimeToAdd() {
        return timeToAdd;
    }

    public void setTimeToAdd(int timeToAdd) {
        this.timeToAdd = timeToAdd;
    }
}
