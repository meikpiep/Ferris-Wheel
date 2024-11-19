import android.graphics.Color.TRANSPARENT
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import ru.github.igla.ferriswheel.FerrisWheelView
import sergio.sastre.uitesting.robolectric.activityscenario.RobolectricActivityScenarioConfigurator
import sergio.sastre.uitesting.robolectric.config.screen.DeviceScreen
import sergio.sastre.uitesting.utils.common.DisplaySize
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForMeasuredViewHolder
import sergio.sastre.uitesting.utils.utils.waitForView

@RunWith(RobolectricTestRunner::class) // or ParameterizedRobolectricTestRunner for parameterized test
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class SnapViewHolderTest {

    @Config(sdk = [30]) // Do not use qualifiers if using `setDeviceScreen()
    @Test
    fun defaultConfig() {
        val activityScenario =
            RobolectricActivityScenarioConfigurator.ForView()
                .setDeviceScreen(DeviceScreen.Phone.PIXEL_4A)
                .setLocale("en_XA")
                .setUiMode(UiMode.NIGHT)
                //.setTheme(R.style.Custom_Theme)
                //.setOrientation(Orientation.PORTRAIT)
                .setFontSize(FontSize.NORMAL)
                .setDisplaySize(DisplaySize.NORMAL)
                .launchConfiguredActivity(TRANSPARENT)

        val activity = activityScenario.waitForActivity()
        //val layout = activity.inflateAndWaitForIdle(R.layout.memorise_row)

        val viewHolder = waitForView {
            FerrisWheelView(
                activity
            )
        }

        viewHolder.captureRoboImage("path/MemoriseViewHolder.png")

        activityScenario.close()
    }

    @Config(sdk = [30]) // Do not use qualifiers if using `setDeviceScreen()
    @Test
    fun lotsOfCabins() {
        val activityScenario =
            RobolectricActivityScenarioConfigurator.ForView()
                .setDeviceScreen(DeviceScreen.Phone.PIXEL_4A)
                .setLocale("en_XA")
                .setUiMode(UiMode.NIGHT)
                //.setTheme(R.style.Custom_Theme)
                //.setOrientation(Orientation.PORTRAIT)
                .setFontSize(FontSize.NORMAL)
                .setDisplaySize(DisplaySize.NORMAL)
                .launchConfiguredActivity(TRANSPARENT)

        val activity = activityScenario.waitForActivity()
        //val layout = activity.inflateAndWaitForIdle(R.layout.memorise_row)

        val viewHolder = waitForView {
            val view = FerrisWheelView(
                activity
            )

            view.numberOfCabins = 30
            view.cabinSize = 3

            view
        }

        viewHolder.captureRoboImage("path/MemoriseViewHolder2.png")

        activityScenario.close()
    }
}