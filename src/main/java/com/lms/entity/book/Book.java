package com.lms.entity.book;

import java.util.Date;

import com.lms.entity.IdEntity;

public class Book extends IdEntity {

	private static final long serialVersionUID = -782849358969191087L;

	private String name;
	private String auth;
	private String press;
	private Integer totalNumber;
	private Integer lendNumber;
	private Integer inventoryNumber;
	private Integer status;
	private String remark;
	private Date lendTime;
	private Date returnTime;

	public Book() {
	}

	public Book(String name, String auth, String press, Integer totalNumber, Integer lendNumber,
			Integer inventoryNumber, Integer status, String remark) {
		this.name = name;
		this.auth = auth;
		this.press = press;
		this.totalNumber = totalNumber;
		this.lendNumber = lendNumber;
		this.inventoryNumber = inventoryNumber;
		this.status = status;
		this.remark = remark;
	}

	public Book(Integer id, String name, String auth, String press, Integer totalNumber, Integer lendNumber,
			Integer inventoryNumber, Integer status, String remark) {
		this.id = id;
		this.name = name;
		this.auth = auth;
		this.press = press;
		this.totalNumber = totalNumber;
		this.lendNumber = lendNumber;
		this.inventoryNumber = inventoryNumber;
		this.status = status;
		this.remark = remark;
	}
	public Book(Integer id, String name, String auth, String press, Integer totalNumber, Integer lendNumber,
			Integer inventoryNumber, Integer status, String remark,Date lendTime,Date returnTime) {
		this.id = id;
		this.name = name;
		this.auth = auth;
		this.press = press;
		this.totalNumber = totalNumber;
		this.lendNumber = lendNumber;
		this.inventoryNumber = inventoryNumber;
		this.status = status;
		this.remark = remark;
		this.lendTime=lendTime;
		this.returnTime=returnTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getLendNumber() {
		return lendNumber;
	}

	public void setLendNumber(Integer lendNumber) {
		this.lendNumber = lendNumber;
	}

	public Integer getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "Book [id=" + id + ",name=" + name + ", auth=" + auth + ", press=" + press + ", totalNumber="
				+ totalNumber + ", lendNumber=" + lendNumber + ", inventoryNumber=" + inventoryNumber + ", status="
				+ status + ", remark=" + remark + ", lendTime=" + lendTime + ", returnTime=" + returnTime + "]";
	}

}
