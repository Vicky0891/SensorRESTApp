package by.zhuk.SensorRESTApp.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
	@Column(name = "name")
	@Id
	private String name;

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

	@Override
	public String toString() {
		return "Sensor [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}

}
