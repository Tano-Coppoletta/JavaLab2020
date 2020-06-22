package diet;

/**
 * Represent a take-away system user
 *  
 */
public class User {
	private String FirstName;
	private String LastName;
	private String email;
	private String phone;
	
	public User(String name,String LastName,String email,String phone) {
		this.FirstName=name;
		this.LastName=LastName;
		this.email=email;
		this.phone=phone;
	}
	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return LastName;
	}
	
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return FirstName;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email=email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public String toString() {
		return FirstName+" "+LastName; 
	}
	
}
