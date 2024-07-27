package javaLearning;

import java.io.Serializable;

public class LiftRequest implements Serializable {
// change this to enum later
    public static final Integer SUMMON_UP = 1;
    public static final Integer SUMMON_DOWN = 2;
    public static final Integer FLOOR = 3;

    public Integer requestType;
    public Integer destinationFloor;
    public Integer liftNumber;

    public LiftRequest(Integer requestType, Integer destinationFloor, Integer liftNumber)
    {
        this.requestType = requestType;
        this.destinationFloor = destinationFloor;
        this.liftNumber = liftNumber;
    }


    @Override
    public String toString()
    {
        String repr = "LiftRequest = <TYPE, DST, LIFTNUM>";
        switch(requestType){
            case 1:
                repr = repr.replace("TYPE", "SUMMON_UP");
                break;
            case 2:
                repr = repr.replace("TYPE", "SUMMON_DOWN");
                break;
            case 3:
                repr = repr.replace("TYPE", "FLOOR");
                break;
        }
        
        repr = repr.replace("LIFTNUM", liftNumber.toString());
        return repr.replace("DST", destinationFloor.toString());


    }
}
