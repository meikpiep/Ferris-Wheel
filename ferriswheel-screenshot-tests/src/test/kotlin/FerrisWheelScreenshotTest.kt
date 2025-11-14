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
import sergio.sastre.uitesting.utils.common.UiMode
import sergio.sastre.uitesting.utils.utils.waitForActivity
import sergio.sastre.uitesting.utils.utils.waitForView

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class FerrisWheelScreenshotTest {
    private val pathPrefix = "src/test/resources/screenshots"

    @Config(sdk = [30])
    @Test fun sizing() {
        listOf(75, 150, 200, 400, 500, 600, 800, 1000, 1500).forEach { sizeInDp ->
            val activityScenario =
                RobolectricActivityScenarioConfigurator.ForView()
                    .setDeviceScreen(DeviceScreen(sizeInDp, sizeInDp))
                    .setUiMode(UiMode.NIGHT)
                    .setFontSize(FontSize.NORMAL)
                    .launchConfiguredActivity(TRANSPARENT)

            val activity = activityScenario.waitForActivity()

            val viewHolder = waitForView {
                val ferrisView = FerrisWheelView(
                    activity
                )

                ferrisView.calculateNumberOfCabins = true

                ferrisView
            }

            viewHolder.captureRoboImage("$pathPrefix/size_dp_${sizeInDp}.png")

            activityScenario.close()
        }
    }

    @Config(sdk = [30])
    @Test
    fun defaultConfig() {
        val activityScenario =
            RobolectricActivityScenarioConfigurator.ForView()
                .setDeviceScreen(DeviceScreen.Phone.PIXEL_4A)
                .setUiMode(UiMode.NIGHT)
                .setFontSize(FontSize.NORMAL)
                .setDisplaySize(DisplaySize.NORMAL)
                .launchConfiguredActivity(TRANSPARENT)

        val activity = activityScenario.waitForActivity()

        val viewHolder = waitForView {
            FerrisWheelView(
                activity
            )
        }

        viewHolder.captureRoboImage("$pathPrefix/default.png")

        activityScenario.close()
    }

    @Config(sdk = [30])
    @Test
    fun lotsOfCabins() {
        val activityScenario =
            RobolectricActivityScenarioConfigurator.ForView()
                .setDeviceScreen(DeviceScreen.Phone.PIXEL_4A)
                .setLocale("en_XA")
                .setUiMode(UiMode.NIGHT)
                .setFontSize(FontSize.NORMAL)
                .launchConfiguredActivity(TRANSPARENT)

        val activity = activityScenario.waitForActivity()

        val viewHolder = waitForView {
            val view = FerrisWheelView(
                activity
            )

            view.numberOfCabins = 30
            view.cabinSize = 3

            view
        }

        viewHolder.captureRoboImage("$pathPrefix/lots-of-cabins.png")

        activityScenario.close()
    }
}