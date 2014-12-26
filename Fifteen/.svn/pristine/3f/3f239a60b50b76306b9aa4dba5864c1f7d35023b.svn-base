import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FifteenListener implements ActionListener {

	FifteenFrame frame;//the FifteenFrame that created this
	int x;//the x position of the button this is applied to
	int y;//the y position of the button this is applied to
	
	/**
	 * This constructor generates a new FifteenListener with frame=f, x=col, and y=row.
	 * 
	 * @param f Placed into frame
	 * @param row Placed into y
	 * @param col Placed into x
	 */
	public FifteenListener (FifteenFrame f,int row,int col){
		frame=f;
		y=row;
		x=col;
	}
	
	
	/**
	 * This method responds to an ActionEvent and, 
	 * if the button this is applied to is in the correct position, shifts the blank space on the FifteenFrame.
	 */
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0;i<frame.grid.length;i++){
			for(int j=0;j<frame.grid[0].length;j++){
				if(frame.grid[i][j]==0){
									
					if(y==i-1&&x==j){
						frame.shift(4);
						frame.checkWin();
						frame.recordMove(4);
					}
					else if(y==i+1&&x==j){
						frame.shift(2);
						frame.checkWin();
						frame.recordMove(2);
					}
					else if(y==i&&x==j+1){
						frame.shift(1);
						frame.checkWin();
						frame.recordMove(1);
					}
					else if(y==i&&x==j-1){
						frame.shift(3);
						frame.checkWin();
						frame.recordMove(3);
					}
					
					
				}
			}

		}

	}

}
