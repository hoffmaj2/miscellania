package mapgen2;

import java.util.ArrayList;
import java.util.HashSet;

public class Cell {

	private int type;
	
	private HashSet<Cell> neighbors;
	
	private HashSet<Cell> emptyNeighbors;
	
	public Cell(){
		type = -1;
		neighbors = new HashSet<Cell>();
		emptyNeighbors = new HashSet<Cell>();
	}
	
	public void setType(int t){
		type = t;
	}
	
	public int getType(){
		return type;
	}
	
	public void addNeighbor(Cell c){
		neighbors.add(c);
		emptyNeighbors.add(c);
	}

	public int makeType() {
		
		ArrayList<Cell> cl = new ArrayList<Cell>();
		
		for(Cell c : neighbors){
			if(c.type > -1){
				cl.add(c);
			}
			c.emptyNeighbors.remove(this);
		}
		this.type = Terrains.generateTypeFromCells(cl);
		//System.out.println("type: "+type);
		if(emptyNeighbors.isEmpty())return 1;
		int i = (int) (Math.random()*emptyNeighbors.size());
		return 1 + ((Cell)(emptyNeighbors.toArray()[i])).makeType();
	}
	
	public Object[] getNeighbors(){
		return this.neighbors.toArray();
	}

	public int makeType(int neodefault) {
		ArrayList<Cell> cl = new ArrayList<Cell>();
		
		for(Cell c : neighbors){
			if(c.type > -1){
				cl.add(c);
			}
			c.emptyNeighbors.remove(this);
		}
		this.type = Terrains.generateTypeFromCells(cl,neodefault);
		//System.out.println("type: "+type);
		if(emptyNeighbors.isEmpty())return 1;
		int i = (int) (Math.random()*emptyNeighbors.size());
		return 1 + ((Cell)(emptyNeighbors.toArray()[i])).makeType();
	}
}
