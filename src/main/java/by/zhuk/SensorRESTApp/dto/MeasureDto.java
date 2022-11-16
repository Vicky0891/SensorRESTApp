package by.zhuk.SensorRESTApp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class MeasureDto {

	@DecimalMin(value = "-100", message = "Value should be between -100 and 100 degrees")
	@DecimalMax(value = "100")
	private BigDecimal value;

	@NotNull
	private boolean raining;

	private SensorDto sensorDto;

	private LocalDateTime measuredAt;

	public MeasureDto() {
	}

	public MeasureDto(BigDecimal value, boolean raining, SensorDto sensorDto) {
		this.value = value;
		this.raining = raining;
		this.sensorDto = sensorDto;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public SensorDto getSensorDto() {
		return sensorDto;
	}

	public void setSensorDto(SensorDto sensorDto) {
		this.sensorDto = sensorDto;
	}

	public LocalDateTime getMeasuredAt() {
		return measuredAt;
	}

	public void setMeasuredAt(LocalDateTime measuredAt) {
		this.measuredAt = measuredAt;
	}

}
