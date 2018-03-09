package sep.myapplication.Application;

import sep.myapplication.persistence.DataAccessObject;
import sep.myapplication.persistence.DatabaseInterface;

public class Services
{
	private static DatabaseInterface dataAccessService = null;

	public static DatabaseInterface createDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(dbName);
			dataAccessService.open(Main.getDBPathName());
		}
		return dataAccessService;
	}

	public static DatabaseInterface createDataAccess(DatabaseInterface alternateDataAccessService)
	{
		if (dataAccessService == null)
		{
			dataAccessService = alternateDataAccessService;
			dataAccessService.open(Main.getDBPathName());
		}
		return dataAccessService;
	}

	public static DatabaseInterface getDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}
