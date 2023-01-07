package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Violations.findAll", query = "SELECT v FROM Violations v")
public class Violations implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int violations_id;

	private String snapshotTimestamp;
	private String serialNumber;
	private String model;
	private Double positionY;
	private Double positionX;
	private Double distance;
	private String pilotId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String NDZStatus;

	public Violations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Violations(int violations_id, String snapshotTimestamp, String serialNumber, String model, Double positionY,
			Double positionX, Double distance, String pilotId, String firstName, String lastName, String phoneNumber,
			String email, String nDZStatus) {
		super();
		this.violations_id = violations_id;
		this.snapshotTimestamp = snapshotTimestamp;
		this.serialNumber = serialNumber;
		this.model = model;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
		this.pilotId = pilotId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.NDZStatus = nDZStatus;
	}

	public Violations(String snapshotTimestamp, String serialNumber, String model, double positionY, double positionX,
			double distance, String pilotId, String firstName, String lastName, String phoneNumber, String email,
			String nFZStatus) {
		// TODO Auto-generated constructor stub
		this.snapshotTimestamp = snapshotTimestamp;
		this.serialNumber = serialNumber;
		this.model = model;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
		this.pilotId = pilotId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.NDZStatus = nFZStatus;
	}

	public Violations(String snapshotTimestamp, String serialNumber, String model, Double distance, String firstName,
			String lastName, String phoneNumber, String email) {
		// TODO Auto-generated constructor stub
		this.snapshotTimestamp = snapshotTimestamp;
		this.serialNumber = serialNumber;
		this.model = model;
		this.distance = distance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;

	}

	public int getViolations_id() {
		return violations_id;
	}

	public void setViolations_id(int violations_id) {
		this.violations_id = violations_id;
	}

	public String getSnapshotTimestamp() {
		return snapshotTimestamp;
	}

	public String setSnapshotTimestamp(String snapshotTimestamp) {
		return this.snapshotTimestamp = snapshotTimestamp;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPositionY() {
		return positionY;
	}

	public void setPositionY(Double positionY) {
		this.positionY = positionY;
	}

	public Double getPositionX() {
		return positionX;
	}

	public void setPositionX(Double positionX) {
		this.positionX = positionX;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public double setDistance2(Double position1, Double position2) {
		return this.distance = Math.sqrt(Math.pow(250000 - position1, 2) + Math.pow(250000 - position2, 2)) / 1000;
	}

	public String getPilotId() {
		return pilotId;
	}

	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fristName) {
		this.firstName = fristName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNDZStatus() {
		return NDZStatus;
	}

	public void setNDZStatus(String nDZStatus) {
		NDZStatus = nDZStatus;
	}

	public String setNDZStatus1(String NFZ) {
		return this.NDZStatus = NFZ;
	}

	public String setNDZStatus2(String NFZ) {
		return this.NDZStatus = NFZ;
	}

	public void setViolations(List<Violations> violations2) {
		// TODO Auto-generated method stub
		this.setViolations(violations2);
	}

	@Override
	public String toString() {
		return "Violations [violations_id=" + violations_id + ", snapshotTimestamp=" + snapshotTimestamp
				+ ", serialNumber=" + serialNumber + ", model=" + model + ", positionY=" + positionY + ", positionX="
				+ positionX + ", distance=" + distance + ", pilotId=" + pilotId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", NDZStatus="
				+ NDZStatus + "]";
	}

}
