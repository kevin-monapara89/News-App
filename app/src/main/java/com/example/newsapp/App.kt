import android.app.Application
import com.onesignal.OneSignal

class App: Application() {

        private val ONESIGNAL_APP_ID: String = "0a049d28-f3cd-45c0-a2e1-10b484658e1d"

        override fun onCreate() {
            super.onCreate()

            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
            OneSignal.initWithContext(this)
            OneSignal.setAppId(ONESIGNAL_APP_ID)
            OneSignal.promptForPushNotifications();
        }
}