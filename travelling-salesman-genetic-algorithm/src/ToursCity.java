/* 
 * Class that encodes the cities
 */
public class ToursCity {
	double x;
	double y;
	
	//Creates city with x and y coords
	public ToursCity(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	// xCoord() and yCoord() return x and y coordinates of city
	public double xCoord(){
		return this.x;
	}
	
	public double yCoord(){
		return this.y;
	}
	
	// Distance between two cities
	public double distance(ToursCity city){
		double xDist = Math.abs(xCoord() - city.xCoord());
		double yDist = Math.abs(yCoord() - city.yCoord());
		double dist = Math.sqrt((xDist*xDist) + (yDist*yDist));
		
		return dist;
	}
	
	@Override
	public String toString(){
		return xCoord()+ ", " + yCoord();
	}
}