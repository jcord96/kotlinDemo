package es.jco.demo.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.jco.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy {
        RecyclerViewAdapter {
            Toast.makeText(this, "User selected:  ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "Lifecycle - OnCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddUser.setOnClickListener {
            Toast.makeText(this, "Adding user", Toast.LENGTH_SHORT).show()
        }

        binding.recycler.adapter = adapter

        // Coroutine for requesting to get users and collecting result
        lifecycleScope.launch {
            viewModel.loading.onEach { binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE }.launchIn(this)
            viewModel.users.onEach { adapter.users = it }.launchIn(this)

            viewModel.getUsers()
        }
    }
}