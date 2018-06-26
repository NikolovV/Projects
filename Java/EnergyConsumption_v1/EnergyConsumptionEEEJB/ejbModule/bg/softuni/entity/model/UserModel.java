package bg.softuni.entity.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "USERS")
public class UserModel extends BaseDomainObject {

	private static final long serialVersionUID = 1L;
	private Long customerId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private List<PaidBillModel> paidBills;
	private List<UnPaidBillModel> unPaidBills;
	private Long unpaidBillCounter;

	public UserModel() {
	}

	public UserModel(Long id, Long customerID, String username, String password, String firstName, String lastName,
			String email) {
		this.id = id;
		this.customerId = customerID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserModel(Long id, Long customerID, String username, String password, String firstName, String lastName,
			String email, Long unpaidBillCounter) {
		this.id = id;
		this.customerId = customerID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.unpaidBillCounter = unpaidBillCounter;
	}

	@Column(name = "customerID", length = 11, nullable = false)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerID) {
		this.customerId = customerID;
	}

	@Column(name = "username", length = 30, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 50, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", length = 45, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 45, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", length = 45, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public Long getUnpaidBillCounter() {
		return unpaidBillCounter;
	}

	public void setUnpaidBillCounter(Long unpaidBillCounter) {
		this.unpaidBillCounter = unpaidBillCounter;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	public List<PaidBillModel> getPaidBills() {
		return paidBills;
	}

	public void setPaidBills(List<PaidBillModel> paidBills) {
		this.paidBills = paidBills;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	public List<UnPaidBillModel> getUnPaidBills() {
		return unPaidBills;
	}

	public void setUnPaidBills(List<UnPaidBillModel> unPaidBills) {
		this.unPaidBills = unPaidBills;
	}
}