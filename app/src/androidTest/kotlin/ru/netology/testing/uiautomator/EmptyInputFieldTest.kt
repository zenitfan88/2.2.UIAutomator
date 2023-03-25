package ru.netology.testing.uiautomator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class emptyInputFieldTest {
    private lateinit var device: UiDevice;
    private val emptyText = "     ";

    @Before
    fun beforeEachTest() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        val launcherPackage = device.launcherPackageName;
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT);

        val contex = ApplicationProvider.getApplicationContext<Context>()
        val intent = contex.packageManager.getLaunchIntentForPackage(MODEL_PACKAGE);

        contex.startActivity(intent);
        device.wait(Until.hasObject(By.pkg(MODEL_PACKAGE)), TIMEOUT)
    }

    @Test
    fun EmptyInputFieldTest() {
        val expected  = device.findObject(By.res(MODEL_PACKAGE,"textToBeChanged")).text

        device.findObject(By.res(MODEL_PACKAGE,"userInput")).text = emptyText;
        device.findObject(By.res(MODEL_PACKAGE,"buttonChange")).click();

        val actual = device.findObject(By.res(MODEL_PACKAGE,"textToBeChanged")).text

        assertEquals(actual, expected);
    }
}