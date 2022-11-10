package by.zhuk.SensorRESTApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.zhuk.SensorRESTApp.models.Sensor;
import by.zhuk.SensorRESTApp.repositories.SensorRepository;
import by.zhuk.SensorRESTApp.utils.SensorExistException;

@Service
@Transactional(readOnly = true)
public class SensorService {

	private SensorRepository sensorRepository;

	@Autowired
	public SensorService(SensorRepository sensorRepository) {
		this.sensorRepository = sensorRepository;
	}

	private Optional<Sensor> findByName(Sensor sensor) {
		return sensorRepository.findByName(sensor.getName());
	}

	@Transactional
	public void save(Sensor sensor) {
		if (findByName(sensor).isPresent()) {
			throw new SensorExistException("Sensor with this name already exist");
		}
		sensorRepository.save(sensor);
	}

}
