package javaLearning;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
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

	LiftRequest createRequest(String type, Integer floor, Integer liftNumber)
	{
		
		switch(type){
			case "SUMMON_UP":
				return new LiftRequest(LiftRequest.SUMMON_UP, floor, -1);
			case "SUMMON_DOWN":
				return new LiftRequest(LiftRequest.SUMMON_DOWN, floor, -1);
			case "FLOOR":
				return new LiftRequest(LiftRequest.FLOOR, floor, liftNumber);
		}

		


		return new LiftRequest(-1, -1, -1);
		
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
			Integer liftNumber = -1;
			if(requestType.equals("FLOOR"))
				liftNumber = sc.nextInt();
			
			LiftRequest req = createRequest(requestType, floor, liftNumber);

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
						if(request.requestType != LiftRequest.FLOOR)
							lifts[i].addRequest(request);
						else
							lifts[request.liftNumber].addRequest(request);
						assigned++;
						
					}
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	void assignRequestsServer() throws IOException, ClassNotFoundException
	{
		ServerSocket serverSocket = new ServerSocket(3000);
		// assign requests to a single lift 3 at a time
		int currLiftNo = 0;
		int currLiftRequestCount = 0;
		
		while(true){
			Socket sock = serverSocket.accept();
			ObjectInputStream liftRequestStream = new ObjectInputStream(sock.getInputStream());
			PrintStream responseStream = new PrintStream(sock.getOutputStream());
			LiftRequest newLiftRequest = (LiftRequest)liftRequestStream.readObject();
			System.out.println("LiftRequest Type: " + newLiftRequest.requestType + " " + LiftRequest.FLOOR);
			if(!newLiftRequest.requestType.equals(LiftRequest.FLOOR)){
				lifts[currLiftNo].addRequest(newLiftRequest);
				responseStream.printf("Received request %s successfully. Assigned request to lift number %d\n", newLiftRequest.toString(), currLiftNo);
				responseStream.flush();
				currLiftRequestCount++;
				if(currLiftRequestCount == 3){
					currLiftNo = (currLiftNo + 1) % numLifts;
					currLiftRequestCount = 0;
				}
			}
			else{
				lifts[newLiftRequest.liftNumber].addRequest(newLiftRequest);
				responseStream.printf("Received floor request %s successfully. Assigned request to lift number %d\n", newLiftRequest.toString(), newLiftRequest.liftNumber);
				responseStream.flush();
			}
			
			
			
			

			
			
			liftRequestStream.close();
			liftRequestStream.close();
			responseStream.close();

		}

	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try{
			assignRequestsServer();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
