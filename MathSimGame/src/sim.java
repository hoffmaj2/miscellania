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


public class sim {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("select grid size:");
		int n = in.nextInt();
		System.out.println("\nselect a maximum value:");
		final long mmax = in.nextLong();
		System.out.println("\nselect four initial values:");
		long[] init = new long[4];
		init[0]=in.nextLong();
		init[1]=in.nextLong();
		init[2]=in.nextLong();
		init[3]=in.nextLong();
		in.close();
		System.out.println("\ngenerating...");
		
		final Stack<long[][]> done = new Stack<long[][]>();
		final Stack<long[][]> undone = new Stack<long[][]>();
		final long[][] grid = new long[(int) n][(int) n];
		grid[0][0]=init[0];
		grid[0][n-1]=init[1];
		grid[n-1][0]=init[2];
		grid[n-1][n-1]=init[3];
		
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(300,320));
		frame.setMinimumSize(new Dimension(300,320));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		final JPanel panel = new JPanel();
		JButton next = new JButton("next step");
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				done.push(grid);
				if(undone.isEmpty())
				sim.eqiv(grid,sim.takeStep(grid,mmax));
				else sim.eqiv(grid,undone.pop());
				
				sim.updateToGrid(frame, panel, grid,mmax);
			}
		});
		JButton previous = new JButton("previous step");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!done.isEmpty()){
					undone.push(grid);
					sim.eqiv(grid, done.pop());
					System.out.println("KKK");
					sim.updateToGrid(frame, panel, grid,mmax);
				}
			}
		});
		next.setPreferredSize(new Dimension(150,40));
		previous.setPreferredSize(new Dimension(150,40));
		frame.add(next,BorderLayout.SOUTH);
		frame.add(previous,BorderLayout.NORTH);
		panel.setLayout(new GridLayout(n,n));
		panel.setPreferredSize(new Dimension(200,200));
		frame.add(panel,BorderLayout.CENTER);
		frame.validate();
		
		updateToGrid(frame,panel,grid,mmax);
	}

	protected static void eqiv(long[][] grid, long[][] pop) {
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid.length;j++){
				grid[i][j]=pop[i][j];
			}
		}
	}

	protected static long[][] takeStep(long[][] grid, long mmax) {
		long[][] ans = new long[grid.length][grid.length];
		
		for(int i=0;i<ans.length;i++){
			for(int j=0;j<ans.length;j++){
				
				long atk = 0;
				
				if(i>0)atk+=grid[i-1][j];
				if(j>0)atk+=grid[i][j-1];
				if(i<ans.length-1)atk+=grid[i+1][j];
				if(j<ans.length-1)atk+=grid[i][j+1];
				boolean p = isPrime(grid[i][j]);
				boolean b = isPrime(atk);
				if(atk==0){
					ans[i][j]=grid[i][j];
				}
				else if(grid[i][j]==0){
					ans[i][j]=atk;
				}
				else if((b||p)&&(!(b&&p))){
					ans[i][j] = Math.min(grid[i][j], atk);
				}
				else{
					ans[i][j] = Math.max(grid[i][j], atk);
				}
				
				if(ans[i][j]>mmax)ans[i][j] = ans[i][j]%(mmax+1);
				
			}
		}
		return ans;
	}

	private static boolean isPrime(long i) {//not as efficient as it could be... but who cares
		if(i==0)return false;
		if(i==1)return false;
		boolean ans=true;
		for(long j=2;j<(i/2)+1;j++){
			if(i%j==0){ans=false;
			break;}
		}
		return ans;
	}

	private static void updateToGrid(JFrame frame, JPanel panel, long[][] grid, long mmax) {
		panel.removeAll();
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid.length;j++){
				JButton b = new JButton(""+grid[i][j]);
				if(grid[i][j]==0){
					b.setBackground(Color.pink);
				}
				else if(isPrime(grid[i][j])){
					if(grid[i][j]>(mmax/2))b.setBackground(Color.red);
					else{
						b.setBackground(Color.black);
					}
				}
				else {
					if(grid[i][j]>(mmax/2))b.setBackground(Color.blue);
					else{
						b.setBackground(Color.CYAN);
					}
				}
				panel.add(b);
			}
		}
		frame.validate();
	}

}
