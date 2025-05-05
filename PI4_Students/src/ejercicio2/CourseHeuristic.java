package ejercicio2;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;


import us.lsi.common.List2;




public class CourseHeuristic {
	
	public static Double heuristic(CourseVertex c1, Predicate<CourseVertex> goal, CourseVertex c2) {
		Set<Integer> remainingAreas = new HashSet<>(List2.rangeList(0, DatosCursos.getNumAreas()));
		remainingAreas.removeAll(c1.coveredAreas());
		
		int budget = c1.remainBudget();
		int possibleScore = 0;
		for(int i = c1.index(); i < DatosCursos.getNumCursos(); i++) {
			int cost = DatosCursos.getCoste(i);
			if(cost <= budget) {
				int area = DatosCursos.getArea(i);
				possibleScore += DatosCursos.getRelevancia(i);
				remainingAreas.remove(area);
			}
		}
		
		if(!remainingAreas.isEmpty()) {
			return Double.NEGATIVE_INFINITY;
		}
		
		return (double) possibleScore;
		
		//return (double) IntStream.range(c1.index(), DatosCursos.getNumCursos()).map(i -> DatosCursos.getRelevancia(i)).sum();
	}

}
