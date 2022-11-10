package by.zhuk.SensorRESTApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.zhuk.SensorRESTApp.dto.SensorDto;
import by.zhuk.SensorRESTApp.models.Sensor;
import by.zhuk.SensorRESTApp.services.SensorService;
import by.zhuk.SensorRESTApp.utils.SensorErrorResponse;
import by.zhuk.SensorRESTApp.utils.SensorExistException;
import by.zhuk.SensorRESTApp.utils.SensorNotCreatedException;

@RestController
@RequestMapping("/sensors")
public class SensorController {

	private final SensorService sensorService;

	private final ModelMapper modelMapper;

	@Autowired
	public SensorController(SensorService sensorService, ModelMapper modelMapper) {
		this.sensorService = sensorService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
			}
			throw new SensorNotCreatedException(errorMessage.toString());
		}
		sensorService.save(convertToSensor(sensorDto));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	private Sensor convertToSensor(SensorDto sensorDto) {
		return modelMapper.map(sensorDto, Sensor.class);
	}

	@ExceptionHandler
	private ResponseEntity<SensorErrorResponse> handleException(SensorExistException e) {
		SensorErrorResponse response = new SensorErrorResponse("Person with this name already exist",
				System.currentTimeMillis());
		return new ResponseEntity<SensorErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
		SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<SensorErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
