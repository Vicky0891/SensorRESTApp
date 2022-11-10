package by.zhuk.SensorRESTApp.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "measure")
public class Measure {

	@Column(name = "value")
	@Min(value = -100, message = "Value should be between -100 and 100 degrees")
	@Max(value = 100)
	@NotEmpty
	private double value;

	@Column(name = "raining")
	@NotEmpty(message = "Field should not be empty")
	private boolean raining;

	@ManyToOne
	@JoinColumn(name = "sensor_name", referencedColumnName = "name")
	private Sensor sensor;

	@Column(name = "measured_at")
	private LocalDateTime measuredAt;

	public Measure() {
	}

	public Measure(double value, boolean raining, Sensor sensor) {
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

	@Override
	public String toString() {
		return "Measure [value=" + value + ", raining=" + raining + ", sensor=" + sensor + ", measuredAt=" + measuredAt
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(measuredAt, raining, sensor, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Measure other = (Measure) obj;
		return Objects.equals(measuredAt, other.measuredAt) && raining == other.raining
				&& Objects.equals(sensor, other.sensor)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

}
