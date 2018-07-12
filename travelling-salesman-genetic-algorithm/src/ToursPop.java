/*
 * Class that holds population of candidate tours
 */
public class ToursPop {
	// Contains pop of tours
	Tours[] tours;
	
	public ToursPop(int sizePop, boolean init){
		tours = new Tours[sizePop];
		// Checks if a pop of tours needs to be initialised
		if(init){
			for(int i = 0; i < sizePop(); i++){
				Tours tourNew = new Tours();
				tourNew.createIndividual();
				tourSave(i, tourNew);
			}
		}
	}
	
	// saves a tour to the pop
	public void tourSave( int i, Tours tour){
		tours[i] = tour;
	}
	
	// Returns a tour from pop
	public Tours tourGet(int i){
		return tours[i];
	}
	
	// Returns fittest tour from pop
	public Tours fittestTour(){
		Tours fittest = tours[0];
		// Loops through indivuals to find best
		for (int i = 1; i < sizePop(); i++){
			if(fittest.fitnessGet() <= tourGet(i).fitnessGet()){
				fittest = tourGet(i);
			}
		}
		return fittest;
	}
	
	// Returns size of pop
	public int sizePop(){
		return tours.length;
	}

}
