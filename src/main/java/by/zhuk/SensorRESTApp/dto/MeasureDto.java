package by.zhuk.SensorRESTApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import by.zhuk.SensorRESTApp.models.Sensor;

public class MeasureDto {

	@Min(value = -100, message = "Value should be between -100 and 100 degrees")
	@Max(value = 100)
	@NotEmpty
	private double value;

	@NotEmpty(message = "Field should not be empty")
	private boolean raining;

	private Sensor sensor;

	public MeasureDto() {
	}

	public MeasureDto(double value, boolean raining, Sensor sensor) {
		this.value = value;
		this.raining = raining;
		this.sensor = sensor;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}
