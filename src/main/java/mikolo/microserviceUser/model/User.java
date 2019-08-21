package mikolo.microserviceUser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
	
	private long id;
	private String email;
	private String password;
	private String name;
	private String lastName;
	private int nrRoli;
	private int active;
	
	public User(String email, String password, String name, String lastName) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}
	
	
	
}
