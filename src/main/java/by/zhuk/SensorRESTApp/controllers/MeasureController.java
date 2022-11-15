package by.zhuk.SensorRESTApp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.zhuk.SensorRESTApp.dto.MeasureDto;
import by.zhuk.SensorRESTApp.models.Measure;
import by.zhuk.SensorRESTApp.services.MeasureService;
import by.zhuk.SensorRESTApp.utils.MeasureNotCreatedException;
import by.zhuk.SensorRESTApp.utils.SensorErrorResponse;
import by.zhuk.SensorRESTApp.utils.SensorNotCreatedException;
import by.zhuk.SensorRESTApp.utils.SensorNotExistException;

@RestController
@RequestMapping("/measurements")
public class MeasureController {

	private MeasureService measureService;
	private ModelMapper modelMapper;

	@Autowired
	public MeasureController(MeasureService measureService, ModelMapper modelMapper) {
		this.measureService = measureService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<MeasureDto> getAll() {
		return measureService.findAll().stream().map(this::convertToMeasureDto).collect(Collectors.toList());
	}

	@GetMapping("/rainyDaysCount")
	public int getRainyDays() {
		return measureService.getRainyDays();
	}

	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addMeasure(@RequestBody @Valid MeasureDto measureDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError error : errors) {
				errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
			}
			throw new MeasureNotCreatedException(errorMessage.toString());
		}
		measureService.saveMeasure(convertToMeasure(measureDto));
		return ResponseEntity.ok(HttpStatus.OK);
	}

	private MeasureDto convertToMeasureDto(Measure measure) {
		return modelMapper.map(measure, MeasureDto.class);
	}

	private Measure convertToMeasure(MeasureDto measureDto) {
		return modelMapper.map(measureDto, Measure.class);
	}
	
	@ExceptionHandler
	private ResponseEntity<SensorErrorResponse> handleException(SensorNotExistException e) {
		SensorErrorResponse response = new SensorErrorResponse("Sensor with this name not exists",
				System.currentTimeMillis());
		return new ResponseEntity<SensorErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
		SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<SensorErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
