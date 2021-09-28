package es.jco.demo.presentation.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.jco.demo.R
import es.jco.demo.databinding.ActivitySplashScreenBinding
import es.jco.demo.presentation.common.showErrorDialog
import es.jco.demo.presentation.ui.State
import es.jco.demo.presentation.ui.main.MainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

/**
 * Splash screen activity
 *
 * @constructor Create empty Splash screen activity
 */
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private val TAG = SplashScreenActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: SplashScreenViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivitySplashScreenBinding

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Annotation -> To control data access, an authentication service should be launched before reaching this point

        // To speed up the data load, when the application is initialized,
        // the query to the API is launched to obtain the users in the background
        lifecycleScope.launch {

            // The result of the query is checked to launch the main screen or display a dialog warning of the error
            viewModel.loadUsers().collect {
                when (it) {
                    State.OnLoading -> Unit // nothing
                    is State.OnSuccess -> startMainActivity()
                    is State.OnFailure -> failureLoadData(it.throwable)
                }
            }
        }
    }

    /**
     * Private function to start the main activity
     */
    private fun startMainActivity() {
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }

    /**
     * Private function to handle data load failures
     *
     * @param throwable failure received
     */
    private fun failureLoadData(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) getString(R.string.error_unexpected) else throwable.localizedMessage
        )

        applicationContext.showErrorDialog(
            R.string.ad_message_error_loading_data,
            R.string.ad_continue
        ) { dialog, _ ->
            dialog.dismiss()
            startMainActivity()
        }
    }
}