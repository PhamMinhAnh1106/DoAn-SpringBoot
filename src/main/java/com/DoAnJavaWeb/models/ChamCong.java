package com.DoAnJavaWeb.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chamcong")
public class ChamCong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_attendance")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id")
	private Users employee;

	@Column(name = "check_in_time")
	private LocalDateTime checkInTime;

	@Column(name = "check_out_time")
	private LocalDateTime checkOutTime;

	@Column(name = "work_date")
	private LocalDateTime workDate;

	@Column(name = "enabled")
	private Boolean enabled;

	public ChamCong(Integer id, Users employee, LocalDateTime checkInTime, LocalDateTime checkOutTime,
			LocalDateTime workDate, Boolean enabled) {
		super();
		this.id = id;
		this.employee = employee;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.workDate = workDate;
		this.enabled = enabled;
	}

	public ChamCong() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getEmployee() {
		return employee;
	}

	public void setEmployee(Users employee) {
		this.employee = employee;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public LocalDateTime getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDateTime workDate) {
		this.workDate = workDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
