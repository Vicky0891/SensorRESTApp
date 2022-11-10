package by.zhuk.SensorRESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.zhuk.SensorRESTApp.models.Sensor;
import by.zhuk.SensorRESTApp.repositories.SensorRepository;

@Service
@Transactional
public class SensorService {

	private SensorRepository sensorRepository;

	@Autowired
	public SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}

	public void save(Sensor sensor) {
		sensorRepository.save(sensor);
	}

}
