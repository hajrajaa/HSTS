package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedVirtual;

import java.util.List;

public class GetStudentExamsEvent {



    List<ExecutedVirtual> executedVirtuals;


    public List<ExecutedVirtual> getExecutedVirtuals() {
        return executedVirtuals;
    }

    public void setExecutedVirtuals(List<ExecutedVirtual> executedVirtuals) {
        this.executedVirtuals = executedVirtuals;


    }

    public GetStudentExamsEvent(List<ExecutedVirtual> executedVirtuals) {
        this.executedVirtuals = executedVirtuals;
    }









}
