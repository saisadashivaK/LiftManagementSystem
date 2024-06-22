package javaLearning;

import java.util.HashMap;
import java.util.LinkedList;

public class LiftRequestQueue {
    private LinkedList<LiftRequest> queue;
    private HashMap<Integer, LinkedList<LiftRequest>>mp;

    public LiftRequestQueue()
    {
        queue = new LinkedList<LiftRequest>();
        mp = new HashMap<Integer, LinkedList<LiftRequest>>();
    }

    public synchronized void addRequest(LiftRequest req)
    {
        queue.add(req);
        if(mp.get(req.destinationFloor) != null)
            mp.get(req.destinationFloor).add(req);
        else{
            LinkedList<LiftRequest>ll = new LinkedList<LiftRequest>();
            ll.add(req);
            mp.put(req.destinationFloor, ll);
        }
        this.notifyAll();    
    }


    public synchronized Integer getNextRequestDestination() throws InterruptedException
    {
        // System.out.println("Called getNextRequestDestination " + queue.peek());
        while(queue.peek() == null)
            this.wait();
        
        Integer floor = queue.peek().destinationFloor;
        return floor;
    }

    public synchronized LiftRequest getNextRequest()
    {
        return queue.peek();
    }


    public synchronized void removeRequest(Integer key)
    {
        LinkedList<LiftRequest>lreq = mp.get(key);
        if(lreq != null){
            for(LiftRequest req: lreq){
                queue.remove(req);
            }

            mp.remove(key);
        }
    }

    public synchronized boolean checkRequest(Integer key)
    {
        return mp.containsKey(key);
    }








    
}
