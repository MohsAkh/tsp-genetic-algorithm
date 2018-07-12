/*
 * Class that handles the genetic algorithm
 * and evolves population of solutions
 */
public class ToursGeneticAlgorithm {
	
	// Genetic algorithm params
	private static final double rateMutation = 0.015;
	private static final int sizeTournament = 5;
	private static final boolean elitism = true;
	
	//evolves pop over single generation
	public static ToursPop evolvePop(ToursPop pop){
		ToursPop newPop = new ToursPop(pop.sizePop(), false);
		
		//  Saves fittest tour if elitisim is on
		int offsetElitism = 0;
		if (elitism){
			newPop.tourSave(0, pop.fittestTour());
			offsetElitism = 1;
		}
		
		// population has an ordered crossover to create new tours
		for (int i = offsetElitism; i < newPop.sizePop(); i++){
			// Parents are selected and crossover occurs
            Tours p1 = tournamentSelection(pop);
            Tours p2 = tournamentSelection(pop);
            Tours offspring = crossover(p1, p2);
            
            // Creates new offspring for pop
            newPop.tourSave(i, offspring);
		}
		
		// population is mutated
        for (int i = offsetElitism; i < newPop.sizePop(); i++) {
            mutate(newPop.tourGet(i));
        }
		return newPop;
	}
	
	// parents have crossover applied to create offspring
	public static Tours crossover(Tours p1, Tours p2){
		// new offspring tour
		Tours offspring = new Tours();
		
		int positionStart = (int)(Math.random()*p1.sizeTour());
		int positionEnd = (int)(Math.random()*p1.sizeTour());
		
		for(int i = 0; i < offspring.sizeTour(); i++){
			if(positionStart < positionEnd && i > positionStart && i < positionEnd){
				offspring.citySet(i, p1.cityGet(i));
			} 
			else if (positionStart > positionEnd){
				if(!(i < positionStart && i > positionEnd)){
					offspring.citySet(i, p1.cityGet(i));
				}
			}
		}
		for (int i = 0; i < p2.sizeTour(); i++) {
            if (!offspring.tourContains(p2.cityGet(i))) {
                for (int j = 0; j < offspring.sizeTour(); j++) {
                    if (offspring.cityGet(j) == null) {
                        offspring.citySet(j, p2.cityGet(i));
                        break;
                    }
                }
            }
        }
        return offspring;
	}
	
	// mutation is applied to the tou5r 
    private static void mutate(Tours tour) {
        for(int tourPos1=0; tourPos1 < tour.sizeTour(); tourPos1++){
            if(Math.random() < rateMutation){

                int tourPos2 = (int) (tour.sizeTour() * Math.random());

                ToursCity city1 = tour.cityGet(tourPos1);
                ToursCity city2 = tour.cityGet(tourPos2);

                tour.citySet(tourPos2, city1);
                tour.citySet(tourPos1, city2);
            }
        }
    }

    // candidate tour is selected for crossover and fittest tour is returned
    private static Tours tournamentSelection(ToursPop pop) {
        ToursPop tournament = new ToursPop(sizeTournament, false);
        for (int i = 0; i < sizeTournament; i++) {
            int randomId = (int) (Math.random() * pop.sizePop());
            tournament.tourSave(i, pop.tourGet(randomId));
        }
        Tours fittest = tournament.fittestTour();
        return fittest;
    }

}
