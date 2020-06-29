package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import Entity.Plan;
import Entity.Railway;
import Entity.coordinate;
import Exception.DaoException;
import Jdbc.JdbcUtils;

public class RailwayDao {
	QueryRunner qRunner = new QueryRunner();
	
	public List<Railway> searchRailway() {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select * From RAILWAY";
			List<Railway> rs = qRunner.query(conn, sql,new BeanListHandler<Railway>(Railway.class));
			conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public List<Plan> searchPlan(String railway_id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select * From PROGRAM,RAILWAY "
					+ "Where PROGRAM.railway_id=RAILWAY.railway_id And RAILWAY.railway_id=?";
			String sql2 = "Select line_id,x,y_ground,y_design From LINE Where line_id=? ORDER BY (x+0)";
			Object[] params = { railway_id };
			List<Plan> plans = qRunner.query(conn, sql,new BeanListHandler<Plan>(Plan.class),params);
			for(Plan plan : plans) {
				String id = plan.getLine_id();
				List<coordinate> coordinate = qRunner.query(conn, sql2, new BeanListHandler<coordinate>(coordinate.class), id);
				plan.setCoordinate(coordinate);
			}
			conn.close();
			return plans;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void insertPlan(String railway_id,String plan_name, String lenght, String tunnel, String bridge, 
							String height, int earthwork, int cost, String coordinate) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql1 = "Select max(plan_id) From PROGRAM";
			int plan_id;
			String id = qRunner.query(conn, sql1, new ScalarHandler<>());
			if(id == null || id.contentEquals("")) {
				plan_id=1;
			}else {
				plan_id = Integer.parseInt(id);
				plan_id++;
			}
			
			int line_id=plan_id;
			
			String sql3 = "INSERT INTO PROGRAM (plan_id, plan_name, railway_id, line_id, lenght, tunnel, bridge, height, earthwork, cost)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			Object[] param1 = { plan_id, plan_name, railway_id, line_id, lenght, tunnel, bridge, height, earthwork, cost};
			qRunner.update(conn, sql3, param1);
			
			String sql4 = "INSERT INTO LINE ( line_id, x, y_ground, y_design, railway_id) VALUES (?,?,?,?,?)";
			String[] lines = coordinate.split("\r\n");
			for(String x : lines) {
				String[] line = x.split("\t");
				Object[] param2 = { line_id, line[0], line[1], line[2], railway_id};
				qRunner.update(conn, sql4, param2);
			}
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void deletePlan(String plan_id, String line_id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql1 = "Delete From PROGRAM Where plan_id=?";
			String sql2 = "Delete From LINE Where line_id=?";
			qRunner.update(conn, sql1, plan_id);
			qRunner.update(conn, sql2, line_id);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void insertRailway(String railway_name, String grade,
									String speed, String lines, String track, String min_radius, String slope) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql1 = "Select max(railway_id) From RAILWAY";
			int railway_id;
			String id = qRunner.query(conn, sql1, new ScalarHandler<>());
			if(id == null || id.contentEquals("")) {
				railway_id=1;
			}else {
				railway_id = Integer.parseInt(id);
				railway_id++;
			}
			String sql2 = "INSERT INTO RAILWAY (railway_id, railway_name,grade,speed,lines,track,min_radius ,slope) VALUES (?,?,?,?,?,?,?,?)";
			Object[] params = { railway_id, railway_name, grade, speed, lines, track, min_radius, slope };
			qRunner.update(conn, sql2, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void deleteRailway(String railway_id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql1 = "Delete From RAILWAY Where railway_id=?";
			String sql2 = "Delete From PROGRAM Where railway_id=?";
			String sql3 = "Delete From LINE Where railway_id=?";
			Object[] params = { railway_id };
			qRunner.update(conn, sql1, params);
			qRunner.update(conn, sql2, params);
			qRunner.update(conn, sql3, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void editRailway(String railway_id,String railway_name,String grade,String speed,String lines,String track,String min_radius,String slope) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update RAILWAY Set railway_name=?,grade=?,speed=?,lines=?,track=?,min_radius=?,slope=? Where railway_id=?";

			Object[] params = {  railway_name, grade, speed, lines, track, min_radius, slope, railway_id};
			qRunner.update(conn, sql, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void editPlan(String plan_id, String plan_name, String lenght, String tunnel, String bridge, String height) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update PROGRAM Set plan_name=?,lenght=?,tunnel=?,bridge=?,height=? Where plan_id=?";

			Object[] params = { plan_name, lenght, tunnel, bridge, height, plan_id };
			qRunner.update(conn, sql, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void editCoordinate(String plan_id, int earthwork, int cost, String line_id, String coordinate, String railway_id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql1 = "Delete From LINE Where line_id=?";
			qRunner.update(conn, sql1, line_id);
			String sql2 = "Insert INTO LINE ( line_id, x, y_ground, y_design, railway_id) VALUES (?,?,?,?,?)";
			String[] lines = coordinate.split("\r\n");
			for(String x : lines) {
				String[] line = x.split("\t");
				Object[] param = { line_id, line[0], line[1], line[2], railway_id};
				qRunner.update(conn, sql2, param);
			}
			String sql3 = "Update PROGRAM Set earthwork=?,cost=? Where plan_id=?";
			Object[] param2 = { earthwork, cost, plan_id};
			qRunner.update(conn, sql3, param2);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	
}
