import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Tests {

	public TeleportationGraph graph;
	
	@Test
	void test() {
		graph = new TeleportationGraph();
		graph.linkCities("Fortuna", "Hemmingway");
		graph.linkCities("Fortuna", "Atlantis");
		graph.linkCities("Hemmingway", "Chesterfield");
		graph.linkCities("Chesterfield", "Springton");
		graph.linkCities("Los Amigos", "Paristown");
		graph.linkCities("Paristown", "Oaktown");
		graph.linkCities("Los Amigos", "Oaktown");
		graph.linkCities("Summerton", "Springton");
		graph.linkCities("Summerton", "Hemmingway");
		
		String jumpTest1 = graph.citiesInJumps("Summerton", 1);
		String jumpTest2 = graph.citiesInJumps("Summerton", 2);
		assertTrue(jumpTest1.contains("Hemmingway"));
		assertTrue(jumpTest1.contains("Springton"));
		assertFalse(jumpTest1.contains("Fortuna"));
		assertTrue(jumpTest2.contains("Hemmingway"));
		assertTrue(jumpTest2.contains("Springton"));
		assertTrue(jumpTest2.contains("Chesterfield"));
		assertTrue(jumpTest2.contains("Fortuna"));
		
		assertTrue(graph.canReachCity("Springton", "Atlantis"));
		assertFalse(graph.canReachCity("Oaktown", "Atlantis"));
		
		assertTrue(graph.cycleFrom("Oaktown"));
		assertTrue(graph.cycleFrom("Paristown"));
		assertFalse(graph.cycleFrom("Fortuna"));
		assertTrue(graph.cycleFrom("Hemmingway"));
	}

}
