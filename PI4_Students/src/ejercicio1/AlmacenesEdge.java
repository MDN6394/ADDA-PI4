package ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;


public record AlmacenesEdge(AlmacenesVertex source,AlmacenesVertex target,Integer action, Double weight)
	implements SimpleEdgeAction<AlmacenesVertex,Integer>{
	
	public static AlmacenesEdge of(AlmacenesVertex c1, AlmacenesVertex c2, Integer action) {
		AlmacenesEdge a = new AlmacenesEdge(c1, c2, action, action * 1.0);
		return a;
	}

}
