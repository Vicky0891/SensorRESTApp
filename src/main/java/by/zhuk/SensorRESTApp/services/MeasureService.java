package by.zhuk.SensorRESTApp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.zhuk.SensorRESTApp.models.Measure;
import by.zhuk.SensorRESTApp.repositories.MeasureRepository;
import by.zhuk.SensorRESTApp.repositories.SensorRepository;
import by.zhuk.SensorRESTApp.utils.SensorNotExistException;

@Service
@Transactional(readOnly = true)
public class MeasureService {

	private MeasureRepository measureRepository;
	private SensorRepository sensorRepository;

	@Autowired
	public MeasureService(MeasureRepository measureRepository, SensorRepository sensorRepository) {
		this.measureRepository = measureRepository;
		this.sensorRepository = sensorRepository;
	}

	public List<Measure> findAll() {
		return measureRepository.findAll();
	}

	public int getRainyDays() {
		return measureRepository.countByRaining(true);

	}

	@Transactional
	public void saveMeasure(Measure measure) {
		if (sensorRepository.findByName(measure.getSensor().getName()).isPresent()) {
			enrichMeasure(measure);
			measureRepository.save(measure);
		} else {
			throw new SensorNotExistException("Sensor with this name not exist");
		}

	}

	private void enrichMeasure(Measure measure) {
		measure.setMeasuredAt(LocalDateTime.now());
	}
}
