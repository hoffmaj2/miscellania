package mapgen2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapgen2 {

	
	protected static JFrame frame;

	private static int xsize = 100;
	private static int ysize = 100;

	private static final int cellSize = 1;

	private static final int nradius = 1;
	private static final int neodefault = 1;
	private static final int oridefault = 5;
	private static final int numdefs = 30;
	
	public static void main(String[] args) {

		try{
		frame = new JFrame("JMapGen");
		frame.setLayout(new GridLayout(xsize, ysize));

		Cell[][] map = new Cell[xsize][ysize];
		
		int emptyCells = xsize*ysize;
		
		for(int i=0;i<xsize;i++){
			for(int j=0;j<ysize;j++){
				map[i][j] = new Cell();
				for(int k=-nradius; k<1;k++){
					for(int l=-nradius+Math.abs(k); l<1;l++){
						if(!(k==0&&l==0)&&(i+k>-1&&i+k<xsize)&&(j+l>-1&&j+l<ysize)){
							map[i][j].addNeighbor(map[i+k][j+l]);
							map[i+k][j+l].addNeighbor(map[i][j]);
						}
					}
				}
			}
		}
		
		int dftracker = 0;
		
		while(emptyCells>0){
			//System.out.println(emptyCells);
			int x = (int) (Math.random()*xsize);
			int y = (int) (Math.random()*xsize);
			if(dftracker<numdefs){
				if(map[x][y].getType()<0)emptyCells -= map[x][y].makeType(oridefault);
				dftracker++;
			}
			else{
				if(map[x][y].getType()<0)emptyCells -= map[x][y].makeType(neodefault);
				dftracker++;
			}
			//System.out.println("recType: "+map[x][y].getType());
		}
		
		for(int i=0;i<xsize;i++){
			for(int j=0;j<ysize;j++){
				int num = ((Cell)(map[i][j].getNeighbors()[0])).getType();
				boolean isall = true;
				for(Object o : map[i][j].getNeighbors()){
					Cell c = (Cell)o;
					if(c.getType()!=num){isall = false; break;}
				}
				if(isall)map[i][j].setType(num);
			}
		}
		
		for(int i=0;i<xsize;i++){
			for(int j=0;j<ysize;j++){
				JPanel p = new JPanel();
				//p.setMaximumSize(new Dimension(cellSize, cellSize));
				p.setPreferredSize(new Dimension(cellSize, cellSize));
				//System.out.println("Adding: "+map[i][j].getType()+" at ("+i+","+j+")");
				p.setBackground(Terrains.getCellColor(map[i][j].getType()));
				frame.add(p);
			}
		}
		
		frame.pack();
		frame.validate();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
		catch(Exception e){
			//ignore for now
		}
	}
	
}
