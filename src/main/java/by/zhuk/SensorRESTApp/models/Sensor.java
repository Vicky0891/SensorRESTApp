package by.zhuk.SensorRESTApp.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
	private String name;

	@OneToMany(mappedBy = "sensorName")
	private List<Measure> measures;

	public Sensor() {
	}

	public Sensor(String name) {
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

	@Override
	public String toString() {
		return "Sensor [name=" + name + ", measures=" + measures + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(measures, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sensor other = (Sensor) obj;
		return Objects.equals(measures, other.measures) && Objects.equals(name, other.name);
	}

}
