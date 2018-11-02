
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SingleBall //extends Elasic_Collision
{

	// Arrays of colors and radius
//	private static final Color[] ARRAY_OF_COLORS = {Color.PINK, Color.RED, Color.GREEN, Color.BLACK, Color.BLUE};
//	private static final int[]ARRAY_OF_RADIUS = {0, 14, 18, 21, 24};
	
	public static final int DELAY = 1;  //Delay
	private static final int DOUBLE_MASS = 5;  //Parameter which double the masses
	
//	private int mass= (int) (Math.random() * 4 +1) * DOUBLE_MASS ;  //Ball mass by random
	private int radius; // Ball radius
	private Color color; //Ball color
	private double mass; //mass of ball
	
	private PointDouble centerPoint = new PointDouble(0, 0);  //start
	private double dx = 0.7;  //Delta X, Y
	private double dy = 0.7;

	private BallPanel ballPanel;
	
	/**Constructor**/
	public SingleBall(int r, Color c, double m){
		this.radius = r;
		this.color = c;
		this.mass = m;
	}
	
	
	/**Get radius by mass**/
	/*
	private int getRadiusByMass(int m) {
		return ARRAY_OF_RADIUS[m / DOUBLE_MASS];
	}

	/**Get the color by mass**/
	/*
	private Color getColorByMass(int m) {
		return ARRAY_OF_COLORS[m / DOUBLE_MASS];
	}
	/**Get methods**/
	public double getX(){return centerPoint.getX();}
	public double getY(){return centerPoint.getY();}
	public double getDx(){return dx;}
	public double getDy(){return dy;}
	public double getSpeed(){return (Math.sqrt( Math.pow(getDx(), 2) + Math.pow(getDy(), 2) ) * BallPanel.TURNING_TO_METER_PER_SECEND_ADDITIONAL);}
	public double getEnergy(){return Math.pow(getSpeed(), 2) * getMass() / 2.0;}
	public PointDouble getCenter(){return centerPoint;}
	public int getRadius(){return radius;}
	public double getMass(){return mass;}
	public int getDelay(){return DELAY;}
	public Color getColor(){return color;}
	
	/**Set methods**/
	public void setX(double x){centerPoint.setX(x);}
	public void setY(double y){centerPoint.setY(y);}
	public void setDx(double dx){this.dx = dx;}
	public void setDy(double dy){this.dy = dy;}
	public void setRadius(int r){this.radius = r;}
	
	
	public BallPanel getBallPanel(){
		return ballPanel;
	}

	

	
	
}
	
	
	
	
	
	
	
	
	
	