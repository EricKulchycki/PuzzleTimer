package sep.myapplication.acceptance;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.robotium.solo.Solo;
import org.junit.Test;
import junit.framework.TestCase;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sep.myapplication.R;
import sep.myapplication.view.MainActivity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestColourChange extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public TestColourChange()
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


    public void testColourChange() {

        solo.unlockScreen();
        solo.assertCurrentActivity("Main Activity", MainActivity.class);

        solo.clickOnView(solo.getView(R.id.changeColour));
        solo.clickOnMenuItem("Yellow", true);

        solo.clickOnView(solo.getView(R.id.changeColour));
        solo.clickOnMenuItem("Blue", true);

        solo.clickOnView(solo.getView(R.id.changeColour));
        solo.clickOnMenuItem("Purple", true);

        solo.clickOnView(solo.getView(R.id.changeColour));
        solo.clickOnMenuItem("Green", true);

        solo.clickOnView(solo.getView(R.id.changeColour));
        solo.clickOnMenuItem("White", true);
    }


}
