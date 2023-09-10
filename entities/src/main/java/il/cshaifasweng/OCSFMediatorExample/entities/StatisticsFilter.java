package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class StatisticsFilter implements Serializable
{
    public enum FilterBy {StudentFilter,TeacherFilter,CourseFilter}

    private String text;
    private FilterBy filter;

    public StatisticsFilter(String text, FilterBy filter) {
        this.text = text;
        this.filter = filter;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public FilterBy getFilter() {
        return filter;
    }
    public void setFilter(FilterBy filter) {
        this.filter = filter;
    }
}
