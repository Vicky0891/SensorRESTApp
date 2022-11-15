package by.zhuk.SensorRESTApp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import by.zhuk.SensorRESTApp.models.Sensor;

public class MeasureDto {

	@Min(value = -100, message = "Value should be between -100 and 100 degrees")
	@Max(value = 100)
	private double value;

	@NotNull
	private boolean raining;

	private Sensor sensor;

	private LocalDateTime measuredAt;

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

	public LocalDateTime getMeasuredAt() {
		return measuredAt;
	}

	public void setMeasuredAt(LocalDateTime measuredAt) {
		this.measuredAt = measuredAt;
	}

}
