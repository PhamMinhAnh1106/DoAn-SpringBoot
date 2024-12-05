package com.DoAnJavaWeb.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.DoAnJavaWeb.models.NghiPhep;
@Repository
public interface NghiPhepRepository extends JpaRepository<NghiPhep, Integer>{

	List<NghiPhep> findAllByEmployee_Id(Integer employeeId);
}