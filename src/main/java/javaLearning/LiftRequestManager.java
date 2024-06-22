package javaLearning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class LiftRequestManager implements Runnable{
	private Lift[] lifts;
	private LinkedList<LiftRequest>globalRequestQueue;
	private int numLifts;
	public LiftRequestManager(int numLifts, Lift[] lifts)
	{
		this.numLifts = numLifts;
		this.lifts = new Lift[numLifts];
		for(int i = 0; i < numLifts; i++){
			this.lifts[i] = lifts[i];
		}

		globalRequestQueue = new LinkedList<LiftRequest>();
	}

	LiftRequest createRequest(String type, Integer floor)
	{
		
		switch(type){
			case "SUMMON_UP":
				return new LiftRequest(LiftRequest.SUMMON_UP, floor);
			case "SUMMON_DOWN":
				return new LiftRequest(LiftRequest.SUMMON_DOWN, floor);
			case "FLOOR":
				return new LiftRequest(LiftRequest.FLOOR, floor);
		}

		


		return new LiftRequest(-1, -1);
		
	}

	void readLiftRequestsFromFile(String filename) throws FileNotFoundException
	{
		FileInputStream fin = new FileInputStream(filename);
		Scanner sc = new Scanner(fin);

		int n = sc.nextInt();
		System.out.println(n);
		for(int i = 0; i < n; i++){
			System.out.println(i);
			String requestType = sc.next();
			Integer floor = sc.nextInt();

			LiftRequest req = createRequest(requestType, floor);

			globalRequestQueue.addLast(req);
			
		}

		sc.close();


		

		
	}

	void assignRequests()	
	{
		Random rand = new Random();
		int assigned = 0;
		int toBeAssigned = globalRequestQueue.size();
		try {
			while(assigned < toBeAssigned){
				
				for(int i = 0; i < numLifts; i++){
					
					for(int x = 0; x < 3; x++){
						Thread.sleep(1000);
						// System.out.println("Sairam " + globalRequestQueue.size());
						LiftRequest request = globalRequestQueue.pollFirst();
						if(request == null)
							return;
	
						System.out.println(request);
						lifts[i].addRequest(request);
						assigned++;
						
					}
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		assignRequests();
	}


}
