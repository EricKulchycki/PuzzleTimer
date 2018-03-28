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

import sep.myapplication.Application.Main;
import sep.myapplication.Application.Services;
import sep.myapplication.R;
import sep.myapplication.persistence.DatabaseInterface;
import sep.myapplication.view.MainActivity;
import sep.myapplication.view.TimeListActivity;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


@RunWith(AndroidJUnit4.class)
public class ChangeScrambleSizeTest extends TestCase {



    public void testModifyScrambleSize() throws Exception {
        ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
        Solo solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());

        solo.unlockScreen();
        solo.assertCurrentActivity("Main Activity", MainActivity.class);
        solo.clickOnView(solo.getView(R.id.puzzleType));
        solo.clickOnMenuItem("2x2x2", true);

        //Check to see if it goes back to 3x3x3
        solo.clickOnView(solo.getView(R.id.puzzleType));
        solo.clickOnMenuItem("3x3x3", true);

    }
}