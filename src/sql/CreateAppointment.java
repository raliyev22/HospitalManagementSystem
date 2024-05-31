package sql;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import objects.DatabaseConnection;
import objects.Doctor;
import objects.Patient;

public class CreateAppointment extends JFrame{
	private int d_id;
	private int p_id;
	private int id;
	private String[] hourList = {
    	    "9:00", "9:35", "10:10", "10:45", "11:20", "11:55",
    	    "12:30", "13:05", "13:40", "14:15", "14:50", "15:25",
    	    "16:00", "16:35"
    	};
	
	private JButton confirm;
	private boolean isUpdated;
	//private JFrame updateFrame;
	private Timestamp time;
	private boolean insert;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	CreateAppointment frame = new CreateAppointment(0,0,0,false);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

	}
	
	public CreateAppointment(int id,int d_id,int p_id,boolean insert) {
		this.id=id;
		this.p_id=p_id;
		this.d_id = d_id;
		this.insert=insert;

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(400, 350);
    	setLayout(new GridBagLayout());
    	GridBagConstraints margins = new GridBagConstraints();
    	margins.insets=new Insets(0, 0, 35, 5);
    	margins.gridx=0;
    	margins.gridy=0;
    	JLabel newDate = new JLabel("Select a Date");
    	newDate.setFont(new Font("Objektiv Mk1", Font.BOLD, 15));
    	add(newDate,margins);
    	
    	margins.insets=new Insets(0, 5, 35, 0);
    	margins.gridx++;
    	JTextField dateField = new JTextField(10);
    	dateField.setFont(new Font("Arial", Font.PLAIN, 16));
    	add(dateField,margins);
    	
    	
    	margins.insets=new Insets(0, 0, 0, 0);
    	margins.gridx=0;
    	margins.gridy=1;
    	JLabel hour = new JLabel("Select the hour");
    	hour.setFont(new Font("Objektiv Mk1", Font.BOLD, 15));
    	add(hour,margins);
    	
    	JComboBox<String> hourBox = new JComboBox<String>(hourList);
    	hourBox.setFont(new Font("Objektiv Mk1", Font.BOLD, 15));
    	margins.insets=new Insets(0, 5, 0, 0);
    	margins.gridx=1;
    	margins.gridy=1;
    	add(hourBox,margins);
    	
    	
    	
    	
    	margins.insets=new Insets(20, 0, 0, 120);
    	margins.gridx=1;
    	margins.gridy=2;
    	confirm = new JButton("Confirm");
    	confirm.setFont(new Font("Objektiv Mk1", Font.BOLD, 15));
        confirm.setBackground(Color.white);
        confirm.setForeground(Color.decode("#00008B"));
        confirm.setFocusPainted(false);
        add(confirm,margins);
        
        
        
        
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the JComboBox
            	if(dateField.getText().isEmpty()) {dispose();}
            
            	else {
            	    time = createTimestamp(dateField.getText(),(String)hourBox.getSelectedItem());
            	    
            	    if(time==null) {JOptionPane.showMessageDialog(CreateAppointment.this, "Please enter a valid date.", "Error", JOptionPane.ERROR_MESSAGE);}
            	    
            	    else {
            	    	if(!insert) {updateRow();}
            	    	
            	    	else {insertRow();}
            	    	
            	    }
            	    
            }
            	
            	dispose();
            		}
            
            		
            }
        );              	
    	setVisible(true);
	}
	
	private Timestamp createTimestamp(String date,String hour) {
    	if(isValidDateFormat(date)) {
	    	Timestamp newAppointmentDate = Timestamp.valueOf(modifyDate(date)+hour+":00");
	    	return newAppointmentDate;
	    	
    	}
    	return null;
    }
	
	private boolean isValidDateFormat(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
	
	private String modifyDate(String date) {
    	String[] component = date.split("/");
    	return component[2]+'-'+component[1]+'-'+component[0]+" ";
    	
    }
	
	protected boolean isUpdated() {
		isUpdated = true;
		return isUpdated;
	}
	
	private void updateRow() {
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DatabaseConnection.getConnection();
    	    
    	    
    	    String sql = "update appointment set appointment_date = ? where id = ?";
    	    
    	    
    	    PreparedStatement statement = connection.prepareStatement(sql);
    	    statement.setTimestamp(1, time);
    	    statement.setInt(2, id);
    	    
    	    statement.executeUpdate();
//    	    populateAppointmentsMap();
//            showAppointments(appointmentsMap);
    	    isUpdated();
	    	connection.close();
    	
    	}
    	catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(CreateAppointment.this, "This appointment time is already taken.", "Error", JOptionPane.ERROR_MESSAGE);
            
    	}
    	
    	catch(Exception error) {
    		
			System.out.println(error);
		}
	}
	
	
	
	private void insertRow() {
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DatabaseConnection.getConnection();
    	    
    	    
    	    String sql = "insert into appointment (id,p_id,appointment_date,d_id,stat,billing) values (?, ?, ?, ?, ?, ?)";
    	    PreparedStatement statement = connection.prepareStatement(sql);
    	    statement.setInt(1, id);
    	    statement.setInt(2, p_id);
    	    statement.setTimestamp(3, time);
    	    statement.setInt(4, d_id);
    	    statement.setString(5, "pending");
    	    statement.setFloat(6, fetchBilling());
    	    
    	    statement.executeUpdate();
//    	    populateAppointmentsMap();
//            showAppointments(appointmentsMap);
    	    isUpdated();
	    	connection.close();
    	
    	}
    	catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(CreateAppointment.this, "This appointment time is already taken.", "Error", JOptionPane.ERROR_MESSAGE);
            
    	}
    	
    	catch(Exception error) {
    		
			System.out.println(error);
		}
	}
	
	private Float fetchBilling() {
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DatabaseConnection.getConnection();
    	    
    	    
    	    String sql = "select billing from billing,doctor where billing.department_name = doctor.department_name and doctor.id = ?";
    	    
    	    
    	    PreparedStatement statement = connection.prepareStatement(sql);
    	    statement.setInt(1, d_id);
    	    
    	    
    	    ResultSet result = statement.executeQuery();
    	    if (result.next()) {
                 return result.getFloat("billing");
            }
//    	    populateAppointmentsMap();
//            showAppointments(appointmentsMap);
    	    
	    	connection.close();
	    	
	    	
    	
    	}
		
		catch(Exception error) {
    		
			System.out.println(error);
		}
		
		return null;
	}

}
