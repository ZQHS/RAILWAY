package Entity;

public class coordinate {
	private String x;
	private String y_ground;
	private String y_design;
	
	public coordinate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public coordinate(String x, String y_ground, String y_design) {
		super();
		this.x = x;
		this.y_ground = y_ground;
		this.y_design = y_design;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY_ground() {
		return y_ground;
	}

	public void setY_ground(String y_ground) {
		this.y_ground = y_ground;
	}

	public String getY_design() {
		return y_design;
	}

	public void setY_design(String y_design) {
		this.y_design = y_design;
	}

	@Override
	public String toString() {
		return "coordinate [x=" + x + ", y_ground=" + y_ground + ", y_design=" + y_design + "]";
	}
	
}
