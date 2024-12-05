package com.DoAnJavaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnJavaWeb.models.NghiPhep;
import com.DoAnJavaWeb.repository.NghiPhepRepository;

@Service
public class NghiPhepServiceImpl implements NghiPhepService{
	@Autowired
	private NghiPhepRepository nghiPhepRepository;

	@Override
	public List<NghiPhep> getAll() {
		// TODO Auto-generated method stub
		return nghiPhepRepository.findAll();
	}

	@Override
	public Boolean create(NghiPhep nghiPhep) {
		// TODO Auto-generated method stub
		try {
			nghiPhepRepository.save(nghiPhep);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public NghiPhep findById(Integer idLeave) {
		// TODO Auto-generated method stub
		return nghiPhepRepository.findById(idLeave).orElse(null);
	}

	@Override
	public Boolean update(NghiPhep nghiPhep) {
		// TODO Auto-generated method stub
		try {
			nghiPhepRepository.save(nghiPhep);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer idLeave) {
		// TODO Auto-generated method stub
		try {
			nghiPhepRepository.deleteById(idLeave);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<NghiPhep> getByEmployeeId(Integer employeeId) {
		// TODO Auto-generated method stub
		return nghiPhepRepository.findAllByEmployee_Id(employeeId);
	
	}

}
