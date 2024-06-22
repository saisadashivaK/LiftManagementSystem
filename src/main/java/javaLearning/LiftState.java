package javaLearning;



public interface LiftState {
	public void fetchNewRequest(Lift lift) throws InterruptedException;
	public void reachDestination(Lift lift) throws InterruptedException;

}
