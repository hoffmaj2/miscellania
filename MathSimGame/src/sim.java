import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Jared Hoffman
 * Completed 1/15/2015
 *
 * A class designed to run and display a cellular simulation on an arbitrary NxN grid, with
 * arbitrary starting values x1, x2, x3, and x4, in its corners, and zero in all other cells, such
 * that each 'step' of the simulation, each cell becomes the maximum of itself and the sum of all adjacent
 * cells if it is composite of zero, and each cell becomes the minimum of itself and the sum of all
 * adjacent cell if it is prime.
 */
public class sim {
	
	public static final Color zero_color = Color.white;//color for cells with a value of zero
	public static final Color lower_composite_color = Color.CYAN;//color for cells with a low composite value
	public static final Color lower_prime_color = Color.black;//color for cells with a low prime value
	public static final Color upper_composite_color = Color.blue;//color for cells with a high composite value
	public static final Color upper_prime_color = Color.gray;//color for cells with a high prime value

	/**
	 * this is the main method; it runs and displays the simulation;
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("select grid size:");
		final int n = in.nextInt();
		System.out.println("\nselect a maximum value:");
		final long mmax = in.nextLong();
		System.out.println("\nselect four initial values:");
		long[] init = new long[4];
		init[0] = in.nextLong();
		init[1] = in.nextLong();
		init[2] = in.nextLong();
		init[3] = in.nextLong();
		in.close();
		System.out.println("\ngenerating...");

		final Stack<long[][]> done = new Stack<long[][]>();//stores moves we've already made
		final Stack<long[][]> undone = new Stack<long[][]>();//stores moves we've undone
		final long[][] grid = new long[(int) n][(int) n];
		grid[0][0] = init[0];
		grid[0][n - 1] = init[1];
		grid[n - 1][0] = init[2];
		grid[n - 1][n - 1] = init[3];

		final JFrame frame = new JFrame();//set up a frame with some default dimensions etc.
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(300, 320));
		frame.setMinimumSize(new Dimension(300, 320));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		final JPanel panel = new JPanel();
		JButton next = new JButton("next step");//the "next" button
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				long[][] temp = new long[n][n];
				sim.eqiv(temp, grid);
				done.push(temp);//push a copy of the current grid to the done stack
				if (undone.isEmpty())
					sim.eqiv(grid, sim.takeStep(grid, mmax));//calculate a new step
				else
					sim.eqiv(grid, undone.pop());//re-do an undone step

				sim.updateToGrid(frame, panel, grid, mmax);//update display
			}
		});
		JButton previous = new JButton("previous step");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!done.isEmpty()) {
					long[][] temp = new long[n][n];
					sim.eqiv(temp, grid);
					undone.push(temp);//push a copy of the current grid to the undone stack
					sim.eqiv(grid, done.pop());//undo a step
					sim.updateToGrid(frame, panel, grid, mmax);//update display
				}
			}
		});
		
		next.setPreferredSize(new Dimension(150, 40));//set button and panel layout stuff
		previous.setPreferredSize(new Dimension(150, 40));
		frame.add(next, BorderLayout.SOUTH);
		frame.add(previous, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(n, n));
		panel.setPreferredSize(new Dimension(200, 200));
		frame.add(panel, BorderLayout.CENTER);
		frame.validate();

		updateToGrid(frame, panel, grid, mmax);//initialize display
	}

	/**
	 * 
	 * changes the values of one 2-d long array to the values of another
	 * 
	 * @param grid the array to change
	 * @param pop the array to change it to
	 */
	protected static void eqiv(long[][] grid, long[][] pop) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = pop[i][j];
			}
		}
	}

	/**
	 * calculates a step of the simulation
	 * 
	 * @param grid the current state of the grid
	 * @param mmax the maximum cell value
	 * @return a new grid (2d long array) representing the simulation's next state
	 */
	protected static long[][] takeStep(long[][] grid, long mmax) {
		long[][] ans = new long[grid.length][grid.length];

		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {//for each cell (i,j)

				long atk = 0;

				if (i > 0)
					atk += grid[i - 1][j];
				if (j > 0)
					atk += grid[i][j - 1];
				if (i < ans.length - 1)
					atk += grid[i + 1][j];
				if (j < ans.length - 1)
					atk += grid[i][j + 1];
				boolean p = isPrime(grid[i][j]);
				boolean b = isPrime(atk);
				if (atk == 0) {
					ans[i][j] = grid[i][j];
				} else if (grid[i][j] == 0) {
					ans[i][j] = atk;
				} else if ((b || p) && (!(b && p))) {
					ans[i][j] = Math.min(grid[i][j], atk);
				} else {
					ans[i][j] = Math.max(grid[i][j], atk);
				}

				if (ans[i][j] > mmax)
					ans[i][j] = ans[i][j] % (mmax + 1);

			}
		}
		return ans;
	}

	/**
	 * boolean functions that returns if a long is a prime
	 * 
	 * @param i the number to check
	 * @return whether or not i is prime
	 */
	private static boolean isPrime(long i) {//look into improving efficiency by memoization 
											//or using matrixes
		if (i == 0)
			return false;
		if (i == 1)
			return false;
		boolean ans = true;
		for (long j = 2; j < ((int)(Math.sqrt(i)) + 1); j++) {
			if (i % j == 0) {
				ans = false;
				break;
			}
		}
		return ans;
	}

	/**
	 * updates a frame and panel to a specific grid with a specified max cell value
	 * 
	 * used for updating the simulation display
	 * 
	 * @param frame the frame that holds the display
	 * @param panel the panel that holds the display
	 * @param grid the grid representing the current state of the simulation
	 * @param mmax the maximum cell value of the simulation
	 */
	private static void updateToGrid(JFrame frame, JPanel panel, long[][] grid,
			long mmax) {
		panel.removeAll();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				JButton b = new JButton("" + grid[i][j]);
				
				//color stuff based on whether it's 0, low composite, high composite, low prime, or high prime
				if (grid[i][j] == 0) {
					b.setBackground(zero_color);
				} else if (isPrime(grid[i][j])) {
					if (grid[i][j] > (mmax / 2))
						b.setBackground(upper_prime_color);
					else {
						b.setBackground(lower_prime_color);
					}
				} else {
					if (grid[i][j] > (mmax / 2))
						b.setBackground(upper_composite_color);
					else {
						b.setBackground(lower_composite_color);
					}
				}
				panel.add(b);
			}
		}
		frame.validate();
	}
	
	/**
	 * basic print method the print a string representation of a grid to the console
	 * 
	 * used for testing purposes
	 * 
	 * @param gr the grid to print
	 */
	public static void printGrid(long[][] gr){
		for(long[] i:gr){
			for(long j:i){
				System.out.print(j+",");
			}
			System.out.print('\n');
		}
	}

}
