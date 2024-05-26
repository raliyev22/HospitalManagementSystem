package sql;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.Statement;

import objects.Doctor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpPage extends JFrame implements ActionListener{

    private JPanel mainPanel;
    private Font font;
    private Font textFieldFont;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel surnameLabel;
    private JTextField surnameField;
    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel genderLabel;
    private JComboBox<String> genderComboBox;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private String url;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpPage frame = new SignUpPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SignUpPage() {
    	url = "jdbc:mysql://localhost:3306/hospital";
    	
    	 setTitle("Sign Up");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(500, 500);

         font = new Font("Objektiv Mk1", Font.BOLD, 20);
         textFieldFont = new Font("Arial", Font.PLAIN, 16);

         mainPanel = new JPanel();
         mainPanel.setBackground(Color.decode("#00008B"));
         mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
         mainPanel.setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(10, 10, 50, 10);
         gbc.fill = GridBagConstraints.HORIZONTAL;

         nameLabel = new JLabel("Name:");
         nameLabel.setFont(font);
         nameLabel.setForeground(Color.white);
         nameField = new JTextField(20);
         nameField.setFont(textFieldFont);
         gbc.gridx = 0;
         gbc.gridy = 0;
         mainPanel.add(nameLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(nameField, gbc);

         surnameLabel = new JLabel("Surname:");
         surnameLabel.setFont(font);
         surnameLabel.setForeground(Color.white);
         surnameField = new JTextField(20);
         surnameField.setFont(textFieldFont);
         gbc.gridx = 0;
         gbc.gridy = 1;
         mainPanel.add(surnameLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(surnameField, gbc);

         ageLabel = new JLabel("Age:");
         ageLabel.setFont(font);
         ageLabel.setForeground(Color.white);
         ageField = new JTextField(20);
         ageField.setFont(textFieldFont);
         gbc.gridx = 0;
         gbc.gridy = 2;
         mainPanel.add(ageLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(ageField, gbc);

         genderLabel = new JLabel("Gender:");
         genderLabel.setFont(font);
         genderLabel.setForeground(Color.white);
         String[] genders = {"Male", "Female"};
         genderComboBox = new JComboBox<>(genders);
         gbc.gridx = 0;
         gbc.gridy = 3;
         mainPanel.add(genderLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(genderComboBox, gbc);

         emailLabel = new JLabel("Email:");
         emailLabel.setFont(font);
         emailLabel.setForeground(Color.white);
         emailField = new JTextField(20);
         emailField.setFont(textFieldFont);
         gbc.gridx = 0;
         gbc.gridy = 4;
         mainPanel.add(emailLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(emailField, gbc);

         passwordLabel = new JLabel("Password:");
         passwordLabel.setFont(font);
         passwordLabel.setForeground(Color.white);
         passwordField = new JPasswordField(20);
         passwordField.setFont(textFieldFont);
         gbc.gridx = 0;
         gbc.gridy = 5;
         mainPanel.add(passwordLabel, gbc);
         gbc.gridx = 1;
         mainPanel.add(passwordField, gbc);

         signUpButton = new JButton("Sign Up");
         signUpButton.setFocusPainted(false);
         signUpButton.addActionListener(this);
         
         gbc.gridx = 0;
         gbc.gridy = 6;
         gbc.gridwidth = 2;
         mainPanel.add(signUpButton, gbc);
         
         
         JScrollPane scrollPane = new JScrollPane(mainPanel);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         add(scrollPane);

         setVisible(true);
    }

    


    
    
    
    public void actionPerformed(ActionEvent e) {
    	String name = nameField.getText();
        String surname = surnameField.getText();
        String ageText = ageField.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || surname.isEmpty() || ageText.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int age = Integer.parseInt(ageText);
                if (age < 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.length() < 6) {
                    JOptionPane.showMessageDialog(this, "Password must contain at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.matches(".*\\d.*")) {
                    JOptionPane.showMessageDialog(this, "Password must contain at least one numeric character.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.matches(".*[A-Z].*")) {
                    JOptionPane.showMessageDialog(this, "Password must contain at least one capital letter.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	createUser(getNumberOfRows(),gender,name,age,surname,password,email);
                	dispose();
                	LogIn view  = new LogIn();
                	view.setVisible(true);
                	
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getNumberOfRows() {
    	
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection = DriverManager.getConnection(url,"root","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");
    	    Statement statement = connection.createStatement();
    	    
    	    //ResultSet resultSet = statement.executeQuery("select * from doctor");
    	    
    	    ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM patient");
            
            if (resultSet.next()) {
                return resultSet.getInt(1);
//                System.out.println("Number of rows: " + rowCount);
            }
            
    	    connection.close();
    	    
    		}
    		catch(Exception e) {
    			System.out.println(e);
    		}
		return null;
    }
    
    
    
    public static void createUser(int id,String gender,String name,int age,String surname,String password,String email) {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, "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");

            String sql = "INSERT INTO patient (id,gender,p_name,age,surname,password,email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Set the parameters for the SQL statement
            statement.setInt(1, id+1);
            statement.setString(2, gender);
            statement.setString(3, name);
            
            statement.setInt(4, age);
            statement.setString(5, surname);
            statement.setString(6, password);
            statement.setString(7, email);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

