package model;

import java.io.Serializable;
import java.util.List;

public class Drone extends Capture implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String serialNumber;
	private String model;
	private String manufacturer;
	private String mac;
	private String ipv4;
	private String ipv6;
	private String firmware;
	private Double positionY;
	private Double positionX;
	private Double altitude;
	private Double distance;
	private String NDZStatus;
	private Pilot pilot;

	public Drone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Drone(String snapshotTimestamp) {
		super(snapshotTimestamp);
		// TODO Auto-generated constructor stub
	}

	public Drone(String serialNumber, String model, String manufacturer, String mac, String ipv4,
			String ipv6, String firmware, Double positionY, Double positionX, Double altitude) {
		super();

		this.serialNumber = serialNumber;
		this.model = model;
		this.manufacturer = manufacturer;
		this.mac = mac;
		this.ipv4 = ipv4;
		this.ipv6 = ipv6;
		this.firmware = firmware;
		this.positionY = positionY;
		this.positionX = positionX;
		this.altitude = altitude;
	}

	public Drone(List<Drone> drones, String snapshotTimestamp) {
		super(drones, snapshotTimestamp);
		// TODO Auto-generated constructor stub
	}

	public Drone(List<Drone> drones) {
		super(drones);
		// TODO Auto-generated constructor stub
	}
	
	public Drone(String serialNumber, String model, String manufacturer, String mac, String ipv4, String ipv6,
			String firmware, double positionY, double positionX, double altitude) {
		// TODO Auto-generated constructor stub
		this.serialNumber = serialNumber;
		this.model = model;
		this.manufacturer = manufacturer;
		this.mac = mac;
		this.ipv4 = ipv4;
		this.ipv6 = ipv6;
		this.firmware = firmware;
		this.positionY = positionY;
		this.positionX = positionX;
		this.altitude = altitude;
	}


	public Drone(String serialNumber, String model, String manufacturer, String firmware, double positionY,
			double positionX, double distance) {
		// TODO Auto-generated constructor stub
		this.serialNumber = serialNumber;
		this.model = model;
		this.manufacturer = manufacturer;
		this.firmware = firmware;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
	}

	public Drone(String serialNumber, String model, String manufacturer, String firmware, double positionY,
			double positionX, double distance, String NDZStatus) {
		// TODO Auto-generated constructor stub
		this.serialNumber = serialNumber;
		this.model = model;
		this.manufacturer = manufacturer;
		this.firmware = firmware;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
		this.NDZStatus = NDZStatus;
	}
	
	public Drone(String serialNumber, Pilot pilot, String model, String manufacturer, String firmware,
			double positionY, double positionX, double distance, String nFZStatus) {
		// TODO Auto-generated constructor stub
		this.serialNumber = serialNumber;
		this.pilot = pilot;
		this.model = model;
		this.manufacturer = manufacturer;
		this.firmware = firmware;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
		this.NDZStatus = nFZStatus;
	}

	public Drone(String serialNumber, Pilot pilot, String model, double positionY, double positionX,
			double distance, String nFZStatus) {
		// TODO Auto-generated constructor stub
		this.serialNumber = serialNumber;
		this.pilot = pilot;
		this.model = model;
		this.positionY = positionY;
		this.positionX = positionX;
		this.distance = distance;
		this.NDZStatus = nFZStatus;
	}

	@Override
	public List<Drone> getDrones() {
		// TODO Auto-generated method stub
		return super.getDrones();
	}

	@Override
	public void setDrones(List<Drone> drones) {
		// TODO Auto-generated method stub
		super.setDrones(drones);
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber  =  serialNumber;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model  =  model;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer  =  manufacturer;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac  =  mac;
	}

	public String getIpv4() {
		return this.ipv4;
	}

	public void setIpv4(String ipv4) {
		this.ipv4  =  ipv4;
	}

	public String getIpv6() {
		return this.ipv6;
	}

	public void setIpv6(String ipv6) {
		this.ipv6  =  ipv6;
	}

	public String getFirmware() {
		return this.firmware;
	}

	public void setFirmware(String firmware) {
		this.firmware  =  firmware;
	}

	public Double getPositionY() {
		return this.positionY;
	}

	public void setPositionY(Double positionY) {
		this.positionY  =  positionY;
	}

	public Double getPositionX() {
		return this.positionX;
	}

	public void setPositionX(Double positionX) {
		this.positionX  =  positionX;
	}

	public Double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude  =  altitude;
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public double setDistance2(Double position1, Double position2) {
		return this.distance= Math.sqrt(Math.pow(250000 - position1, 2) + Math.pow(250000 - position2, 2))/1000;
	}
	
	public String getNDZStatus() {
		return this.NDZStatus;
	}

	public void setNDZStatus(String NFZStatus) {
		this.NDZStatus = NFZStatus;
	}
	
	public String setNDZStatus1(String NFZ) {
		return this.NDZStatus = NFZ;
	}
	
	public String setNDZStatus2(String NFZ) {
		return this.NDZStatus = NFZ;
	}
	
	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public String toString() {
		return "Drone [serialNumber=" + serialNumber + ", model=" + model + ", manufacturer=" + manufacturer + ", mac="
				+ mac + ", ipv4=" + ipv4 + ", ipv6=" + ipv6 + ", firmware=" + firmware + ", positionY=" + positionY
				+ ", positionX=" + positionX + ", altitude=" + altitude + ", distance=" + distance + ", NDZStatus=" + NDZStatus
				+ "]\n";
	}



}
