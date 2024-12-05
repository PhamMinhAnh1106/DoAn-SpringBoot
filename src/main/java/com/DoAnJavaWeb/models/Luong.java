package com.DoAnJavaWeb.models;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Luong")
public class Luong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salary")
    private Integer idSalary;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Users employee;

    @Column(name = "basic_salary", precision = 15, scale = 2)
    private BigDecimal basicSalary;

    @Column(name = "bonus", precision = 15, scale = 2)
    private BigDecimal bonus;

    @Column(name = "deduction", precision = 15, scale = 2)
    private BigDecimal deduction;

    @Column(name = "total_salary", insertable = false, updatable = false, precision = 15, scale = 2)
    private BigDecimal totalSalary;

    @Column(name = "pay_date")
    private Date payDate;

    // Constructors, getters, and setters

    public Luong() {}

    public Luong(Integer idSalary, Users employee, BigDecimal basicSalary, BigDecimal bonus, BigDecimal deduction,
                 Date payDate) {
        this.idSalary = idSalary;
        this.employee = employee;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deduction = deduction;
        this.payDate = payDate;
        this.totalSalary = calculateTotalSalary();  // Tính toán totalSalary khi khởi tạo
    }

    public Integer getIdSalary() {
        return idSalary;
    }

    public void setIdSalary(Integer idSalary) {
        this.idSalary = idSalary;
    }

    public Users getEmployee() {
        return employee;
    }

    public void setEmployee(Users employee) {
        this.employee = employee;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
        this.totalSalary = calculateTotalSalary();  // Cập nhật totalSalary khi thay đổi basicSalary
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
        this.totalSalary = calculateTotalSalary();  // Cập nhật totalSalary khi thay đổi bonus
    }

    public BigDecimal getDeduction() {
        return deduction;
    }

    public void setDeduction(BigDecimal deduction) {
        this.deduction = deduction;
        this.totalSalary = calculateTotalSalary();  // Cập nhật totalSalary khi thay đổi deduction
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

 // Tính toán totalSalary từ basicSalary, bonus và deduction
    public BigDecimal calculateTotalSalary() {
        if (basicSalary == null) {
            return BigDecimal.ZERO;  // Nếu không có lương cơ bản, trả về 0
        }
        // Nếu bonus hoặc deduction là null thì coi như bằng 0
        BigDecimal finalBonus = bonus != null ? bonus : BigDecimal.ZERO;
        BigDecimal finalDeduction = deduction != null ? deduction : BigDecimal.ZERO;
        
        return basicSalary.add(finalBonus).subtract(finalDeduction);
    }

}
