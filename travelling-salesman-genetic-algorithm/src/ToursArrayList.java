/* Class that holds all our destination
 * cities for our tour
 */
import java.util.ArrayList;

public class ToursArrayList {
	// ArrayList to place all the cities in
	private static ArrayList arrayListCities = new ArrayList<ToursCity>();
	
	// Adds city to ArrayList
	public static void cityAdd(ToursCity city){
		arrayListCities.add(city);
	}
	
	// Returns a city from a given index
	public static ToursCity cityGet(int i){
		return (ToursCity)arrayListCities.get(i);
	}
	
	// Returns size of ArrayList with cities
	public static int citiesNumber(){
		return arrayListCities.size();
	}
}
