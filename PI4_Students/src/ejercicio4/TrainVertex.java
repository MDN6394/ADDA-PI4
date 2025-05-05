package ejercicio4;

import java.util.ArrayList;
import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public interface TrainVertex extends VirtualVertex<TrainVertex, TrainEdge, Integer>{
	Integer index();
	List<Integer> path();
	Double acumCost();
	
	public static TrainVertex start() {
		
		List<Integer> path = new ArrayList<>();
		path.add(0);
		return new TrainVertexI(0,path,0.0);
		
	}
}
