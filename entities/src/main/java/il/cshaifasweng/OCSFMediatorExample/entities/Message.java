package il.cshaifasweng.OCSFMediatorExample.entities;


import java.io.Serializable;


public class Message implements Serializable {

    private static final long serialVersionUID = -8224097662914849956L;

    private String message;

   private Object object1;


   public Message(String message, Object object1)
   {
        this.message=message;
        this.object1=object1;
   }

    public Message(String message)
    {
        this.message=message;
    }

    public Object getObject1()
    {
        return object1;
    }

    public void setObject1(Object object1)
    {
        this.object1=object1;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}

