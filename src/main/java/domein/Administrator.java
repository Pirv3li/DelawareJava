package domein;

public class Administrator extends Gebruiker {

	private int idAdmin;
	private String roles;
	
    public Administrator(String gebruikersnaam, String password_Hash, int idAdmin, String roles) {
        super(gebruikersnaam, password_Hash);
        this.idAdmin = idAdmin;
    }

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
    
    
}
