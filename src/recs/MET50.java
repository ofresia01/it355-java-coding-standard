


/**
 * A class demonstrating the recommendation to avoid ambiguous or confusing uses of overloading.
 */
public class MET50 {
    
    /**
     * Method to calculate distance traveled from the source.
     * 
     * @param sourceLat the latitude of the source location
     * @param sourceLon the longitude of the source location
     * @param destLat the latitude of the destination location
     * @param destLon the longitude of the destination location
     * @return the distance traveled from the source
     */
    public double getDistanceFromSource(double sourceLat, double sourceLon, double destLat, double destLon) {
        // Implementation to calculate distance traveled from the source
        return 0.0; 
    }
    
    /**
     * Method to calculate remaining distance to the destination.
     * 
     * @param destLat the latitude of the destination location
     * @param destLon the longitude of the destination location
     * @param sourceLat the latitude of the source location
     * @param sourceLon the longitude of the source location
     * @return the remaining distance to the destination
     */
    public double getDistanceToDestination(double destLat, double destLon, double sourceLat, double sourceLon) {
        // Implementation to calculate remaining distance to the destination
        return 0.0;
    }

    /**
     * Main method to demonstrate the use of the refactored methods.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        MET50 calculator = new MET50();
        
        // Calculate distance traveled from the source
        double distanceFromSource = calculator.getDistanceFromSource(34.0522, -118.2437, 40.7128, -74.0060);
        System.out.println("Distance traveled from the source: " + distanceFromSource);
        
        // Calculate remaining distance to the destination
        double distanceToDestination = calculator.getDistanceToDestination(40.7128, -74.0060, 34.0522, -118.2437);
        System.out.println("Remaining distance to the destination: " + distanceToDestination);
    }
}
