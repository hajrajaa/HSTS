package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class StatisticsInfo  implements Serializable
{
    private String title;
    private int infoID;
    private double average;
    private double median;
    private int[] hist;
    private int inTimeCounter;
    private int timeUpCounter;

    public StatisticsInfo (ExecutedExamInfo exam)
    {
        this.title = exam.getTitle();
        this.infoID = exam.getId();
        this.average = exam.getAverage();
        this.median = exam.getMedian();
        this.hist = exam.getHist();
        this.inTimeCounter = exam.getInTimeCounter();
        this.timeUpCounter = exam.getTimeUpCounter();
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public int[] getHist() {
        return hist;
    }

    public void setHist(int[] hist) {
        this.hist = hist;
    }

    public int getInTimeCounter() {
        return inTimeCounter;
    }

    public void setInTimeCounter(int inTimeCounter) {
        this.inTimeCounter = inTimeCounter;
    }

    public int getTimeUpCounter() {
        return timeUpCounter;
    }

    public void setTimeUpCounter(int timeUpCounter) {
        this.timeUpCounter = timeUpCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInfoID() {
        return infoID;
    }

    public void setInfoID(int infoID) {
        this.infoID = infoID;
    }
}
