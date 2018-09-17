import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//
		Scanner scanner = new Scanner(System.in);
		
		TeleportationGraph graph = new TeleportationGraph();
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			
			// Link cities
			if (line.contains(" - ")) {
				String cityA = line.substring(0, line.indexOf("-")).trim();
				String cityB = line.substring(line.indexOf("-")+1).trim();
				graph.linkCities(cityA,  cityB);
			} else if (line.startsWith("cities from ")) {
				String citySrc = line.substring(12, line.indexOf(' ', 13)).trim();
				String jumpString = line.substring(line.indexOf(" in ") + 3, line.length()-5).trim();
				int jumps = Integer.parseInt(jumpString);
				System.out.println("cities from " + citySrc + " in " + jumps + " jumps: " + graph.citiesInJumps(citySrc, jumps));
			} else if (line.startsWith("can I teleport from")) {
				String cityA = line.substring(line.indexOf("from ") + 5, line.indexOf(" to ")).trim();
				String cityB = line.substring(line.indexOf(" to ")+4).trim();
				System.out.println("can I teleport from " + cityA + " to " + cityB+": " + (graph.canReachCity(cityA, cityB) ? "yes" : "no"));
			} else if (line.startsWith("loop possible from")) {
				String citySrc = line.substring(line.indexOf("from ") + 5).trim();
				System.out.println("loop possible from " + citySrc + ": " + (graph.cycleFrom(citySrc) ? "yes" : "no"));
			}
		}
	}

}
