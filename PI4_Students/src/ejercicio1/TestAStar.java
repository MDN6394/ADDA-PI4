package ejercicio1;

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
			DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			
			AlmacenesVertex start = AlmacenesVertex.start();
			
			System.out.println("#### Algoritmo A* ####");
			
			EGraph<AlmacenesVertex, AlmacenesEdge> graph =
					EGraph.virtual(start)
							.pathType(PathType.Sum)
							.type(Type.Max)
							.edgeWeight(x -> x.weight())
							.heuristic(AlmacenesHeuristic :: heuristic)
							.build();
			
			
			AStar<AlmacenesVertex, AlmacenesEdge,?> aStar = AStar.ofGreedy(graph);
			
			GraphPath<AlmacenesVertex, AlmacenesEdge> gp = aStar.search().get();
			
			List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
					.collect(Collectors.toList());
			
			SolucionAlmacen s_as = SolucionAlmacen.create(gp_as);

			System.out.println(s_as);
			System.out.println(gp_as);
			System.out.println("========================================================================================================");
		}
	}
}
