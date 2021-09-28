package es.jco.demo.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import es.jco.demo.databinding.ActivityMainBinding
import es.jco.demo.presentation.common.KEY_USER
import es.jco.demo.presentation.ui.detail.DetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: MainViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivityMainBinding

    /** Adapter for recycler view initialized by lazy */
    private val adapter by lazy {
        RecyclerViewAdapter {
            Log.i(TAG, "Selected user: ${it.name}")
            startActivity(
                // Annotation -> To secure the identifier, it would be advisable to encode the value before passing it to the other activity
                Intent(this, DetailActivity::class.java).putExtra(KEY_USER, it.id!!)
            )
        }
    }

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bound onClickListener to button for add new user
        binding.buttonAddUser.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        // Bound adapter to recycler view of the main layout
        binding.recycler.adapter = adapter

        // Attached onSwipe gesture to card views to remove users
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // When card view is swiped, it's invoked to viewmodel to remove the user
                val vh = viewHolder as RecyclerViewAdapter.ViewHolder
                vh.remove(viewModel::deleteUser)

            }
        }).attachToRecyclerView(binding.recycler)

        // Binding stateFlows to views to automatically update values
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.loading.onEach {
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            viewModel.users.onEach { adapter.users = it }.launchIn(this)
        }

        // Request to get all users from database
        viewModel.getUsers()
    }
}