package javaLearning;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Formatter;

class Lift implements Runnable{
	private Integer liftId;
	private int currentFloor;
	public String direction = "NA";
	private int destinationFloor;
	public Formatter outfmt;

	private LiftRequestQueue liftQueue;
	private LiftState state;
	public LiftState IDLE;
	public LiftState MOVING_DOWN;
	public LiftState MOVING_UP;
	public Lift(int liftId, LiftRequestQueue liftQueue) throws FileNotFoundException
	{
		this.liftId = liftId;
		this.IDLE = new Idle(liftQueue);
		this.MOVING_DOWN = new MovingDown(liftQueue);
		this.MOVING_UP = new MovingUp(liftQueue);
		this.state = this.IDLE;
		this.liftQueue = liftQueue;
		this.outfmt = new Formatter(new FileOutputStream(this.liftId.toString() + ".liftout"));

		this.outfmt.format("Lift %2d at floor: %4d%n", this.liftId, this.currentFloor);
		this.outfmt.flush();
		System.out.printf("Lift %2d at floor: %4d%n", this.liftId, this.currentFloor);
	}

	public void finalize()
	{
		this.outfmt.close();
	}

	public void setState(LiftState liftState)
	{
		this.state = liftState;
	}
	// atomic
	public void setLiftDirection(String direction)
	{
		this.direction = direction;
	}
	// atomic
	public void setDestinationFloor(int destinationFloor)
	{
		this.destinationFloor = destinationFloor;
	}
	
	// atomic
	public int getDestinationFloor()
	{
		return this.destinationFloor;
	}

	// atomic
	public void incrementCurrentFloor()
	{
		this.currentFloor++;
	}

	// atomic
	public void decrementCurrentFloor()
	{
		this.currentFloor--;
	}

	// atomic 
	public int getCurrentFloor()
	{
		return this.currentFloor;
	}	
	// atomic
	public synchronized boolean hasReachedDestination()
	{
		return this.currentFloor == this.destinationFloor;
	}

	// atomic 
	public String getDirection()
	{
		return (this.destinationFloor >=  this.currentFloor) ? "UP": "DOWN";
	}

	

	void startLift() throws InterruptedException
	{
		while(true){
			state.fetchNewRequest(this);
			state.reachDestination(this);
		}
	}

	void addRequest(LiftRequest request)
	{
		this.liftQueue.addRequest(request);
	}


	public Object getLiftId() {
		// TODO Auto-generated method stub
		return this.liftId;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			startLift();
		}catch(InterruptedException e){
			outfmt.format("Lift - %2d thread failed%n", getLiftId());
			e.printStackTrace();
		}
	}





}
