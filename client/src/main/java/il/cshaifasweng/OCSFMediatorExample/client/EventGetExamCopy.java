
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ExecutedVirtual;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;

import java.util.List;

public class EventGetExamCopy
{
    private ExecutedVirtual vExamCopy;

    public EventGetExamCopy(ExecutedVirtual vExamCopy) {
        this.vExamCopy = vExamCopy;
    }

    public ExecutedVirtual getvExamCopy() {
        return vExamCopy;
    }

    public void setvExamCopy(ExecutedVirtual vExamCopy) {
        this.vExamCopy = vExamCopy;
    }
}

