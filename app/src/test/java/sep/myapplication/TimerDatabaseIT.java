package sep.myapplication;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.sql.SQLException;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.objects.Timer;
import sep.myapplication.persistence.*;

public class TimerDatabaseIT extends TestCase {


    public TimerDatabaseIT(String arg0)
    {
        super(arg0);
    }

    DataAccessStub DASTest = new DataAccessStub(Main.dbName);
    DatabaseInterface DBTest;
    Timer TTest = new Timer();

    @Test
    public void testInitialValues() throws Exception{
        TTest = new Timer();
        DBTest = new DataAccessStub(Main.dbName);
    }
}

