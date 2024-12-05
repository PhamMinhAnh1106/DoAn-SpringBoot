package com.DoAnJavaWeb.models;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "nghiphep")
public class NghiPhep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_leave")
	private Integer id_leave;

	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id")
	private Users employee;

	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "end_date")
	private Date end_date;

	@Column(name = "leave_type")
	private String leave_type;

	@Column(name = "reason")
	private String reason;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private LeaveStatus status;

	public NghiPhep(Integer id_leave, Users employee, Date start_date, Date end_date, String leave_type, String reason,
			LeaveStatus status) {
		super();
		this.id_leave = id_leave;
		this.employee = employee;
		this.start_date = start_date;
		this.end_date = end_date;
		this.leave_type = leave_type;
		this.reason = reason;
		this.status = status;
	}

	public NghiPhep() {
		super();
	}

	public Integer getId_leave() {
		return id_leave;
	}

	public void setId_leave(Integer id_leave) {
		this.id_leave = id_leave;
	}

	public Users getEmployee() {
		return employee;
	}

	public void setEmployee(Users employee) {
		this.employee = employee;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

}
