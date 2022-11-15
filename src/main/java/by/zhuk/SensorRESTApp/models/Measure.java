package by.zhuk.SensorRESTApp.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measure")
public class Measure implements Serializable {

	@Id
	@Column(name = "value")
	@DecimalMin(value = "-100", message = "Value should be between -100 and 100 degrees")
	@DecimalMax(value = "100")
	private BigDecimal value;

	@Column(name = "raining")
	@NotNull
	private boolean raining;

	@ManyToOne
	@JoinColumn(name = "sensor_name", referencedColumnName = "name")
	private Sensor sensorName;

	@Column(name = "measured_at")
	private LocalDateTime measuredAt;

	public Measure() {
	}

	public Measure(BigDecimal value, boolean raining, Sensor sensorName) {
		this.value = value;
		this.raining = raining;
		this.sensorName = sensorName;
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

	public Sensor getSensor() {
		return sensorName;
	}

	public void setSensor(Sensor sensorName) {
		this.sensorName = sensorName;
	}

	public LocalDateTime getMeasuredAt() {
		return measuredAt;
	}

	public void setMeasuredAt(LocalDateTime measuredAt) {
		this.measuredAt = measuredAt;
	}

	@Override
	public String toString() {
		return "Measure [value=" + value + ", raining=" + raining + ", sensor=" + sensorName + ", measuredAt="
				+ measuredAt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(measuredAt, raining, sensorName, value);
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
				&& Objects.equals(sensorName, other.sensorName) && Objects.equals(value, other.value);
	}

}
