package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

public class EventGetAllCoursesBySubject
{
    private List<String> allCoursesNames;

    public EventGetAllCoursesBySubject(List<String> all) {
        this.allCoursesNames = all;
    }

    public List<String> getAllCoursesNames() {
        return allCoursesNames;
    }

    public void setAllCoursesNames(List<String> all) {
        this.allCoursesNames = all;
    }
}
