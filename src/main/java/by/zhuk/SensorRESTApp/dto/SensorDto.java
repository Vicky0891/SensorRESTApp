package by.zhuk.SensorRESTApp.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import by.zhuk.SensorRESTApp.models.Measure;

public class SensorDto {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
	private String name;

	private List<Measure> measures;

	public SensorDto() {
	}

	public SensorDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Measure> getMeasures() {
		return measures;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

}
