package ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CourseEdge(CourseVertex source, CourseVertex target, Integer action, Double weight) implements SimpleEdgeAction<CourseVertex, Integer> {
	
	public static CourseEdge of(CourseVertex c1, CourseVertex c2, Integer action) {		
		return new CourseEdge(c1,c2,action, (double) action*DatosCursos.getRelevancia(c1.index()));	
	}

}
