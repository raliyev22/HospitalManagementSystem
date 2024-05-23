package sql;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.Doctor;

public class SearchResult extends JFrame{
	
	private ArrayList<Doctor> list;
	private ArrayList<JLabel> doctorLabels;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResult frame = new SearchResult(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public SearchResult(ArrayList<Doctor> list){
		
		this.list = list;
		doctorLabels=new ArrayList<JLabel>();
	}

}
