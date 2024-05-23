package sql;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import objects.Doctor;

public class Appointment extends JFrame implements ActionListener,MouseListener{
	JPanel topPanel;
	JPanel northPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;
	JPanel main;
	JPanel bottom;
	
	JLabel topLabel;
	JPanel down;
	
	JLabel topText;
	
	JLabel doctor1;
	JLabel doctor2;
	JLabel doctor3;
	JLabel doctor4;
	JLabel doctor5;
	
	
	
	
	
	JLabel dLabel1;
	JLabel dLabel2;
	JLabel dLabel3;
	JLabel dLabel4;
	JLabel dLabel5;
	JLabel dLabel6;
	
	
	
	JLabel doctor2Name;
	JLabel doctor3Name;
	JLabel doctor4Name;
	JLabel doctor5Name;
	
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton apply;
	
	HashMap<JLabel,Doctor> panelMap;
	ArrayList<JLabel> doctorLabels;
	ArrayList<String> path;
	ArrayList<Doctor> search;
	String url;
	
	Font font;
	
	JComboBox<String> departmentComboBox;
	JLabel selectNameLabel;
	JTextField selectName;
	
	
	JPanel down1panel;
	JPanel down2panel;
	JPanel down3panel;
	
	JPanel bottomPanel;
	
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointment frame = new Appointment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Appointment() {
		path = new ArrayList<String>();
		path.add("d1.jpg");
		path.add("d2.jpg");
		path.add("d3.jpg");
		path.add("d4.jpg");
		path.add("d5.jpg");
		
		
		doctorLabels = new ArrayList<JLabel>();
		search =new ArrayList<Doctor>();
		
		
		panelMap = new HashMap<JLabel,Doctor>();
		url = "jdbc:mysql://localhost:3306/hospital";

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		font = new Font("Objektiv Mk1",Font.BOLD, 40); 

		
		bottom = new JPanel();

		
		topPanel= new JPanel();
		//topPanel.setPreferredSize(new Dimension(200,200));
		topPanel.setLayout(new GridLayout(2,1,0,0));
		topPanel.setBorder(new EmptyBorder(0,0,20,0));
		//topPanel.setBackground(Color.decode("#00008B"));
		
		
		topLabel=new JLabel("Select a Doctor");
		topLabel.setFont(font);
		topLabel.setOpaque(true);
		topLabel.setBackground(Color.decode("#00008B"));
		topLabel.setForeground(Color.white);
		topLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		topPanel.add(topLabel);
		
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.decode("#00008B"));
		bottomPanel.setLayout(new GridBagLayout());
		
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Add a JComboBox
        gbc.insets = new Insets(5, 5, 5, 50);
        String[] department = {"None","Cardiology", "Neurology","Orthopedics","Pediatrics","Dermatology"};
        departmentComboBox = new JComboBox<>(department);
        bottomPanel.add(departmentComboBox, gbc);

        // Add JLabel and JTextField pair
        gbc.insets = new Insets(5, 200, 5, 5);
        gbc.gridx++;
        selectNameLabel = new JLabel("Name");
        selectNameLabel.setFont(new Font("Objektiv Mk1",Font.BOLD, 20) );
        //selectNameLabel.setOpaque(true);
        selectNameLabel.setForeground(Color.white);
        bottomPanel.add(selectNameLabel, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(5, 20, 5, 50);
        selectName = new JTextField(10);
        bottomPanel.add(selectName, gbc);

        gbc.gridx ++;
        gbc.insets = new Insets(5, 200, 5, 5);
        //gbc.gridy++;
        //gbc.gridwidth = 3; // Span across three columns
        apply = new JButton("Apply");
        bottomPanel.add(apply, gbc);
        
        apply.setFont(new Font("Objektiv Mk1", Font.BOLD, 20));
	    apply.setBackground(Color.white);
	    apply.setForeground(Color.decode("#00008B"));
	    apply.setFocusPainted(false);
	    apply.addActionListener(this);
		

		
		topPanel.add(bottomPanel);
		


		bottom.setLayout(new GridLayout(5,1,20,20));

//		doctor1 = new JLabel();
//		this.createFields(doctor1);
//		
//		
//		doctor2 = new JLabel();
//		this.createFields(doctor2);
//		
//		doctor3 = new JLabel();
//		this.createFields(doctor3);
//		
//		
//		doctor4 = new JLabel();
//		this.createFields(doctor4);
//		
//		
//		doctor5 = new JLabel();
//		this.createFields(doctor5);
//		
//		
//		doctorLabels.add(doctor1);
//		doctorLabels.add(doctor2);
//		doctorLabels.add(doctor3);
//		doctorLabels.add(doctor4);
//		doctorLabels.add(doctor5);
		
		for(int i=0;i<getNumberOfRows();i++) {
			JLabel doctor = new JLabel();
			createFields(doctor);
			doctorLabels.add(doctor);
		}
		
		createHashMapList();
		displayInfo();
		
		main=new JPanel();
		main.setLayout(new BorderLayout());
		main.add(topPanel,BorderLayout.NORTH);

		main.add(bottom,BorderLayout.CENTER);
		
		add(main);
		
		
		
		JScrollPane scrollPane = new JScrollPane(main);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
        
        

        
        
    
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (Doctor doc : panelMap.values()) {
			if(e.getSource()==doc.getButton()) {
				System.out.println(doc.getId());
			}
		}
		
		if(e.getSource()==apply) {
			if(departmentComboBox.getSelectedItem().equals("None") && selectName.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(this, "Please enter information", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
//			else if(!departmentComboBox.getSelectedItem().equals("None") && selectName.getText().isEmpty()) {
			else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
		    	    Connection connection = DriverManager.getConnection(url,"root","password");
		    	    
		    	    String sql;
		    	    PreparedStatement statement;
		    	    if(!departmentComboBox.getSelectedItem().equals("None") && selectName.getText().isEmpty()) {
			    	    sql = "select * from doctor where department_name = ?";
			    	    statement = connection.prepareStatement(sql);
			    	    statement.setString(1, (String)departmentComboBox.getSelectedItem());
		    	    }
		    	    
		    	    else if(departmentComboBox.getSelectedItem().equals("None") && !selectName.getText().isEmpty()) {
		    	    	sql = "select * from doctor where d_name = ?";
			    	    statement = connection.prepareStatement(sql);
			    	    statement.setString(1, (String)selectName.getText());
		    	    }
		    	    
		    	    else {
		    	    	sql = "select * from doctor where d_name = ? and department_name = ?";
			    	    statement = connection.prepareStatement(sql);
			    	    statement.setString(1, (String)selectName.getText());
			    	    statement.setString(2, (String)departmentComboBox.getSelectedItem());
		    	    }
	

		    	    
		    	    ResultSet resultSet = statement.executeQuery();
		    	    
		    	    if (!resultSet.next()) {
		    	        JOptionPane.showMessageDialog(this, "No match found", "Error", JOptionPane.ERROR_MESSAGE);
		    	    } else {
		    	        do {
		    	            search.add(new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), new JButton(), ""));
		    	            System.out.println("selected");
		    	        } while (resultSet.next());
		    	    }
		    	    
		    	    
		    	    connection.close();
				}
				
				catch(Exception error) {
					System.out.println(error);
				}
				
			}
				
			
		}
		
	}
	
	public void populateField(JLabel doctor,JLabel component) {
		doctor.setOpaque(true);
		doctor.setBackground(Color.decode("#00008B"));
		component.add(doctor);
	}
	
	public void createFields(JLabel doctor) {
		doctor.setPreferredSize(new Dimension(400,400));
		doctor.setOpaque(true);
		doctor.setBackground(Color.decode("#00008B"));
		
		doctor.setLayout(new GridLayout(2,2,0,0));
		bottom.add(doctor);
	}
	
	private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	private Image getScaledImage(BufferedImage srcImg, int width, int height) {
        Image scaledImage = srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }
	
	public void createHashMapList() {
		
		try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DriverManager.getConnection(url,"root","password");
    	    Statement statement = connection.createStatement();
    	    
    	    ResultSet resultSet = statement.executeQuery("select * from doctor");
    	    
    	    int myindex = 0;
    	    while(resultSet.next()) {
    	    	panelMap.put(doctorLabels.get(myindex), new Doctor(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),new JButton(),path.get(myindex)));
    	    	myindex+=1;
    	    }
    	    connection.close();
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
	}
	
	
	
	public void setFields(JLabel doctor,JLabel content) {
		doctor.setHorizontalAlignment(SwingConstants.CENTER);
        doctor.setVerticalAlignment(SwingConstants.CENTER);
		doctor.setFont(font);
		doctor.setForeground(Color.white);
		this.populateField(doctor,content);
	}
	
	public void displayPicture(JLabel doctor,JLabel component,String picture) {
		doctor.setPreferredSize(new Dimension(getWidth(),400));
		this.populateField(doctor,component);
		doctor.setBackground(Color.white);
		BufferedImage originalImage = loadImage("C:\\Users\\HP\\eclipse-workspace\\Hospital\\"+picture);

		doctor.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (originalImage != null) {
                    Image resizedImage = getScaledImage(originalImage, doctor.getWidth(), doctor.getHeight());
                    doctor.setIcon(new ImageIcon(resizedImage));
                    
                }
            }
        });
	}
	
	public void displayInfo() {;
		 for (Entry<JLabel, Doctor> entry : panelMap.entrySet()) {
			 setFields(new JLabel("Name: "+entry.getValue().getName()),entry.getKey());
			 setFields(new JLabel("Surname: "+entry.getValue().getSurname()),entry.getKey());
			 displayPicture(new JLabel(),entry.getKey(),entry.getValue().getPicture());
			 setFields(new JLabel("Age: "+entry.getValue().getAge()),entry.getKey());
			 setFields(new JLabel("Department: "+entry.getValue().getDepartmentName()),entry.getKey());
		     addButton(entry.getValue().getButton(), entry.getKey(),new JLabel());

	        }
		
	}
	
	public void addButton(JButton button, JLabel label,JLabel inside) {
	    // Set the text and font for the button
	    button.setText("Select");
	    button.setFont(new Font("Objektiv Mk1", Font.BOLD, 20));
	    button.setBackground(Color.white);
	    button.setForeground(Color.decode("#00008B"));
	    button.setPreferredSize(new Dimension(150,50));
	    button.setFocusPainted(false);
	    button.addActionListener(this);
	    
	 

	    inside.setLayout(new GridBagLayout());

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 10; 
	    gbc.gridy = 4; 
	    
	

	    inside.add(button, gbc);
	    
	    label.add(inside);
	}
	
	
	public Integer getNumberOfRows() {
    	
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DriverManager.getConnection(url,"root","password");
    	    Statement statement = connection.createStatement();
    	    
    	    
    	    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM doctor");
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            
    	    connection.close();
    	    
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
		return null;
    }

}

