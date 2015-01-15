package mapgen2;

import java.awt.Color;
import java.util.ArrayList;

public class Terrains {

	private static final int size = 8;
	private static final Color[] t = {new Color(169, 222, 83),new Color(106, 204, 73),
		new Color(47, 112, 25),new Color(230, 226, 170),new Color(90, 164, 235),new Color(45, 124, 235),
		new Color(177, 224, 202),new Color(180, 210, 210)};
	// in order: scrub, grass, forest, desert, shallow water, deep water, hill, mountain
	private static final double[][] tp = {{1000,2,0.5,3,0.5,0,0.5,1},{2,1000,3,0.5,2,0,3,1},
		{1,3,1000,0.5,2,0,2,1},{2,0.5,0.5,1000,1,0,0.5,1},{10,30,1,30,800,0,2,1},
		{4,8,4,4,400,8000,4,4},{1,3,3,1,2,0,400,30},{1,1,1,1,0.5,0,200,1000}};
	//probabilities of each of the terrains generating each other terrain as a double, in order
	
	private static final int defaultType = 5;
	
	public static int getSize(){
		return size;
	}
	
	public static int generateTypeFromCells(ArrayList<Cell> cl){
		int ans = 0;
		
		double totalProb = 0;
		double[] probs = new double[8];
		
		for(Cell c : cl){
			double[] d = tp[c.getType()];
			for(int i=0;i<size;i++){
				probs[i]+=d[i];
				totalProb+=d[i];
			}
		}
		
		if(totalProb == 0){
			return defaultType;
		}
		
		double rand = Math.random()*totalProb;
		//System.out.println("rand is: "+rand);
		
		totalProb = 0.0;
		for(ans = 0;ans<size;ans++){
			totalProb += probs[ans];
			if(totalProb>=rand)break;
		}
		
		//System.out.println("ans is: "+ans);
		
		return ans;
	}
	
	public static int generateTypeFromCells(ArrayList<Cell> cl,int df){
		int ans = 0;
		
		double totalProb = 0;
		double[] probs = new double[8];
		
		for(Cell c : cl){
			double[] d = tp[c.getType()];
			for(int i=0;i<size;i++){
				probs[i]+=d[i];
				totalProb+=d[i];
			}
		}
		
		if(totalProb == 0){
			return df;
		}
		
		double rand = Math.random()*totalProb;
		//System.out.println("rand is: "+rand);
		
		totalProb = 0.0;
		for(ans = 0;ans<size;ans++){
			totalProb += probs[ans];
			if(totalProb>=rand)break;
		}
		
		//System.out.println("ans is: "+ans);
		
		return ans;
	}

	public static Color getCellColor(int type) {
		return t[type];
	}
	
}
