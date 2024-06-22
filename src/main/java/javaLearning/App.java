package javaLearning;

import java.io.FileNotFoundException;

import javax.sound.sampled.SourceDataLine;

/**
 * Hello world!
 *
 */
public class App 
{
    

    static Lift[] lifts;
    static Thread[] liftThreads;
    static LiftRequestManager requestManager;
    static LiftRequestQueue[] liftQueue;

    public static void initialize(int numLifts)
    {
        lifts = new Lift[numLifts];
        liftThreads = new Thread[numLifts];
        liftQueue= new LiftRequestQueue[numLifts];
    }
    public static void main( String[] args )  throws FileNotFoundException
    {
        System.out.println("WELCOME TO THE LIFT MANAGEMENT SYSTEM");
        int numLifts = 2;
        initialize(numLifts);
        for(int i = 0; i < lifts.length; i++){
            liftQueue[i] = new LiftRequestQueue();
            lifts[i] = new Lift(i, liftQueue[i]);
            liftThreads[i] = new Thread(lifts[i]);
        }

        requestManager = new LiftRequestManager(lifts.length, lifts);
        System.out.println(args[0]);
        requestManager.readLiftRequestsFromFile(args[0]);

        for(Thread t: liftThreads){
            t.start();
        }

        Thread requestManagerThread = new Thread(requestManager);
        requestManagerThread.run();

        
    }
}
