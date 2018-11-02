import javax.swing.Timer;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class BallPanel extends JPanel {
	
	public static final double LIMIT = 0.99;
	public static final double TURNING_TO_METER_PER_SECEND_ADDITIONAL = 1.0 / 11.8;
	
	private BallPanel ballPanel = this;  //Ball Panel
	private BallControl ballControl;  //ballControl 
	private double totalEnergy = 0;  //Energy
	private double avgEnergy = 0;
	private double totalSpeed = 0; //Speed
	private double avgSpeed = 0;
	private double friction = 0;   //The Friction in area
	private double percentWastedEnergy = 0;
	
	private ArrayList<SingleBall> arrayOfBalls = new ArrayList<SingleBall>();  // The arrayList Ball
	private SingleBall currentBall;  //Single ball
	private Timer THE_TIMER = new Timer(SingleBall.DELAY, new TimerListener());  //Main timer
	private boolean isSuspend = false;
	private boolean isUserClickedAddInSuspend = false;

	/**Constructor**/
	public BallPanel(BallControl ballControl) { 
		//ballAdding();  //start the program by add a ball
		
		this.ballControl = ballControl;
	}

	/**Timer Listener**/
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
	
	//Get methods
	public double getTotalEnergy() {return totalEnergy;}  //Get Total Energy Of System
	public double getAvgEnergyInSystem(){return (totalEnergy / (double)arrayOfBalls.size());}  // Get Avg Energy Of System
	public double getTotalSpeed() {return totalSpeed;}  //Speed Of System
	public double getAvgSpeedInSystem(){
		return (totalSpeed / (double)arrayOfBalls.size());
	}
	public int getNumOdBalls() {return arrayOfBalls.size();}  //Number of balls
	private double getSpeedOfSingleBall(SingleBall currentBall) {  // Get Speed of single ball in m/sec
		return Transfer_Speed_From_Pixel_Per_Ms_To_Meter_Per_Sec_SingleBall(currentBall);
	}
	private double getEnergyOfSingleBall(SingleBall currentBall) {  //Energy of single ball
		return Kinemathics_Energy_Of__SingleBall(currentBall);
	}
	
	//Set methods
	private void setTotalSpeed(double newTotalSpeed) {
		this.totalSpeed = newTotalSpeed;
		setAvgSpeed(newTotalSpeed / (double)getNumOdBalls());
	}
	private void setAvgSpeed(double newAvgSpped){
		this.avgSpeed = newAvgSpped;
	}
	private void setTotalEnergy(double newTotalEnergy) {
		this.totalEnergy = newTotalEnergy;
		setAvgEnergy(newTotalEnergy / (double)getNumOdBalls());
	}
	private void setAvgEnergy(double newAvgEnergy){
		this.avgEnergy = newAvgEnergy;
	}
	
	//Formulas
	public double Transfer_Speed_From_Pixel_Per_Ms_To_Meter_Per_Sec_SingleBall (SingleBall currentBall){  //of one ball
		double speedX_PixelPerMs = currentBall.getDx() / (double)currentBall.getDelay();
		double speedY_PixelPerMs = currentBall.getDy() / (double)currentBall.getDelay();
		double TotalSpeed_PixelPerMs_pow2 = Math.pow(speedX_PixelPerMs, 2) + Math.pow(speedY_PixelPerMs, 2);
		return Math.sqrt(TotalSpeed_PixelPerMs_pow2) * TURNING_TO_METER_PER_SECEND_ADDITIONAL;  //become from pixel/ms to meter/sec
	}
	public double Kinemathics_Energy_Of__SingleBall (SingleBall currentBall){
		double Speed_pow2 = Math.pow(getSpeedOfSingleBall(currentBall), 2);
		return currentBall.getMass() * Speed_pow2 / 2.0;  
	}
	
	/**Drawing**/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < arrayOfBalls.size(); i++){
			SingleBall currentBall = arrayOfBalls.get(i);
			g.setColor(currentBall.getColor());
			for(int j = i+1; j < arrayOfBalls.size(); j++ ){  //Check all the balls
				if(isThereCollision(currentBall, arrayOfBalls.get(j))){
					//There is a collision
					SingleBall currentBall2 = arrayOfBalls.get(j); //currentBall2 set
					////**Collision code**////
					Elasic_Collision collision_E = new Elasic_Collision(currentBall, currentBall2);
					double Speed_Change = collision_E.getSpeedChange();     //set total Speed
					double ts = totalSpeed + Speed_Change;
					setTotalSpeed(ts);   //Change the total and avg speed
					double Energy_Change = collision_E.getWastedEnergyInCollision();   //set total Speed
					double te = totalEnergy + Energy_Change;
					setTotalEnergy(te);
					setAllTextsInBallControl();   //Set Texts
					
				}
			}
			double x = currentBall.getX();
			double y = currentBall.getY();
			double dx = currentBall.getDx();
			double dy = currentBall.getDy();
			int radius = currentBall.getRadius();
			
			if (x < radius)
				dx = Math.abs(dx); // Check boundaries
			if (x > getWidth() - radius)
				dx = -Math.abs(dx);
			if (y < radius)
				dy = Math.abs(dy);
			if (y > getHeight() - radius)
				dy = -Math.abs(dy);
			x += dx; // Adjust ball position
			y += dy;
			g.fillOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);
			currentBall.setDx(dx);
			currentBall.setDy(dy);
			currentBall.setX(x);
			currentBall.setY(y);
		}
	}
	
	/**Collision Event**/
	//Collision Event
	public void ColusionEvent(SingleBall b1, SingleBall b2){
		Elasic_Collision elasticCol = new Elasic_Collision(b1, b2);
	}

	
	public void suspend() {
		if(THE_TIMER.isRunning() && arrayOfBalls.size() > 0){
			THE_TIMER.stop();
			isSuspend = true;
		}
	} // Suspend timer

	public void resume() {
		if(!THE_TIMER.isRunning() && arrayOfBalls.size() > 0){
			THE_TIMER.start();
			isSuspend = false;
			isUserClickedAddInSuspend = false;
		}
	} // Resume timer

	public void ballAdding() {
		if(!isUserClickedAddInSuspend){
			SingleBall currentBall = new SingleBall(ballControl.getRBPanel().getSelectedRadius(), 
                    ballControl.getRBPanel().getSelectedColor(), 
                    ballControl.getRBPanel().getSelectedMass());//new SingleBall();
            arrayOfBalls.add(currentBall);
            setTotalEnergy(totalEnergy +  getEnergyOfSingleBall(currentBall));
            setTotalSpeed(totalSpeed + getSpeedOfSingleBall(currentBall));
            if(!THE_TIMER.isRunning() && !isSuspend){  //Check if the user suspend
                     THE_TIMER.start();  //The timer start clocking
             }
            if(isSuspend){
            	isUserClickedAddInSuspend = true;
            }
            if(arrayOfBalls.size() > 0){
                   setAllTextsInBallControl();
            }
            repaint();
		}
		
	}// Add ball
	
   
   
    /**Remove single ball**/
	public void ballRemoving() {
		if(arrayOfBalls.size() > 0){
			int index = arrayOfBalls.size() - 1;
			currentBall = arrayOfBalls.get(index);
			arrayOfBalls.remove(index);
			setTotalEnergy(totalEnergy -  getEnergyOfSingleBall(currentBall));
			setTotalSpeed(totalSpeed - getSpeedOfSingleBall(currentBall));
			setAllTextsInBallControl();
		}
		repaint();
	}
	
	public void setAllTextsInBallControl() {
	    ballControl.setSpeedText(getSpeedMessage());
		ballControl.setAvgSpeedText(getAvgSpeedMessage());
		ballControl.setEnergyText(getEnergyMessage());
		ballControl.setAvgEnergyText(getAvgEnergyMessage());
		ballControl.setKmSpeedText(getKmSpeedMessage());
		ballControl.setAvgKmSpeedText(getAvgKmSpeedMessage());
	}
	
	/**The massage that will be printed (for control panel)**/
	//(Total Energy)
	public String getEnergyMessage() {
		return getNumForMessage(totalEnergy, " Newton");
	}
	//Average Energy
	public String getAvgEnergyMessage() {
		return getNumForMessage(avgEnergy, " Newton");
	}
	//Total Speed
	public String getSpeedMessage(){
		return getNumForMessage(totalSpeed, " m/sec");
	}
	//Average Speed
	public String getAvgSpeedMessage(){
		return getNumForMessage(avgSpeed, " m/sec");
	}
	//Total Speed in km/h
	public String getKmSpeedMessage(){
		double speedKm = totalSpeed * 3.6;
		return getNumForMessage(speedKm, " km/h");
	}
	//Avg Speed in km/h
	public String getAvgKmSpeedMessage(){
		double avgSpeedKm = totalSpeed * 3.6 / (double)(arrayOfBalls.size());
		return getNumForMessage(avgSpeedKm, " km/h");
	}
	
	/////**New method**////
	public String getNumForMessage(double num, String str){
		if(arrayOfBalls.size() == 0){
			return 0 + str;
		}
		else{
			if(arrayOfBalls.size() > 0){
				if(num >= LIMIT){
					return castDoubleToSmallOne(num) + str;
				}
				else
					if(num >= LIMIT*Math.pow(10, -3)){
						return castDoubleToSmallOne(num*Math.pow(10, 3)) + " mili " + str;
					}
					else
						if(num >= LIMIT*Math.pow(10, -6)){
							return castDoubleToSmallOne(num*Math.pow(10, 6)) + " micro " + str;
						}
						else
							if(num >= LIMIT*Math.pow(10, -9)){
								return castDoubleToSmallOne(num*Math.pow(10, 9)) + " nano " + str;
							}
							else
								if(num >= LIMIT*Math.pow(10, -12)){
									return  castDoubleToSmallOne(num*Math.pow(10, 12)) + " pico " + str;
								}
			}
		}
		return "Inhale By Zero!";
	}
	
	/**Cast a double number to small one**/
	public double castDoubleToSmallOne(double dNum){
		int num;
		if(dNum % 0.1 >= 0.6)
			num = (int) (dNum*10.0) + 1;
		else
			num = (int) (dNum*10.0) ;
		dNum =  num/10.0;
		return dNum;
	}
	
	public boolean isThereCollision(SingleBall ball1, SingleBall ball2) {
		double distance;  //Between center of cb and center of cb2
		distance = Math.sqrt( Math.pow(ball1.getX() - ball2.getX() , 2) + Math.pow(ball1.getY() - ball2.getY() , 2) );
		if(distance <= ball1.getRadius() + ball2.getRadius()){
			return true;
		}
		return false;
	}
	
	public BallControl getControlPanel(){
		return ballControl;
	}
	public double getFriction() {
		return friction;
	}
	public double getPercentWstedEnergy() {
		return percentWastedEnergy;
	}
}
