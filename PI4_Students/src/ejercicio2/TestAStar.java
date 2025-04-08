package ejercicio2;

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
			DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada"+id_fichero+".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			
			CourseVertex start = CourseVertex.start();
			
			System.out.println("#### Algoritmo A* ####");
			
			EGraph<CourseVertex, CourseEdge> graph = EGraph.virtual(start)
														  	.pathType(PathType.Sum)
														  	.type(Type.Max)
														  	.edgeWeight(x -> x.weight())
														  	.heuristic(CourseHeuristic::heuristic)
														  	.build();
			
			AStar<CourseVertex, CourseEdge,?> aStar = AStar.ofGreedy(graph);
			
			GraphPath<CourseVertex, CourseEdge> gp = aStar.search().get();
			
			List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
					.collect(Collectors.toList());
			
			SolucionCursos s_as = SolucionCursos.create(gp_as);
			
			System.out.println(s_as);
			System.out.println(gp_as);
			System.out.println("========================================================================================================");

		}
		
		
	}

}
