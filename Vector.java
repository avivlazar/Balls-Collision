
public class Vector {

	private PointDouble direction;
	private double length;
	
	public Vector(double X_start, double Y_start, double X_end, double Y_end) {
		double vec_X = X_end - X_start;
		double vec_Y = Y_end - Y_start;
		this.length = Math.sqrt(vec_X * vec_X + vec_Y * vec_Y);
		vec_X /= length;
		vec_Y /= length;
		direction = new PointDouble(vec_X, vec_Y);
	}
	
	public Vector(double dx, double dy) {
		double vec_X = dx;
		double vec_Y = dy;
		length = Math.sqrt(vec_X * vec_X + vec_Y * vec_Y);
		vec_X /= length;
		vec_Y /= length;
		direction = new PointDouble(vec_X, vec_Y);
	}
	
	public Vector(PointDouble direction, double length) {
		this.direction = direction;
		this.length = length;
	}
	
	
	public PointDouble getDirection(){return direction;}
	public double getLength(){return length;}
	
	public void setLength(double length){this.length = length;}

	public double multiple(Vector v) {  //////// Multiple vector with Vector//////
		return ((this.getDirection().getX() * v.getDirection().getX()) + (this.getDirection().getY() * v.getDirection().getY())) * v.length * this.length;
	}
	
	public double getCos(Vector v){
		return (this.getDirection().getX() * v.getDirection().getX()) + 
				(this.getDirection().getY() * v.getDirection().getY());
	}

	public Vector subWith(Vector v) {
		return this.addWith(new Vector(v.getDirection(), -v.getLength()));
	}
	
	public double getDX(){
		return this.getDirection().getX() * this.getLength();
	}
	
	public double getDy() {
		return this.getDirection().getY() * this.getLength();
	}
	public Vector addWith(Vector v) {
		double dx = this.getDX() + v.getDX();
		double dy = this.getDy() + v.getDy();
		return new Vector(dx, dy);
	}
	
}
