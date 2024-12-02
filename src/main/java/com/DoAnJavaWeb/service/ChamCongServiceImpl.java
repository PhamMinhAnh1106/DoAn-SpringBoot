package com.DoAnJavaWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoAnJavaWeb.models.ChamCong;
import com.DoAnJavaWeb.repository.ChamCongRepository;

@Service
public class ChamCongServiceImpl implements ChamCongService {
	@Autowired
	private ChamCongRepository chamCongRepository;

	@Override
	public List<ChamCong> getAll() {
		// TODO Auto-generated method stub
		return chamCongRepository.findAll();
	}

	@Override
	public Boolean create(ChamCong chamCong) {
		// TODO Auto-generated method stub
		try {
			chamCongRepository.save(chamCong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChamCong findById(Integer id) {
		// TODO Auto-generated method stub
		return chamCongRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean update(ChamCong chamCong) {
		// TODO Auto-generated method stub
		try {
			chamCongRepository.save(chamCong);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		try {
			chamCongRepository.delete(findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
