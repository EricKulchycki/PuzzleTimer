package sep.myapplication.acceptance;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
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

import sep.myapplication.R;
import sep.myapplication.view.MainActivity;
import sep.myapplication.view.TimeListActivity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(AndroidJUnit4.class)
public class ModifyDeleteTimesTest extends TestCase {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Solo solo;

    @Before
    public void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    @Test
    public void modifyDeleteTimes() throws Exception {
        solo.unlockScreen();
        //add times
        solo.assertCurrentActivity("Main Activity", MainActivity.class);

        for (int i = 0; i < 3; i++) {
            solo.clickOnView(solo.getView(R.id.inspection));
            solo.clickOnView(solo.getView(R.id.startTimer));
            solo.sleep(5000*i);
            solo.clickOnView(solo.getView(R.id.stopTimer));
        }

        solo.clickOnView(solo.getView(R.id.timeList));
        solo.assertCurrentActivity("Expected timeList", TimeListActivity.class);

        ListView list = (ListView)solo.getView(R.id.listview);
        View listItem = list.getChildAt(0);

        solo.clickOnView(listItem);
        solo.clickOnView(R.id.Modify);
        solo.clickOnView(R.id.Delete);
        solo.clickOnView(R.id.ClearList);
    }
}