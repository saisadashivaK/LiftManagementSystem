package javaLearning;



class Idle implements LiftState {
	private LiftRequestQueue liftQueue;

	

	public Idle(LiftRequestQueue liftQueue) 
	{
		this.liftQueue = liftQueue;
	}

	@Override
	public void fetchNewRequest(Lift lift) throws InterruptedException
	{
		// TODO Auto-generated method stub


		Integer destinationFloor;
		destinationFloor = liftQueue.getNextRequestDestination();
			// liftQueue.wait();
		// System.out.println("Called idle.fetchNewRequest");
		if(lift.getCurrentFloor() > destinationFloor){
			lift.setState(lift.MOVING_DOWN);
		}else{
			lift.setState(lift.MOVING_UP);
		}
		lift.setDestinationFloor(destinationFloor);
		
	}

	@Override
	public void reachDestination(Lift lift) throws InterruptedException
	{
		
	}
	
}


class MovingUp implements LiftState {
	private LiftRequestQueue liftQueue;

	
	public MovingUp(LiftRequestQueue liftQueue) 
	{
		this.liftQueue = liftQueue;
	}

	@Override
	public void fetchNewRequest(Lift lift) throws InterruptedException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reachDestination(Lift lift) throws InterruptedException	
	{
		// TODO Auto-generated method stub

		while(!lift.hasReachedDestination()){


			if(liftQueue.checkRequest(lift.getCurrentFloor())){
				lift.outfmt.format("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
				lift.outfmt.flush();
				System.out.printf("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
				lift.setState(lift.IDLE);
				Thread.sleep(2000);
				liftQueue.removeRequest(lift.getCurrentFloor());
				return;
			}
			Thread.sleep(3000);
			lift.incrementCurrentFloor();
			lift.outfmt.format("Lift %2d at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
			lift.outfmt.flush();
			System.out.printf("Lift %2d at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		}

		lift.outfmt.format("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		lift.outfmt.flush();
		System.out.printf("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		lift.setState(lift.IDLE);
		liftQueue.removeRequest(lift.getCurrentFloor());
		Thread.sleep(2000);
		return;
		
	}
	

}

class MovingDown implements LiftState {
	private LiftRequestQueue liftQueue;

	public MovingDown(LiftRequestQueue liftQueue)
	{
		this.liftQueue = liftQueue;
	}

	@Override
	public void fetchNewRequest(Lift lift) throws InterruptedException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reachDestination(Lift lift) throws InterruptedException
	{
		// TODO Auto-generated method stub
		while(!lift.hasReachedDestination()){

			if(liftQueue.checkRequest(lift.getCurrentFloor())){
				lift.outfmt.format("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
				lift.outfmt.flush();
				System.out.printf("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
				lift.setState(lift.IDLE);
				Thread.sleep(2000);
				liftQueue.removeRequest(lift.getCurrentFloor());
				return;
			}

			Thread.sleep(3000);
			lift.decrementCurrentFloor();
			lift.outfmt.format("Lift %2d at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
			lift.outfmt.flush();
			System.out.printf("Lift %2d at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		}

		lift.outfmt.format("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		lift.outfmt.flush();
		System.out.printf("Lift %2d stopping at floor: %4d%n", lift.getLiftId(), lift.getCurrentFloor());
		lift.setState(lift.IDLE);
		Thread.sleep(2000);
		liftQueue.removeRequest(lift.getCurrentFloor());
		return;
		
	}
	

}

