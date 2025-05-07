package ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public interface CourseVertex extends VirtualVertex<CourseVertex, CourseEdge, Integer>{
	Integer index();
	List<Integer> selectedCourses();
	Set<Integer> coveredAreas();
	Integer remainBudget();
	
	public static CourseVertexI start() {
		List<Integer> selected = new ArrayList<>();
		Set<Integer> covered = new HashSet<>();
		Integer remain = DatosCursos.getPresupuestoTotal();
		return new CourseVertexI(0, selected, covered, remain);
	}
	

}
