package Entity;

public class Railway {
	private String railway_id;
	private String railway_name;
	private String grade;
	private String speed;
	private String lines;
	private String track;
	private String min_radius;
	private String slope;
	
	public Railway() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Railway(String railway_id, String railway_name, String grade, String speed, String lines, String track,
			String min_radius, String slope) {
		super();
		this.railway_id = railway_id;
		this.railway_name = railway_name;
		this.grade = grade;
		this.speed = speed;
		this.lines = lines;
		this.track = track;
		this.min_radius = min_radius;
		this.slope = slope;
	}

	public String getRailway_id() {
		return railway_id;
	}

	public void setRailway_id(String railway_id) {
		this.railway_id = railway_id;
	}

	public String getRailway_name() {
		return railway_name;
	}

	public void setRailway_name(String railway_name) {
		this.railway_name = railway_name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getMin_radius() {
		return min_radius;
	}

	public void setMin_radius(String min_radius) {
		this.min_radius = min_radius;
	}

	public String getSlope() {
		return slope;
	}

	public void setSlope(String slope) {
		this.slope = slope;
	}

	@Override
	public String toString() {
		return "Railway [railway_id=" + railway_id + ", railway_name=" + railway_name + ", grade=" + grade + ", speed="
				+ speed + ", lines=" + lines + ", track=" + track + ", min_radius=" + min_radius + ", slope=" + slope
				+ "]";
	}
	
	
}
