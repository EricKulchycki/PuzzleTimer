package sep.myapplication.acceptance;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.robotium.solo.Solo;
import org.junit.Test;
import junit.framework.TestCase;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.R;
import sep.myapplication.persistence.DatabaseInterface;
import sep.myapplication.view.MainActivity;
import sep.myapplication.view.TimeListActivity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class ModifyDeleteTimesTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public ModifyDeleteTimesTest()
    {
        super(MainActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }


    public void testModifyDeleteTimes() {

        Main.startUp();
        DatabaseInterface testDB = Services.getDataAccess(Main.dbName);

        solo.unlockScreen();
        //add times
        solo.assertCurrentActivity("Main Activity", MainActivity.class);

        //Run the timer a couple times and add to the DB
        for (int i = 0; i < 3; i++) {
            solo.clickOnView(solo.getView(R.id.inspection));
            solo.clickOnView(solo.getView(R.id.startTimer));
            solo.sleep(5000*i);
            solo.clickOnView(solo.getView(R.id.stopTimer));
        }

        solo.clickOnView(solo.getView(R.id.timeList));
        solo.assertCurrentActivity("Expected timeList", TimeListActivity.class);
        assertTrue(testDB.getIndex(5000) == -1);

        ListView list = (ListView)solo.getView(R.id.timeListView);
        View listItem = list.getChildAt(0);



        solo.clickOnView(listItem);
        solo.clickOnButton("+2 sec");
        assertTrue(testDB.getTime(0) > 0);
        solo.clickOnButton("Delete");
        assertTrue(testDB.getTime(0) > 0);
    }


}