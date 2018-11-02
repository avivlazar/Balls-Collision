import java.awt.event.*;
import java.util.*;


public interface CollisionListener{
	
	boolean isThereSavingEnergyInCollision();
	double getWastedEnergyInCollision();
	//boolean isThereCollision(SingleBall ball1, SingleBall ball2);
}
