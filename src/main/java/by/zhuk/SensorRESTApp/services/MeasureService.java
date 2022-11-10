package by.zhuk.SensorRESTApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.zhuk.SensorRESTApp.models.Measure;
import by.zhuk.SensorRESTApp.repositories.MeasureRepository;

@Service
@Transactional(readOnly = true)
public class MeasureService {

	private MeasureRepository measureRepository;

	@Autowired
	public MeasureService(MeasureRepository measureRepository) {
		this.measureRepository = measureRepository;
	}

	public List<Measure> findAll() {
		return measureRepository.findAll();
	}

	public long getRainyDays() {
		return measureRepository.count();
	}
}
