package javaLearning;

public class LiftRequest {
// change this to enum later
    public static final Integer SUMMON_UP = 1;
    public static final Integer SUMMON_DOWN = 2;
    public static final Integer FLOOR = 3;

    public Integer requestType;
    public Integer destinationFloor;

    public LiftRequest(Integer requestType, Integer destinationFloor)
    {
        this.requestType = requestType;
        this.destinationFloor = destinationFloor;
    }


    @Override
    public String toString()
    {
        String repr = "LiftRequest = <TYPE, DST>";
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
        return repr.replace("DST", destinationFloor.toString());


    }
}
