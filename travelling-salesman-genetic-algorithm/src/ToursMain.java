import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class ToursMain {
	public static void main(String[] args) {
		// User input to select file to tour through
        Scanner input = new Scanner(System.in);
        System.out.println("1. berlin52.tsp\n" + "2. rl5915.tsp\n" + "3. eil51.tsp\n" + "4. kroB200.tsp\n"+ "5. d2103.tsp\n" + "\nEnter a number: ");
        int n = input.nextInt();
        String tsp_file = "";
        int flag = 0;
        switch(n){
        	case 1: 
        		tsp_file = "berlin52.tsp";
        		flag = 1;
        		break;
        	case 2:
        		tsp_file = "rl5915.tsp";
        		flag = 1;
        		break;
        	case 3:
        		tsp_file = "eil51.tsp";
        		flag = 1;
        		break;
        	case 4:
        		tsp_file = "kroB200.tsp";
        		flag = 1;
        		break;
        	case 5:
        		tsp_file = "d2103.tsp";
        		flag = 1;
        		break;
        	default:
        		System.out.println("invalid entry");
        }

        //Starts timer
        Instant starts = Instant.now();

        // Loads user selected file
        if(flag == 1){
        	ArrayList<Point2D> tour1 = TSPLibrary.loadTSPLib(tsp_file);
        
        // Creates ArrayList of cities
	        for(Point2D possible:tour1){
	            ToursCity city = new ToursCity(possible.getX(), possible.getY());
	            ToursArrayList.cityAdd(city);
        }
        
        // Population of 'j' is initialized
        int j = 50;
        
        ToursPop pop = new ToursPop(j, true);
        System.out.println("Number of points: " + pop.fittestTour().sizeTour());
        System.out.println("Initial distance: " + pop.fittestTour().distanceGet());

        // population is evolved over 'k' generations
        int k = 500;
        
        pop = ToursGeneticAlgorithm.evolvePop(pop);
        for (int i = 0; i < k; i++) {
            pop = ToursGeneticAlgorithm.evolvePop(pop);
        }

        // Output of results
        System.out.println("Final distance: " + pop.fittestTour().distanceGet()); 
        Instant ends = Instant.now();
        System.out.println("Elapsed time: " + Duration.between(starts, ends));
        System.out.println("Solution:");
        System.out.println(pop.fittestTour());
        
        }
    }

}
