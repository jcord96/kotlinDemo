package es.jco.demo.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.jco.demo.databinding.ActivityDetailBinding
import es.jco.demo.databinding.ActivityMainBinding
import es.jco.demo.presentation.ui.main.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}