package ejercicio4;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAStar {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosTren.iniDatos("resources/ejercicio4/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			
			TrainVertex start = TrainVertex.start();
			
			System.out.println("#### Algoritmo A* ####");
			
			EGraph<TrainVertex, TrainEdge> graph = EGraph.virtual(start)
														 .pathType(PathType.Sum)
														 .type(Type.Min)
														 .edgeWeight(x -> x.weight())
														 .heuristic(TrainHeuristic::heuristic)
														 .build();
			
			AStar<TrainVertex, TrainEdge, ?> aStar = AStar.ofGreedy(graph);		
			
			GraphPath<TrainVertex, TrainEdge> gp = aStar.search().get();
			
			List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
					.collect(Collectors.toList());
			
			SolucionEstaciones s_as = SolucionEstaciones.create(gp_as);
			
			System.out.println(s_as);
			System.out.println(gp_as);
			System.out.println("Last vertex: " + gp.getEndVertex());
			System.out.println("Neighbors of last vertex: " + graph.edgesListOf(gp.getEndVertex()));
			System.out.println("========================================================================================================");
		
		
		}
	}

}
