package domein;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
@NamedQuery(name = "Admin.getAdminByGebruikersnaam", 
	query = "SELECT ad FROM Admin ad WHERE ad.gebruikersnaam = :gebruikersnaam")
public class Admin extends Gebruiker implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAdmin")
	private int idAdmin;
	private String gebruikersnaam;
	private String email;
	
	@Column(columnDefinition = "VARCHAR(255)")
	private String roles; 
	
    public Admin(String gebruikersnaam, String password_Hash, int idAdmin, String roles, String email) {
        super(gebruikersnaam, password_Hash);
        this.idAdmin = idAdmin;
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        this.roles = roles;
    }

	public String getRoles() {
		return roles;
	}

	public Admin() {
		super();
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
    
    
}
