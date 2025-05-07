package ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import us.lsi.common.List2;

public record CourseVertexI(Integer index, List<Integer> selectedCourses, Set<Integer> coveredAreas, Integer remainBudget) implements CourseVertex {
	
	public static CourseVertexI of(Integer i, List<Integer> sC, Set<Integer> cA, Integer rB) {
		return new CourseVertexI(i,sC,cA,rB);
	}
	
	public Boolean goal() {
		return this.index == DatosCursos.getNumCursos();
	}
	
	public Boolean goalHasSolution() {
		int techCourses = numTechCourses();
		boolean moreTechCourses = areMoreTechCourses(techCourses);
		int totalDuration = getTotalDuration();
		int numSelectedCourses = this.selectedCourses.size();
		if(numSelectedCourses == 0) {
			numSelectedCourses = 1;
		}
		boolean enoughTotalDuration = (totalDuration/numSelectedCourses) >= 20;
		boolean positiveRemainBudget = this.remainBudget() >= 0;
		boolean allAreas = hasAllAreas(this.coveredAreas);
		
		
		
		Boolean sol = moreTechCourses && enoughTotalDuration && positiveRemainBudget && allAreas;
		
		return sol;
	}
	private boolean hasAllAreas(Set<Integer> cover) {
		for(int area = 0; area < DatosCursos.getNumAreas(); area++) {
			if(!cover.contains(area)) {
				return false;
			}
		}
		return true;
	}
	
	private Integer numTechCourses() {
		int techCourses = 0;
		for(int i = 0; i < this.selectedCourses.size(); i ++) {
			if(DatosCursos.getArea(selectedCourses.get(i)) == 0) {
				techCourses ++;
			}
		}
		return techCourses;
	}
	
	private boolean areMoreTechCourses(Integer techCourses) {
		Boolean moreTechCourses = true;
		
		for(int k = 1; k < DatosCursos.getNumAreas(); k ++) {
			int acc = 0;
			if(this.index < DatosCursos.getNumCursos()) {
				if(DatosCursos.getArea(this.index) == k) {
					acc ++;
				}
			}
			
			for(int j = 0; j < this.selectedCourses.size(); j ++) {
				if(DatosCursos.getArea(selectedCourses.get(j)) == k) {
					acc ++;
				}
			}
			if(acc > techCourses) {
				moreTechCourses = false;
				break;
			}
		}
		
		return moreTechCourses;
	}
	
	private Integer getTotalDuration() {
		int totalDuration = 0;
		for(int i = 0; i < this.selectedCourses.size(); i ++) {
			totalDuration = totalDuration + DatosCursos.getDuracion(this.selectedCourses().get(i));
		}
		return totalDuration;
	}

	@Override
	public List<Integer> actions() {
		
		Boolean enoughBudget = DatosCursos.getCoste(this.index) <= this.remainBudget;
		Set<Integer> future = new HashSet<>(this.coveredAreas());
		future.add(DatosCursos.getArea(this.index()));
		Boolean allAreas = hasAllAreas(future);
		int techCourses = numTechCourses();
		Boolean moreTechCourses = areMoreTechCourses(techCourses);
		int totalDuration = getTotalDuration();
		Boolean enoughAvgDuration = (totalDuration + DatosCursos.getDuracion(this.index))/(selectedCourses.size() + 1) >= 20;
		
		if(this.index >= DatosCursos.getNumCursos()) {
			return List2.of();
		} else if (this.index == DatosCursos.getNumCursos() - 1) {
			
			if(enoughBudget && allAreas && moreTechCourses && enoughAvgDuration) {
				return List2.of(1);
			} else if(!(enoughAvgDuration) || !(allAreas) || !(moreTechCourses)) {
				return List2.of();
			} else {
				return List2.of(0);
			}	
		} else {
			if(enoughBudget) {
			 return List2.of(0,1);
			} else {
				if(allAreas && moreTechCourses && enoughAvgDuration) {
					return List2.of(0);
				} else {
					return List2.of();
				}
				
			}
			
		}
	}

	@Override
	public CourseVertexI neighbor(Integer a) {
		List<Integer> newSelectedCourses = new ArrayList<>(this.selectedCourses);
		Set<Integer> newCoveredAreas = new HashSet<>(this.coveredAreas);
		if(a == 0) {
			return CourseVertexI.of(this.index + 1, newSelectedCourses , newCoveredAreas , this.remainBudget);
		} else if (a == 1) {
			newSelectedCourses.add(this.index);
			newCoveredAreas.add(DatosCursos.getArea(this.index));
			Integer newRemainBudget = this.remainBudget() - DatosCursos.getCoste(this.index);
			return CourseVertexI.of(this.index + 1, newSelectedCourses, newCoveredAreas, newRemainBudget);
		} else {
			throw new IllegalArgumentException("Action must be 0 or 1");
		}
	}

	@Override
	public CourseEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return CourseEdge.of(this, this.neighbor(a), a);
	}

}
