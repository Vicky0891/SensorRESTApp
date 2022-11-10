package by.zhuk.SensorRESTApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.zhuk.SensorRESTApp.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {
	
	Optional<Sensor> findByName(String name);

}
