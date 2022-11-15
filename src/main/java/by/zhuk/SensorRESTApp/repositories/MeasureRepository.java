package by.zhuk.SensorRESTApp.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.zhuk.SensorRESTApp.models.Measure;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, BigDecimal> {

	int countByRaining(boolean raining);

}
