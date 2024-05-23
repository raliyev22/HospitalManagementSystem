package objects;

import javax.swing.JButton;

public class Doctor {
	private int id;
	private String departmentName;
	private String name;
	private String surname;
	private int age;
	private JButton button;
	private String picture;
	
	
	public Doctor(int id, String departmentName, String name, String surname, int age,JButton button,String picture) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.button = button;
		this.picture=picture;
	}


	public int getId() {
		return id;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public int getAge() {
		return age;
	}


	public JButton getButton() {
		return button;
	}


	public String getPicture() {
		return picture;
	}
	
	
	
	
	
	
	

}
