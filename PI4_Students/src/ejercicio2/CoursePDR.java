package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;





public class CoursePDR {
	
	public static record partialSolution(Integer action, Double weight) implements Comparable<partialSolution> {
		
		public static partialSolution of(Integer action, Double weight) {
			return new partialSolution(action,weight);
		}

		@Override
		public int compareTo(partialSolution o) {
			return this.weight().compareTo(o.weight());
		}
		
	}
	
	private  static Map<CourseVertexI, partialSolution> memory;
	private static Double bestOF;
	private static CourseVertexI start;
	
	public static SolucionCursos pd(CourseVertexI initialProblem, Double maxValue) {
		start = initialProblem;
		bestOF = maxValue;
		memory = new HashMap<>();
		pd(start,bestOF,memory);
		SolucionCursos r = solucion();
		return r;
		
	}

	private static partialSolution pd(CourseVertexI problem, Double acumOF, Map<CourseVertexI, partialSolution> memory2) {
		partialSolution ret = null;
		if(memory2.containsKey(problem)) {
			ret = memory2.get(problem);
		} else if (problem.index() == DatosCursos.getNumCursos()) {
			ret = partialSolution.of(null, 0.0);
			memory.put(problem, ret);
			if(acumOF > bestOF) {
				bestOF = acumOF;
			}
		} else {
			List<partialSolution> solByActions = new ArrayList<>();
			for(Integer a: problem.actions()) {
				Double weight = (double) DatosCursos.getRelevancia(problem.index())*a;
				
				partialSolution solA = pd(problem.neighbor(a), acumOF + weight, memory);
				if(solA != null) {
					solByActions.add(partialSolution.of(a, solA.weight() + weight));
				}
			}
			if(!solByActions.isEmpty()) {
				ret = solByActions.stream().max(Comparator.naturalOrder()).orElse(null);
				memory.put(problem, ret);
			}
		}
		return ret;
		
		
	}

	private static SolucionCursos solucion() {
		List<Integer> actions = new ArrayList<>();
		CourseVertexI v = start;
		partialSolution s = memory.get(v);
		if(s == null) {
			return SolucionCursos.create(new ArrayList<>());
		}
		while(s.action() != null) {
			actions.add(s.action());
			v = v.neighbor(s.action());
			s = memory.get(v);
		}
		return SolucionCursos.create(actions);
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			CourseVertexI ini = CourseVertex.start();
			pd(ini, 0.0);
			System.out.println(solucion().toString());
		}

	}

}
