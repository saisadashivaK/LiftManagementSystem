package javaLearning;



class Idle implements LiftState {
	public void startForDestination(Lift lift)
	{

		// have to use conditional variable here
		while(lift.getDestinationFloor() == -1);

		if(lift.getDirection() == "UP"){
			lift.state = lift.IDLE_MOVING_UP;
		}else if(lift.getDirection() == "DOWN"){
			lift.state = lift.IDLE_MOVING_DOWN;
		}

		lift.state.startForDestination(lift);


	}

	public void reachDestination(Lift lift)
	{
		
	}
}


class MovingUp implements LiftState {
	public void startForDestination(Lift lift){

	}

	public void reachDestination(Lift lift)
	{
		lift.state = lift.IDLE_MOVING_UP;
		try {
                        System.out.printf("Stopped at floor %d%n", lift.getCurrentFloor());
                        Thread.sleep(3000);
                }catch(Exception e){
                        e.printStackTrace();
                }

		lift.state.startForDestination(lift);

	}

}

class MovingDown implements LiftState {
	public void startForDestination(Lift lift){

	}

	public void reachDestination(Lift lift)
	{
		lift.state = lift.IDLE_MOVING_UP;
		try {
                        System.out.printf("Stopped at floor %d%n", lift.getCurrentFloor());
                        Thread.sleep(3000);
                }catch(Exception e){
                        e.printStackTrace();
                }

		lift.state.startForDestination(lift);

	}

}

class IdleMovingUp implements LiftState {

	public void startForDestination(Lift lift){
		if(lift.getDestinationFloor() == -1){
			lift.state = lift.IDLE;
			lift.setDirection("NA");
			return;
		}

		lift.state = lift.MOVING_UP;
		lift.setDirection("UP");
		while(!lift.hasReachedDestination()){
	       	 try {
	       	         Thread.sleep(3000);
	       	         lift.incrementCurrentFloor();
	
	       	 
	       	 }catch(Exception e){
	       	         e.printStackTrace();
	       	 }
	        
	        }
	
	        lift.state.reachDestination(lift);

	}

	public void reachDestination(Lift lift)
	{

	}
}

class IdleMovingDown implements LiftState {

	public void startForDestination(Lift lift){
		if(lift.getDestinationFloor() == -1){
			lift.state = lift.IDLE;
			lift.setDirection("UP");
			return;
		}
		lift.state = lift.MOVING_DOWN;
		lift.setDirection("DOWN");
		while(!lift.hasReachedDestination()){
	       	 try {
	       	         Thread.sleep(3000);
	       	         lift.decrementCurrentFloor();
	
	       	 
	       	 }catch(Exception e){
	       	         e.printStackTrace();
	       	 }
	        
	        }
	
	        lift.state.reachDestination(lift);

	}

	public void reachDestination(Lift lift)
	{

	}

}

