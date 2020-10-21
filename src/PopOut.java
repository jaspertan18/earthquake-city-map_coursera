package module6;

import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import de.fhpotsdam.unfolding.marker.Marker;

//public class PopOut extends JFrame implements ActionListener{
public class PopOut extends JFrame{
	
	static JButton button;
	static JFrame frame;
	static JPopupMenu menu;
	
	private CommonMarker common;
	private List<Marker> mark;
	
	public PopOut(List<Marker> other, CommonMarker lastClicked) {
		mark = other;
		common = lastClicked;
	}
	
	public void startMenu() {
		double average = 0;
		String mostRecent = "No recent earthquakes YAY";
		int countAffected = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		frame = new JFrame("PopOut");
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JPopupMenu("Message");
		
		for (Marker qm : mark) {
			if (!qm.isHidden()) {
				countAffected++;
				average += ((EarthquakeMarker)qm).getMagnitude();
				map.put(((EarthquakeMarker)qm).getStringProperty("age"), ((EarthquakeMarker)qm).getTitle());
			}
		}
		
		for (String age : map.keySet()) {
			switch (age) {
			case "Past Hour":
				mostRecent = map.get(age) + " in past hour";
				break;
			case "Past Week":
				mostRecent = map.get(age) + " in past week";
				break;
			case "Past Month":
				mostRecent = map.get(age) + " in past month";
				break;
			}
		}
		
		JPanel panel = new JPanel();
		String message1= "Total nearby earthquakes: " + Integer.toString(countAffected);
		String message2 = "Average Magnitude: " + Double.toString(average/countAffected);
		String message3 = "Most Recent Earthquake: " + mostRecent;
		
		JLabel label1 = new JLabel(message1);
		JLabel label2 = new JLabel(message2);
		JLabel label3 = new JLabel(message3);
//		PopOut mouse = new PopOut();
//		button = new JButton("click");
		
//		button.addActionListener(mouse);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
//		panel.add(button);
		frame.add(panel);
		frame.show();
	}


//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		String s = e.getActionCommand();
//		if (s.equals("click")) {
//			menu = new JPopupMenu("Message");
//			
//			JLabel l = new JLabel("this is the popup menu");
//			
//			menu.add(l);
//			
//			menu.show(frame, 50, 50);
//		}
//	}
}
