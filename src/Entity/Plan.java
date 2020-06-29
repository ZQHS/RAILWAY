package Entity;

import java.util.List;

public class Plan {
	private String plan_id;
	private String plan_name;
	private String railway_id;
	private String line_id;
	private String lenght;
	private String tunnel;
	private String bridge;
	private String height;
	private String earthwork;
	private String cost;
	private List<coordinate> coordinate;
	
	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plan(String plan_id, String plan_name, String railway_id, String line_id, String lenght, String tunnel,
			String bridge, String height, String earthwork, String cost, List<Entity.coordinate> coordinate) {
		super();
		this.plan_id = plan_id;
		this.plan_name = plan_name;
		this.railway_id = railway_id;
		this.line_id = line_id;
		this.lenght = lenght;
		this.tunnel = tunnel;
		this.bridge = bridge;
		this.height = height;
		this.earthwork = earthwork;
		this.cost = cost;
		this.coordinate = coordinate;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public String getLenght() {
		return lenght;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public String getTunnel() {
		return tunnel;
	}

	public void setTunnel(String tunnel) {
		this.tunnel = tunnel;
	}

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getEarthwork() {
		return earthwork;
	}

	public void setEarthwork(String earthwork) {
		this.earthwork = earthwork;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public List<coordinate> getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(List<coordinate> coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "Plan [plan_id=" + plan_id + ", plan_name=" + plan_name + ", railway_id=" + railway_id + ", line_id="
				+ line_id + ", lenght=" + lenght + ", tunnel=" + tunnel + ", bridge=" + bridge + ", height=" + height
				+ ", earthwork=" + earthwork + ", cost=" + cost + ", coordinate=" + coordinate + "]";
	}
	

}
