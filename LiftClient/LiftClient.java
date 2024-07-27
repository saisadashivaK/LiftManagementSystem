import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javaLearning.LiftRequest;




class LiftClient {


    static LiftRequest createRequest(String type, Integer floor, Integer liftNumber)
	{
		
		switch(type){
			case "SUMMON_UP":
				return new LiftRequest(LiftRequest.SUMMON_UP, floor, liftNumber);
			case "SUMMON_DOWN":
				return new LiftRequest(LiftRequest.SUMMON_DOWN, floor, liftNumber);
			case "FLOOR":
				return new LiftRequest(LiftRequest.FLOOR, floor, liftNumber);
		}

		


		return new LiftRequest(-1, -1, -1);
		
	}

    public static void main(String[] args) throws IOException
    {
        System.out.println("WELCOME TO THE LIFT MANAGEMENT CLIENT");
        Scanner sc = new Scanner(System.in);
        while(true){
            String requestType = sc.next();
			Integer floor = sc.nextInt();
            Integer liftNumber = -1;
			if(requestType.equals("FLOOR"))
				liftNumber = sc.nextInt();
            
            Socket socket = new Socket("127.0.0.1", 3000, null, 0);
			LiftRequest req = createRequest(requestType, floor, liftNumber);
            ObjectOutputStream requestWriter = new ObjectOutputStream(socket.getOutputStream());
            Scanner responseScanner = new Scanner(socket.getInputStream());
            requestWriter.writeObject(req);
            requestWriter.flush();
            while(responseScanner.hasNextLine()){
                System.out.println(responseScanner.nextLine());
            }
            responseScanner.close();
        }
        // sc.close();
    }
}