package Service;

import Dao.RailwayDao;
import Exception.DaoException;
import Exception.ServiceException;


public class RailwayService {
	RailwayDao railwayDao=new RailwayDao();
	
	public Void insertPlan(String railway_id, String plan_name, String lenght, String tunnel, String bridge, String height, String coordinate) {
		try {
			int earthwork=0;
			int cost=0;
			String[] lines = coordinate.split("\r\n");
			for(String x : lines) {
				String[] line = x.split("\t");
				float y_ground=Float.parseFloat(line[1]);
				float y_design=Float.parseFloat(line[2]);
				if((y_design-y_ground)>=0) {
					earthwork+=(12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100;
					cost+=(12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100*30;
				}else if(y_ground-y_design<20){
					earthwork+=(12.1+(y_ground-y_design)/2)*(y_ground-y_design)*100;
					cost+=(12.1+(y_ground-y_design)/2)*(y_ground-y_design)*100*36;
				}
			}
			if(Float.parseFloat(bridge)<=30) {
				cost+=Float.parseFloat(bridge)*18000;
			}else if(Float.parseFloat(bridge)>1000) {
				cost+=Float.parseFloat(bridge)*19570;
			}else {
				cost+=Float.parseFloat(bridge)*18500;
			}
			cost+=Float.parseFloat(tunnel)*52000;
			cost=cost/10000;
			railwayDao.insertPlan( railway_id, plan_name, lenght, tunnel, bridge, height, earthwork, cost, coordinate);
			return null;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("系统错误");
		}
	}
	
	public Void editCoordinate(String plan_id, String tunnel, String bridge, String line_id, String coordinate, String railway_id ){
		try {
			int earthwork=0;
			int cost=0;
			String[] lines = coordinate.split("\r\n");
			for(String x : lines) {
				String[] line = x.split("\t");
				float y_ground=Float.parseFloat(line[1]);
				float y_design=Float.parseFloat(line[2]);
				if((y_design-y_ground)>=0) {
					earthwork+=(12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100;
					cost+=(12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100*30;
					System.out.print(line[0]+"\t");
					System.out.println((12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100);
				}else if(y_ground-y_design<20){
					earthwork+=(12.1+(y_ground-y_design)/2)*(y_ground-y_design)*100;
					cost+=(12.1+(y_ground-y_design)/2)*(y_ground-y_design)*100*36;
					System.out.print(line[0]+"\t");
					System.out.println(-(12.1+(12.5*(y_design-y_ground))/4)*(y_design-y_ground)*100);
				}
			}
			if(Float.parseFloat(bridge)<=30) {
				cost+=Float.parseFloat(bridge)*18000;
			}else if(Float.parseFloat(bridge)>1000) {
				cost+=Float.parseFloat(bridge)*19570;
			}else {
				cost+=Float.parseFloat(bridge)*18500;
			}
			cost+=Float.parseFloat(tunnel)*52000;
			cost=cost/10000;
			railwayDao.editCoordinate( plan_id, earthwork, cost, line_id, coordinate, railway_id);
			return null;
		}
		catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("系统错误");
		}
	}
}
