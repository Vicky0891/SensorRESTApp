package by.zhuk.SensorRESTApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDto {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
	private String name;

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

}
