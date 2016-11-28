package bg.softuni.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "PAID_BILLS")
public class PaidBillModel extends BaseDomainObject {

	private static final long serialVersionUID = 1L;

	private Long month;
	private Long year;
	private Float sumWithoutTax;
	private Float sumWithTax;
	private UserModel user;

	public PaidBillModel() {
	}

	public PaidBillModel(Long id, Long month, Long year, Float sumWithoutTax, Float sumWithTax) {
		this.id = id;
		this.month = month;
		this.year = year;
		this.sumWithoutTax = sumWithoutTax;
		this.sumWithTax = sumWithTax;
	}

	@Column(name = "month", length = 11, nullable = false)
	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	@Column(name = "year", length = 4, nullable = false)
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@Column(name = "sum_without_tax", precision = 2, nullable = false)
	public Float getSumWithoutTax() {
		return sumWithoutTax;
	}

	public void setSumWithoutTax(Float sumWithoutTax) {
		this.sumWithoutTax = sumWithoutTax;
	}

	@Column(name = "sum_with_tax", precision = 2, nullable = false)
	public Float getSumWithTax() {
		return sumWithTax;
	}

	public void setSumWithTax(Float sumWithTax) {
		this.sumWithTax = sumWithTax;
	}

	@JoinColumn(name = "userID", referencedColumnName = "id")
	@ManyToOne
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel userID) {
		this.user = userID;
	}
}