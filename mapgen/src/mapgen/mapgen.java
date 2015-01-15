package mapgen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapgen {

	protected static JFrame frame;

	/*
	 * constant colors used for the corresponding terrain feature;
	 */
	private static final Color scrub = new Color(169, 222, 83);
	private static final Color grass = new Color(106, 204, 73);
	private static final Color forest = new Color(47, 112, 25);
	private static final Color desert = new Color(230, 226, 170);
	private static final Color shallow_water = new Color(90, 164, 235);
	private static final Color deep_water = new Color(45, 124, 235);
	private static final Color hill = new Color(177, 224, 202);
	private static final Color mountain = new Color(180, 210, 210);

	/*
	 * size of the grid in cells;
	 */
	private static int xsize = 100;
	private static int ysize = 100;

	/*
	 * size of the cells in pixels;
	 */
	private static final int cellSize = 1;

	/*
	 * smoothing radius;
	 */
	private static final int nradius = 4;
	
	/*
	 * number of iterations of smoothing operation;
	 */
	private static final int numTerrainBlends = 1;

	/*
	 * maximum initial terrain values;
	 */
	private static final double max_growth = 80;
	private static final double max_height = 80;
	private static final double max_wetness = 80;

	/*
	 * constants which tie terrain types to terrain values;
	 */
	private static final double scrub_level = 30;// over
	private static final double grass_level = 40;// over
	private static final double forest_level = 50;// over
	private static final double desert_level = 40;// under
	private static final double mountain_level = 60;// over
	private static final double hill_level = 50;// over
	private static final double water_level = 50;// over
	private static final double water_table = 35;// under
	
	/*
	 * light level used in slightly buggy 'lighting effect'
	 */
	private static final double light_level = 20;

	/*
	 * run terrain simulation and generate map/window;
	 */
	public static void main(String[] args) {

		frame = new JFrame("JMapGen");
		frame.setLayout(new GridLayout(xsize, ysize));

		cell[][] map = new cell[xsize][ysize];
		for (int i = 0; i < xsize; i++) {//populate map
			for (int j = 0; j < ysize; j++) {
				map[i][j] = new cell(Math.random() * max_height, Math.random()
						* max_wetness, Math.random() * max_growth);
			}
		}

		for (int n = 0; n < numTerrainBlends; n++) {//smooth terrain
			cell[][] nitr = new cell[xsize][ysize];

			for (int i = 0; i < xsize; i++) {//generate smoothed map
				for (int j = 0; j < ysize; j++) {
					nitr[i][j] = map[i][j].nextCell(getNeighbors(map, i, j));
				}
			}
			for (int i = 0; i < xsize; i++) {//replace map with smoothed map
				for (int j = 0; j < ysize; j++) {
					map[i][j] = nitr[i][j];
				}
			}
		}

		for (int i = 0; i < xsize; i++) {//create colored panels for each cell in map
			for (int j = 0; j < ysize; j++) {
				JPanel p = new JPanel();
				p.setMaximumSize(new Dimension(cellSize, cellSize));
				p.setMinimumSize(new Dimension(cellSize, cellSize));
				p.setBackground(getCellColor(map,i,j));
				frame.add(p);
			}
		}

		frame.pack();
		frame.validate();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * returns neighbors of each cell
	 */
	private static ArrayList<cell> getNeighbors(cell[][] map, int x, int y) {

		ArrayList<cell> c = new ArrayList<cell>();

		for (int i = -nradius; i < nradius + 1; i++) {
			for (int j = -nradius + Math.abs(i); j < nradius + 1 - Math.abs(i); j++) {
				if ((i != 0 || j != 0)
						&& (x + i < xsize && x + i >= 0 && y + j < ysize && y
								+ j >= 0)) {
					c.add(map[x + i][y + j]);
				}
			}
		}

		return c;
	}

	/*
	 * average the values of an ArrayList of Colors and return the resulting Color
	 */
	private static Color getBlendColor(ArrayList<Color> ca) {

		int r = 0;
		int g = 0;
		int b = 0;

		for (Color c : ca) {
			r += c.getRed();
			g += c.getGreen();
			b += c.getBlue();
		}

		r = r / ca.size();
		g = g / ca.size();
		b = b / ca.size();

		return new Color(r, g, b);
	}

	/*
	 * use terrain values to generate a color for a given cell
	 */
	private static Color getCellColor(cell[][] map, int x, int y) {
		
		cell c = map[x][y];

		ArrayList<Color> cl = new ArrayList<Color>();
		if (c.height >= mountain_level)
			cl.add(mountain);
		else {
			if (c.wetness <= desert_level)
				cl.add(desert);
			else {
				if (c.growth >= grass_level)
					cl.add(grass);
				if (c.height >= hill_level && c.growth >= grass_level)
					cl.add(hill);
				else {
					if (c.growth >= scrub_level)
						cl.add(scrub);
				}
			}
		}
		if (c.growth >= forest_level)
			cl.add(forest);
		if (c.height <= water_table)
			cl.add(deep_water);
		if (c.wetness >= water_level)
			cl.add(shallow_water);

		if (cl.size() == 0)
			cl.add(grass);

		//int rand = (int) (Math.random() * cl.size());
		
		

		Color col = getBlendColor(cl);//.get(rand);
		// Apply height difference
		float[] hsb = new float[3];
		double hu,hl,hr,hd,hul,hrd,hdiff;
		if (y > 0)
			hu = map[x][y-1].height;
		else
			hu = c.height+(map[x][y+1].height-c.height)*-1;
		if (x > 0)
			hl = map[x-1][y].height;
		else
			hl = c.height+(map[x+1][y].height-c.height)*-1;
		if (x < map.length-1)
			hr = map[x+1][y].height;
		else
			hr = c.height+(map[x-1][y].height-c.height)*-1;
		if (y < map[0].length-1)
			hd = map[x][y+1].height;
		else
			hd = c.height+(map[x][y-1].height-c.height)*-1;
		hul = (hu+hl)/2;
		hrd = (hr+hd)/2;
		hdiff = hrd-hul;
		Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), hsb);
		//if (1 > 0)
			//return Color.getHSBColor(0,0,(float) ((hdiff/mapgen.light_level)/2.+1./2.));
		if (hdiff > 0)
			return Color.getHSBColor(hsb[0], (float) (hsb[1]*(1-hdiff/mapgen.light_level)), (float) (1-((1-hsb[2])*(1-hdiff/mapgen.light_level))));
		else
			return Color.getHSBColor(hsb[0], hsb[1], (float) (hsb[2]*(1+hdiff/mapgen.light_level)));
	}

}
