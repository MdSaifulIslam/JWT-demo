package com.springlearn.crudDemp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlearn.crudDemp.dao.RawDataRepository;
import com.springlearn.crudDemp.entity.RawData;

@Service		
public class RawDataServiceImpl implements RawDataService {
	
	@Autowired
	private RawDataRepository rawDataRepository;

	@Override
	public RawData saveRawData(RawData rawData) {
		return rawDataRepository.save(rawData);
	}

	@Override
	public List<RawData> findAll() {
		return rawDataRepository.findAll();
	}

	@Override
	public Optional<RawData> findByUserId(int userId) {
		return rawDataRepository.findByUserId(userId);
	}

}
