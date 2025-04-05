package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public interface AlmacenesVertex extends VirtualVertex<AlmacenesVertex,AlmacenesEdge, Integer>{
	
	Integer index();
	List<Set<Integer>> storedProducts();
	List<Integer> remainSpace();
	Double accionReal();
	
	public static AlmacenesVertex start() {
		return new AlmacenesVertexI(0, new ArrayList<>(), new ArrayList<>());
	}

}
