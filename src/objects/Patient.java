package objects;

public class Patient {
	private int id;
	private String gender;
	private String name;
	private int age;
	private String surname;
	private String password;
	private String email;
	
	
	public Patient(int id, String gender, String name, int age, String surname, String password,String email) {
		super();
		this.id = id;
		this.gender = gender;
		this.name = name;
		this.age = age;
		this.surname = surname;
		this.password = password;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public String getGender() {
		return gender;
	}


	public String getName() {
		return name;
	}


	public int getAge() {
		return age;
	}


	public String getSurname() {
		return surname;
	}


	public String getPassword() {
		return password;
	}


	public String getEmail() {
		return email;
	}
	
	
	
	
	
	
	
	
		

}
