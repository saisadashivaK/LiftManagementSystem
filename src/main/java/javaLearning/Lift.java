package javaLearning;


class Lift {
	private int liftId;
	private int currentFloor;
	public String direction = "NA";
	private int destinationFloor;
	public LiftState state;
	public LiftState IDLE;
	public LiftState IDLE_MOVING_UP;
	public LiftState IDLE_MOVING_DOWN;
	public LiftState MOVING_DOWN;
	public LiftState MOVING_UP;
	public Lift()
	{
		this.IDLE = new Idle();
		this.IDLE_MOVING_UP = new IdleMovingUp();
		this.IDLE_MOVING_DOWN = new IdleMovingDown();
		this.MOVING_DOWN = new MovingDown();
		this.MOVING_UP = new MovingUp();
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
	public boolean hasReachedDestination()
	{
		return this.currentFloor == this.destinationFloor;
	}

	// atomic 
	public String getDirection()
	{
		return (this.destinationFloor >=  this.currentFloor) ? "UP": "DOWN";
	}

	

	// atomic
	public void getPreviousDirection()
	{
		return this.direction;
	}

	public void startForDestination()
	{
	}

	public void reachDestination()
	{

	}


}
