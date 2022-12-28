package com.springlearn.crudDemp.service;

import java.util.List;
import java.util.Optional;

import com.springlearn.crudDemp.entity.RawData;

public interface RawDataService {

	public RawData saveRawData(RawData rawData);

	public List<RawData> findAll();

	public Optional<RawData> findByUserId(int userId);
}
