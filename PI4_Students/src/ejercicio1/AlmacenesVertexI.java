package ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public record AlmacenesVertexI(Integer index, List<Set<Integer>> storedProducts, List<Integer> remainSpace) implements AlmacenesVertex {
	
	public static AlmacenesVertexI of(Integer i, List<Set<Integer>> s, List<Integer> r) {
		return new AlmacenesVertexI(i,s,r);
	}
	
	public Boolean goal() {
		return  this.index == DatosAlmacenes.getNumProductos();
	}
	
	public Boolean goalHasSolution() {
		return true;
	}
	
	public String toGraph() {
		return String.format("(%d,%d,%d)", this.index, this.storedProducts, this.remainSpace);
	}
	
	public String toString() {
		return String.format("(%d,%d,%d)", this.index, this.storedProducts, this.remainSpace);
	}
	
	public Boolean isValid() {
		Boolean indexB = this.index() >= 0 && this.index() <= DatosAlmacenes.getNumProductos();
		Boolean capacity = true;
		Boolean incompatibility = true;
		for(int i = 0; i < remainSpace.size(); i ++) {
			if(remainSpace.get(i) > DatosAlmacenes.getMetrosCubicosAlmacen(i)) {
				capacity = false;
			} 
		}
		for(int j = 0; j < this.storedProducts.size(); j++) {
			
		}
		return indexB && capacity;
	}
	
	private Integer parse(String p) {
		String r = p.replace("P", "");
		return Integer.valueOf(r);
	}

	@Override
	public List<Integer> actions() {
		Set<Integer> incompatible = DatosAlmacenes.getProducto(this.index).incompatibilidades().stream()
													.map(p -> parse(p)).collect(Collectors.toSet());
		if(this.index < DatosAlmacenes.getNumProductos()) {
			if(this.index == DatosAlmacenes.getNumProductos() - 1) {
				
			}
				List<Integer> pActions = new ArrayList<>();
				
				for(int i = 0; i < this.remainSpace.size(); i++) {
					if(remainSpace.get(i) + DatosAlmacenes.getMetrosCubicosProducto(this.index) < DatosAlmacenes.getMetrosCubicosAlmacen(i) && 
								!(this.storedProducts.get(i).stream().anyMatch(p -> incompatible.contains(p)))) {
							pActions.add(i);
					}
				}
				pActions.add(DatosAlmacenes.getNumAlmacenes());
				System.out.println("For vertex "+ this.index() +" this actions: "+ pActions); 
				
				return pActions;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public AlmacenesVertex neighbor(Integer a) {
		if(a == DatosAlmacenes.getNumAlmacenes()) {
			return AlmacenesVertexI.of(this.index + 1, this.storedProducts, this.remainSpace);
		}
		List<Set<Integer>> newStoredProducts = new ArrayList<>(this.storedProducts);
		newStoredProducts.get(a).add(this.index);
		List<Integer> newRemainSpace = new ArrayList<>(this.remainSpace);
		newRemainSpace.set(a, newRemainSpace.get(a) + DatosAlmacenes.getMetrosCubicosProducto(this.index));
		return AlmacenesVertexI.of(this.index + 1, newStoredProducts, newRemainSpace);
	}

	@Override
	public AlmacenesEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return AlmacenesEdge.of(this, this.neighbor(a), a);
	}
	
	public Double heuristic() {
		int possible = 0;
		for(int i = this.index; i < DatosAlmacenes.getNumProductos(); i++) {
			int volumeProd = DatosAlmacenes.getMetrosCubicosProducto(i);
			final int product = i;
			
			for(int j = 0; j < this.remainSpace.size(); j++) {
				if(this.remainSpace.get(j) >= volumeProd) {
					Boolean compatible = storedProducts.get(j).stream().noneMatch(k->DatosAlmacenes.sonIncompatibles(k, product)
							|| DatosAlmacenes.sonIncompatibles(product, k));
					if(compatible) {
						possible++;
					}
				}
			}
		}
		return (double) possible;
	}

	@Override
	public Double accionReal() {
		// TODO Auto-generated method stub
		return null;
	}


}
