package ejercicio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public interface AlmacenesVertex extends VirtualVertex<AlmacenesVertex,AlmacenesEdge, Integer>{
	
	Integer index();
	List<Set<Integer>> storedProducts();
	List<Integer> remainSpace();
	
	public static AlmacenesVertex start() {
		List<Set<Integer>> stored = new ArrayList<>();
		List<Integer> remainSpace = new ArrayList<>();
		for(int i = 0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
			stored.add(new HashSet<>());
			remainSpace.add(0);
			
		}
		return new AlmacenesVertexI(0, stored, remainSpace);
	}

}
