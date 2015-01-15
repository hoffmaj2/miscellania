package tcika;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LenuButton extends JPanel {
	
	private Lenu lenu;
	private JButton infoButton;
	private JButton removalButton;
	private Tcika_by by;
	private JPanel jp;
	
	public LenuButton(Lenu l, Tcika_by b){
		by = b;
		jp = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		lenu = l;
		infoButton = new JButton(lenu.getTimeAsString());
		removalButton = new JButton("X");
		this.infoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(new JFrame(),lenu.toString());
			}
		});
		this.removalButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				by.interior.remove(LenuButton.this);
				by.pack();
				by.validate();
			}
		});
		removalButton.setBackground(Color.red);
		this.jp.setBackground(Color.blue);
		this.jp.add(infoButton);
		this.jp.add(removalButton);
		this.add(jp);
	}
	
	public Lenu getLenu(){
		return lenu;
	}
	
}
