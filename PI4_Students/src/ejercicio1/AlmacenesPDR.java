package ejercicio1;

import java.util.*;
import java.util.stream.Collectors;

public class AlmacenesPDR {
	
	public static record partialSolution(Integer action, Double weight) implements Comparable<partialSolution> {
		
		public static partialSolution of(Integer action, Double weight) {
			return new partialSolution(action,weight);
		}

		@Override
		public int compareTo(partialSolution o) {
			return this.weight().compareTo(o.weight());
		}
		
	}
	
	private  static Map<AlmacenesVertexI, partialSolution> memory;
	private static Double bestOF;
	private static  AlmacenesVertexI start;
	
	public static SolucionAlmacen pd(AlmacenesVertexI initialProblem, Double maxValue) {
		start = initialProblem;
		bestOF = maxValue;
		memory = new HashMap<>();
		pd(start, 0.0, memory);
		SolucionAlmacen r = solucion();
		return r;
		
	}

	private static SolucionAlmacen solucion() {
		List<Integer> actions = new ArrayList<>();
		AlmacenesVertexI v = start;
		partialSolution s = memory.get(v);
		if(s == null) {
			return SolucionAlmacen.empty();
		}
		while(s.action() != null) {
			actions.add(s.action());
			v = v.neighbor(s.action());
			s = memory.get(v);
		}
		return SolucionAlmacen.create(actions);
	}

	private static partialSolution pd(AlmacenesVertexI problem, Double acumOF, Map<AlmacenesVertexI, partialSolution> memory2) {
		partialSolution ret = null;
		if(memory.containsKey(problem)) {
			ret = memory.get(problem);
		} else if (problem.index() == DatosAlmacenes.getNumProductos()) {
			ret = partialSolution.of(null, 0.0);
			memory.put(problem, ret);
			if(acumOF > bestOF) {
				bestOF = acumOF;
			}
		} else {
			List<partialSolution> solByActions = new ArrayList<>();
			for(Integer a: problem.actions()) {
				Double weight = 0.0;
				if(a > -1) {
					weight = 1.0;
				}
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
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			AlmacenesVertexI ini = AlmacenesVertex.start();
			pd(ini, 0.0);
			System.out.println(solucion().toString());
		}
		
		
	}

}
