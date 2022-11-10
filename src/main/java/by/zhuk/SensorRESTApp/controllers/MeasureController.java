package by.zhuk.SensorRESTApp.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.zhuk.SensorRESTApp.dto.MeasureDto;
import by.zhuk.SensorRESTApp.models.Measure;
import by.zhuk.SensorRESTApp.services.MeasureService;

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
	public long getRainyDays() {
		return measureService.getRainyDays();
	}

	private MeasureDto convertToMeasureDto(Measure measure) {
		return modelMapper.map(measure, MeasureDto.class);
	}

}
