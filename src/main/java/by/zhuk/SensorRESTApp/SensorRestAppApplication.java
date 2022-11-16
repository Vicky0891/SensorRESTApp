package by.zhuk.SensorRESTApp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import by.zhuk.SensorRESTApp.dto.MeasureDto;
import by.zhuk.SensorRESTApp.dto.SensorDto;

@SpringBootApplication
public class SensorRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorRestAppApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();

		String urlForCreateSensor = "http://localhost:8080/sensors/registration";
		String testSensorName = "Test sensor";

		Map<String, String> jsonToSend = new HashMap<>();
		jsonToSend.put("name", testSensorName);

		HttpEntity<Map<String, String>> requestForCreate = new HttpEntity<Map<String, String>>(jsonToSend);

		restTemplate.postForObject(urlForCreateSensor, requestForCreate, String.class);

		SensorDto sensorDto = new SensorDto(testSensorName);

		String urlForAddMeasure = "http://localhost:8080/measurements/add";

		for (int i = 0; i < 1000; i++) {
			double randomTemp = Math.random() * 200 - 100;
			Random random = new Random();
			boolean randomRainyDay = random.nextBoolean();

			MeasureDto measureDto = new MeasureDto(BigDecimal.valueOf(randomTemp), randomRainyDay, sensorDto);
			HttpEntity<MeasureDto> requestForAdd = new HttpEntity<MeasureDto>(measureDto);
			restTemplate.postForObject(urlForAddMeasure, requestForAdd, String.class);
		}

		String urlForGetMeasure = "http://localhost:8080/measurements";
		ResponseEntity<String> responceForGetMeasure = restTemplate.getForEntity(urlForGetMeasure, String.class);
		System.out.println(responceForGetMeasure.getBody());
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
