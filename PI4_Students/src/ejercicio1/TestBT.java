package ejercicio1;

import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {
		
		
		Locale.setDefault(Locale.of("en", "US"));
		
		for(int id_fichero = 1; id_fichero <= 3; id_fichero++) {
			DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada"+id_fichero+".txt");
			System.out.println("=============");
			System.out.println("\tResultados para el test " + id_fichero + "\n");
			
			DatosAlmacenes.toConsole();
			
			AlmacenesVertex start = AlmacenesVertex.start();
			
			System.out.println("\n#### Algoritmo BT ####");
			
			EGraph<AlmacenesVertex, AlmacenesEdge> graph =
					EGraph.virtual(start)
							.pathType(PathType.Sum)
							.type(Type.Max)
							.edgeWeight(x -> x.weight())
							.heuristic(AlmacenesHeuristic :: heuristic)
							.build();
			
			
			GreedyOnGraph<AlmacenesVertex, AlmacenesEdge> rr = GreedyOnGraph.of(graph);
			
			GraphPath<AlmacenesVertex, AlmacenesEdge> r = rr.path();
			
			System.out.println("Voraz = "+r.getWeight()+"  == "+SolucionAlmacen.of(r));
			
			BT<AlmacenesVertex, AlmacenesEdge, SolucionAlmacen> bta = BT.of(graph, 
					SolucionAlmacen::of, null, null, true);
			if(rr.isSolution(r)) {
				bta = BT.of(graph, SolucionAlmacen::of, r.getWeight(), r, true);
			}
			
			Optional<GraphPath<AlmacenesVertex, AlmacenesEdge>> gp = bta.search();
			System.out.println(SolucionAlmacen.of(gp.get()));
			}

	}

}
