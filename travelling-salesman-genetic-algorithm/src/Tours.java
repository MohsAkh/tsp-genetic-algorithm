/*
 * Class that encodes the tours for the project
 */
import java.util.ArrayList;
import java.util.Collections;

public class Tours {
	private ArrayList tour = new ArrayList<ToursCity>();
	
	private double fitness = 0;
	private int dist = 0;
	
	// Constructor for an empty tour
	public Tours(){
		for (int i = 0; i < ToursArrayList.citiesNumber(); i++){
			tour.add(null);
		}
	}
	
	public Tours(ArrayList tour){
		this.tour = tour;
	}
	
	// Generates a random individual
	public void createIndividual(){
		// Adds all cities from ToursArrayList to tour
		for (int i = 0; i < ToursArrayList.citiesNumber(); i++){
			citySet(i, ToursArrayList.cityGet(i));
		}
		//Shuffles the tour into a random variant
		Collections.shuffle(tour);
	}
	
	// Retrieves city from tour
	public ToursCity cityGet(int index){
		return (ToursCity)tour.get(index);
	}
	
	public void citySet(int index, ToursCity city){
		tour.set(index, city);
		// fitness and distance need to be reset
		fitness = 0;
		dist = 0;
	}
	
	// Returns tours fitness
	public double fitnessGet(){
		if (fitness == 0){
			fitness = 1/(double)distanceGet();
		}
		return fitness;
	}
	
	// Returns distance of the tour
	public int distanceGet(){
		if(dist == 0){
			int tourDist = 0;
			// Loops tours cities
			for(int i = 0; i < sizeTour(); i++){
				// original city
				ToursCity cityFrom = cityGet(i);
				// next city
				ToursCity cityNext;
				//Loop to see if next city is last in tour
				// if it is final city is start
				if(i+1 < sizeTour()){
					cityNext = cityGet(i + 1);
				} else {
					cityNext = cityGet(0);
				}
				tourDist += cityFrom.distance(cityNext);
			}
			dist = tourDist;
		}
		return dist;
	}
	
	// Returns size of tour
	public int sizeTour(){
		return tour.size();
	}
	
	// check if city is in tour
	public boolean tourContains(ToursCity city){
		return tour.contains(city);
	}
	
	@Override
	public String toString(){
		String solutionOutput = "";
		for (int i = 0; i < sizeTour(); i++){
			solutionOutput += "(" + cityGet(i) + "),";
		}
		return solutionOutput;
	}

}
