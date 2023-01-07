package model;

import java.io.Serializable;
import java.util.List;


public class Capture implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Drone> drones;
	private String snapshotTimestamp;

	public Capture() {
		// TODO Auto-generated constructor stub
	}

	public Capture(List<Drone> drones, String snapshotTimestamp) {
		super();
		this.drones = drones;
		this.snapshotTimestamp = snapshotTimestamp;
	}
	
	public Capture(List<Drone> drones) {
		super();
		this.drones = drones;
	}

	public Capture(String snapshotTimestamp) {
		// TODO Auto-generated constructor stub
		this.snapshotTimestamp = snapshotTimestamp;
	}

	public List<Drone> getDrones() {
		return drones;
	}

	public void setDrones(List<Drone> drones) {
		this.drones = drones;
	}

	public void setDrones(String attributeValue) {
		// TODO Auto-generated method stub

	}

	public String getSnapshotTimestamp() {
		return snapshotTimestamp;
	}

	public void setSnapshotTimestamp(String snapshotTimestamp) {
		this.snapshotTimestamp = snapshotTimestamp;
	}
	
	@Override
	public String toString() {
		return "Capture [drones= " + drones + ", snapshotTimestamp= " + snapshotTimestamp + "]\n";
	}

}
