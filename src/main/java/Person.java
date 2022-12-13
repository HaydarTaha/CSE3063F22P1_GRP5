import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public abstract class  Person {
    private String fName;
    private String lName;


    public void setfName(String fName){ this.fName = fName;}

    public void setlName(String lName){ this.lName = lName;}

    String getfName() { return fName; }

    String getlName() { return lName; }
}
