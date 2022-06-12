package ru.edamamlearning.graduationproject.ui

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.DaggerActivity
import ru.edamamlearning.graduationproject.core.NetworkObserver
import ru.edamamlearning.graduationproject.ui.disconnectdialog.DisconnectDialog
import ru.edamamlearning.graduationproject.utils.extensions.color
import ru.edamamlearning.graduationproject.utils.extensions.showSnackMessage
import ru.edamamlearning.graduationproject.utils.message.MessageDialogFragment
import ru.edamamlearning.graduationproject.utils.message.SystemMessage
import ru.edamamlearning.graduationproject.utils.message.SystemMessageNotifier
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerActivity(R.layout.activity_main) {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    @Inject
    lateinit var networkObserver: NetworkObserver

    @Inject
    lateinit var systemMessageNotifier: SystemMessageNotifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        configureNavigation()
        supportFragmentManager.beginTransaction()
        subscribeOnSystemMessages()
        initActionBar()
    }

    private fun initActionBar() {
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            networkObserver.networkIsAvailable()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest { isConnection ->
                    if (!isConnection) {
                        DisconnectDialog().show(
                            supportFragmentManager,
                            DisconnectDialog::class.java.simpleName
                        )
                    }
                }
        }
    }

    private fun configureNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment -> showBottomNav()
                R.id.favoritesFragment -> showBottomNav()
                R.id.dairyFragment -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun subscribeOnSystemMessages() {
        lifecycleScope.launchWhenCreated {
            systemMessageNotifier.notifier
                .onEach { msg ->
                    when (msg) {
                        is SystemMessage.Alert -> showAlertMessage(msg.text)
                        is SystemMessage.Toast -> showToastMessage(msg.text)
                        is SystemMessage.Snack -> showSnackBar(
                            message = msg.text,
                            color = color(msg.colorRes),
                            duration = msg.snackBarDuration,
                            dismissSnackBar = msg.dismissSnackBar,
                            scope = lifecycleScope
                        )
                    }
                }
                .catch { Timber.e(it) }
                .collect()
        }
    }

    private fun showAlertMessage(message: String) {
        MessageDialogFragment.create(message = message).show(supportFragmentManager, null)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showSnackBar(
        message: String,
        @ColorInt color: Int,
        duration: Int,
        dismissSnackBar: Flow<Unit>? = null,
        scope: CoroutineScope
    ) {
        findViewById<FrameLayout>(R.id.nav_host_fragment).showSnackMessage(
            message,
            color,
            duration,
            dismissSnackBar,
            scope
        )
    }
}