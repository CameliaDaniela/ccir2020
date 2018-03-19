package ccir2020MV.model.repository.interfaces;

import java.util.Date;
import java.util.List;

import ccir2020MV.model.base.Activity;

public interface RepositoryActivity {

	List<Activity> getActivities();
	boolean addActivity(Activity activity);
	boolean removeActivity(Activity activity);
	boolean saveActivities();
	int count();
	List<Activity> activitiesByName(String name);
	List<Activity> activitiesOnDate(String name, Date d);
	
}
