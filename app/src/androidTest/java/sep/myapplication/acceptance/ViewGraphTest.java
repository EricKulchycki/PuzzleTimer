package sep.myapplication.acceptance;


import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;




import sep.myapplication.R;
import sep.myapplication.view.GraphActivity;
import sep.myapplication.view.MainActivity;




public class ViewGraphTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public ViewGraphTest()
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


    public void testViewGraph() {
        solo.unlockScreen();
        //add times
        solo.assertCurrentActivity("Main Activity", MainActivity.class);

        //Add some times to the DB
        for (int i = 0; i < 3; i++) {
            solo.clickOnView(solo.getView(R.id.inspection));
            solo.clickOnView(solo.getView(R.id.startTimer));
            solo.sleep(5000*i);
            solo.clickOnView(solo.getView(R.id.stopTimer));
        }

        solo.clickOnView(solo.getView(R.id.graphDisplay));
        solo.assertCurrentActivity("Expected Graph Activity", GraphActivity.class);
    }
}