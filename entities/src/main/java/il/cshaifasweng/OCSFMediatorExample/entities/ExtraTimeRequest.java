package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.*;

@Entity
@Table(name = "extratimerequest")
public class ExtraTimeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int examCode;
    private int extraTime;
    private String reason;

    public ExtraTimeRequest(int examCode, int extraTime, String reason) {
        super();
        this.examCode = examCode;
        this.extraTime = extraTime;
        this.reason = reason;
    }

    public int getExamCode() {
        return examCode;
    }

    public void setExamCode(int examCode) {
        this.examCode = examCode;
    }

    public int getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(int extraTime) {
        this.extraTime = extraTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
