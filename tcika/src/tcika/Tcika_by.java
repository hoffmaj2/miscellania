package tcika;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Tcika_by extends JFrame{
	
	private GregorianCalendar now;
	
	private JButton addButton;
	protected JPanel interior;
	
	private Timer t;
	protected Toolkit tk;

	private static final int tfrequency = 60;
	
	public Tcika_by(){
		now = new GregorianCalendar();
		this.tk = Toolkit.getDefaultToolkit();
		this.t = new Timer();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.interior = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		interior.setBorder(padding);
		this.setLayout(new BorderLayout());
		this.interior.setLayout(new BoxLayout(interior, BoxLayout.Y_AXIS));
		this.add(interior,BorderLayout.CENTER);
		this.addButton = new JButton("Add Event");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Tcika_by.this.addLenu(JOptionPane.showInputDialog("What time is the event? (hour, minute, day, month, year)"),
						JOptionPane.showInputDialog("Describe the event"));
			}
		});
		this.add(addButton,BorderLayout.NORTH);
		this.pack();
	}

	protected void addLenu(String s1, String s2) {
		
		if(s1 == null || s1.length() == 0){
			JOptionPane.showMessageDialog(new JFrame(),
				    "Error: Some of your input is invalid;\nRemember to only use numbers separated by spaces and commas.",
				    "Invalid Input",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		s1 = s1.replaceAll(",", " ");
		s1 = s1.replaceAll(":", " ");
		s1 = s1.replaceAll("  ", " ");
		s1 = s1.toLowerCase();
		String[] s11 = s1.split(" ");
		if(s11.length>1){
			String temp = s11[0];
			s11[0] = s11[1];
			s11[1] = temp;
		}
		else if(s11.length == 1)
		{s11 = new String[]{"0",s11[0]};}
		int[] i1 = new int[s11.length];
		
		if(s11.length == 0){
			JOptionPane.showMessageDialog(new JFrame(),
				    "Error: Some of your input is invalid;\nRemember to only use numbers separated by spaces and commas.",
				    "Invalid Input",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		try{
		for(int i=0; i<s11.length; i++){
			i1[i] = Integer.parseInt(s11[i]);
		}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(new JFrame(),
				    "Error: Some of your input is invalid;\nRemember to only use numbers separated by spaces and commas.",
				    "Invalid Input",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.interior.add(new LenuButton(new Lenu(this, i1, s2), this));
		this.pack();
		this.validate();
	}
	
	public void tick(){
		now = new GregorianCalendar();
		this.scan();
		t.schedule(new ScanTask(), tfrequency *1000);
	}
	
	private void scan() {
		for(Component lb : this.interior.getComponents()){
			if(lb.getClass() == new LenuButton(new Lenu(this), this).getClass()){
				if(((LenuButton)lb).getLenu().test(fullTime())){
					((LenuButton)lb).getLenu().sendMessage();
				}
			}
		}
	}
	
	class ScanTask extends TimerTask{

		@Override
		public void run() {
			Tcika_by.this.tick();
		}
		
	}

	public int day(){
		return now.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	public int month(){
		return now.get(GregorianCalendar.MONTH)+1;
	}
	
	public int year(){
		return now.get(GregorianCalendar.YEAR);
	}
	
	public int[] time(){
		return new int[]{now.get(GregorianCalendar.HOUR_OF_DAY),now.get(GregorianCalendar.MINUTE)};
	}
	
	public int[] fullTime(){
		return new int[]{now.get(GregorianCalendar.MINUTE),now.get(GregorianCalendar.HOUR_OF_DAY),
				now.get(GregorianCalendar.DAY_OF_MONTH),now.get(GregorianCalendar.MONTH)+1,now.get(GregorianCalendar.YEAR)};
	}
	
}
