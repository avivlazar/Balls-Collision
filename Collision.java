import java.awt.event.ActionEvent;
import java.util.ArrayList;


public abstract class Collision implements CollisionListener{
	
	Vector V1_vec, V2_vec, a1_vec, a2_vec, 
	       b1_vec, b2_vec, c1_vec, c2_vec, U1_vec, U2_vec, Uf1_vec, Uf2_vec;
	PointDouble collisionPoint;
	double cos1, cos2;
	BallPanel ballPanel;
	
	public Collision(SingleBall ball1, SingleBall ball2) {
		this.ballPanel = ball1.getBallPanel();
		V1_vec = new Vector(ball1.getDx(), ball1.getDy());
		V2_vec = new Vector(ball2.getDx(), ball2.getDy());
	
		
		collisionPoint = getCollisonPoint(ball1.getCenter(), ball2.getCenter(),
				ball1.getRadius(), ball2.getRadius());
		a1_vec = new Vector(ball1.getCenter().getX(), ball1.getCenter().getY(),
				collisionPoint.getX(), collisionPoint.getY());
		a2_vec = new Vector(ball2.getCenter().getX(), ball2.getCenter().getY(), 
				collisionPoint.getX(), collisionPoint.getY());
		cos1 = V1_vec.getCos(a1_vec);
		cos2 = V2_vec.getCos(a2_vec);
		
		b1_vec = new Vector(a1_vec.getDirection(),  V1_vec.getLength() * cos1);
		b2_vec = new Vector(a1_vec.getDirection(), -V2_vec.getLength() * cos2);
		
		
		
		c1_vec = V1_vec.subWith(b1_vec);
		c2_vec = V2_vec.subWith(b2_vec);
		
//		System.out.println(c1_vec.getDX());
//		System.out.println(c1_vec.getDy());
		
		U1_vec = new Vector (a1_vec.getDirection(), getU1ByMass(ball1.getMass(), ball2.getMass()));
		U2_vec = new Vector(a1_vec.getDirection(), getU2ByMass(ball1.getMass(), ball2.getMass()));
		
		Uf1_vec = U1_vec.addWith(c1_vec);
		Uf2_vec = U2_vec.addWith(c2_vec);
		
		
		ball1.setDx(Uf1_vec.getDX());
		ball1.setDy(Uf1_vec.getDy());
		ball2.setDx(Uf2_vec.getDX());
		ball2.setDy(Uf2_vec.getDy());
		setXY(ball1, ball2);
	}

	private void setXY(SingleBall ball1, SingleBall ball2) {
		double num = Math.sqrt(Math.pow(ball1.getX() - ball2.getX(), 2) + Math.pow(ball1.getY() - ball2.getY(), 2));
		double num2 = ((ball1.getRadius() + ball2.getRadius()) - num) / 2.0;
		if(num2 > 0){
			ball1.setX(ball1.getX() + Uf1_vec.getDirection().getX() * num2);
			ball1.setY(ball1.getY() + Uf1_vec.getDirection().getY() * num2);
			ball2.setX(ball2.getX() + Uf2_vec.getDirection().getX() * num2);
			ball2.setY(ball2.getY() + Uf2_vec.getDirection().getY() * num2);
		}
	}

	private double getU1ByMass(double d, double e) {
		if(d == e){
			return b2_vec.getLength();
		}
		return ((d-e)*V1_vec.getLength()*cos1 - 2*e*V2_vec.getLength()*cos2) / (double)(d + e);
	}
	
	private double getU2ByMass(double d, double e) {
		if(d == e){
			return b1_vec.getLength();
		}
		return ((d-e)*V2_vec.getLength()*cos2 + 2*d*V1_vec.getLength()*cos1) / (double)(d + e);
	}


	public double getSpeedChange(){
		double speed_of_pair_balls_After_col = (Uf2_vec.getLength() + Uf1_vec.getLength()) * BallPanel.TURNING_TO_METER_PER_SECEND_ADDITIONAL;  // Speed After
		double speed_of_pair_balls_Before_col = (V1_vec.getLength() + V2_vec.getLength()) * BallPanel.TURNING_TO_METER_PER_SECEND_ADDITIONAL;  // Speed Before
		return  speed_of_pair_balls_After_col - speed_of_pair_balls_Before_col;
	}

	private PointDouble getCollisonPoint(PointDouble c1, PointDouble c2,
			int r1, int r2) {
		double x = (c1.getX() * (double)r2 + c2.getX() * (double)r1) / (double)(r1 + r2);
		double y = (c1.getY() * (double)r2 + c2.getY() * (double)r1) / (double)(r1 + r2);
		return new PointDouble(x, y);
	}
		
		
	
	
}