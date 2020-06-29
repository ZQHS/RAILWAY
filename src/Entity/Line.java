package Entity;

import java.util.List;

public class Line {
	private String line_id;
	private List<coordinate> coordinate;
	
	public Line() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Line(String line_id, List<Entity.coordinate> coordinate) {
		super();
		this.line_id = line_id;
		this.coordinate = coordinate;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public List<coordinate> getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(List<coordinate> coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "Line [line_id=" + line_id + ", coordinate=" + coordinate + "]";
	}
	
}
