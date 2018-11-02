
public class Elasic_Collision extends Collision{

	
	public Elasic_Collision(SingleBall ball1, SingleBall ball2) {
		super(ball1, ball2);
	}

	@Override
	public boolean isThereSavingEnergyInCollision() {
		return true;
	}

	@Override
	public double getWastedEnergyInCollision() {
		return 0;
	}
	

	

}
