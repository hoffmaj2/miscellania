package mapgen;

import java.util.ArrayList;

/*
 * cell class used to store terrain values
 */
public class cell {
	
	//values should range from 0 to mapgen's max values
	protected double height;
	protected double wetness;
	protected double growth;
	
	/*
	 * create a new cell initialized to 0,0,0
	 */
	public cell(){
		height = 0;
		wetness = 0;
		growth = 0;
	}
	
	/*
	 * create a new cell initialized to specified inputs
	 */
	public cell(double h, double w, double g){
		height = h;
		wetness = w;
		growth = g;
	}

	/*
	 * Returns a new cell who's values are the average of this cell's and its neighbors'
	 */
	public cell nextCell(ArrayList<cell> neighbors) {
		
		if(neighbors == null || neighbors.size() == 0) {//should never happen, but never trust a user!
			System.out.println("cell has no neighbors?!");
			return this;
		}
		
		double sf = 1;
		
		double h = Math.pow(height, sf);
		double w = Math.pow(wetness, sf);
		double g = Math.pow(growth, sf);
		
		for(cell c : neighbors){
			h+=Math.pow(c.height,sf);
			w+=Math.pow(c.wetness,sf);
			g+=Math.pow(c.growth,sf);
		}
		
		h = h/(neighbors.size()+1);
		w = w/(neighbors.size()+1);
		g = g/(neighbors.size()+1);
		
		h = Math.pow(h, 1.0/sf);
		w = Math.pow(w, 1.0/sf);
		g = Math.pow(g, 1.0/sf);
		
		
		return new cell(h,w,g);
	}
	
}
