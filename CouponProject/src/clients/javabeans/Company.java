package clients.javabeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.CouponServerException;
import dao.db.CouponDAODB;

public class Company {
	
	/**
	 * ATTRIBUTES
	 */
	private long idCompany;
	private String compName;
	private String password;
	private String email;
	List<Coupon> coupons = new ArrayList<Coupon>();
	
	/**
	 * CONSTRUCTORE
	 */
	public Company(long idCompany, String compName, String password, String email) {
		super();
		this.idCompany = idCompany;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	/**
	 *GETTERS AND SETERRS METHODS
	 */
	public long getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(long idCompany) {
		this.idCompany = idCompany;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Coupon> getCoupons() throws CouponServerException {
		CouponDAODB couponDAODB = new CouponDAODB();
		coupons = couponDAODB.getAllCoupons();

		return coupons;
	}
	
	public void setCoupons(List<Coupon> list) {
		this.coupons = list;
	}
	
	/**
	 * TO STRING METHOD
	 */
	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", compName=" + compName + ", password=" + password + ", email="
				+ email + ", coupons=" + coupons + "]";
	}

	/**
	 * ADD COUPON METHOD
	 */
	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);	
	}
	

}
