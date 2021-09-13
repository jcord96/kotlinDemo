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


@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private val TAG = SplashScreenActivity::class.qualifiedName
    }

    private val viewModel: SplashScreenViewModel by viewModels()

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.loadUsers().collect {
                when (it) {
                    State.Loading -> Unit // nothing
                    is State.Success -> startMainActivity()
                    is State.Failure -> failureLoadData(it.throwable)
                }
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }

    private fun failureLoadData(throwable: Throwable) {
        Log.e(TAG, throwable.localizedMessage)

        applicationContext.showErrorDialog(
            R.string.ad_message_error_loading_data,
            R.string.ad_continue
        ) { dialog, _ ->
            dialog.dismiss()
            startMainActivity()
        }
    }
}