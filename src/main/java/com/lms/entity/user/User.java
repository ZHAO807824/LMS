package com.lms.entity.user;

import java.util.Date;

import com.lms.entity.IdEntity;

public class User extends IdEntity {

	private static final long serialVersionUID = 4433790888242952391L;

	private String name;
	private String email;
	private String tell;
	private String idcard;
	private String address;
	private Integer gender;
	private Integer lendNumber;
	private Integer role;
	private Integer status;
	private Date lendTime;
	private Date returnTime;

	public User() {
	}

	public User(Integer id, String name, String email, String tell, String idcard, String address, Integer gender) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.idcard = idcard;
		this.address = address;
		this.gender = gender;
	}

	public User(String name, String email, String tell, String idcard, String address, Integer gender) {
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.idcard = idcard;
		this.address = address;
		this.gender = gender;
	}

	public User(String name, String email, String tell, String idcard, String address, Integer gender,
			Integer lendNumber, Integer role, Integer status) {
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.idcard = idcard;
		this.address = address;
		this.gender = gender;
		this.lendNumber = lendNumber;
		this.role = role;
		this.status = status;
	}

	public User(Integer id, String name, String email, String tell, String idcard, String address, Integer gender,
			Integer lendNumber, Integer role, Integer status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.idcard = idcard;
		this.address = address;
		this.gender = gender;
		this.lendNumber = lendNumber;
		this.role = role;
		this.status = status;
	}

	public User(Integer id, String name, String email, String tell, String idcard, String address, Integer gender,
			Integer lendNumber, Integer role, Integer status, Date lendTime, Date returnTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.idcard = idcard;
		this.address = address;
		this.gender = gender;
		this.lendNumber = lendNumber;
		this.role = role;
		this.status = status;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getLendNumber() {
		return lendNumber;
	}

	public void setLendNumber(Integer lendNumber) {
		this.lendNumber = lendNumber;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLendTime() {
		return lendTime;
	}

	public void setLendTime(Date lendTime) {
		this.lendTime = lendTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "name=" + name + ", email=" + email + ", tell=" + tell + ", idcard=" + idcard
				+ ", address=" + address + ", gender=" + gender + ", lendNumber=" + lendNumber + ", role=" + role
				+ ", status=" + status + ", lendTime=" + lendTime + ", returnTime=" + returnTime + "]";
	}

}
