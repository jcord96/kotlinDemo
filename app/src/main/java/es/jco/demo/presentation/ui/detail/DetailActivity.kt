package es.jco.demo.presentation.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import es.jco.demo.R
import es.jco.demo.databinding.ActivityDetailBinding
import es.jco.demo.presentation.common.KEY_USER
import es.jco.demo.presentation.common.binding
import es.jco.domain.Address
import es.jco.domain.Company
import es.jco.domain.Geo
import es.jco.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Detail activity
 *
 * @constructor Create empty Detail activity
 */
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        private val TAG = DetailActivity::class.qualifiedName
    }

    /** View model */
    private val viewModel: DetailViewModel by viewModels()

    /** View binding */
    private lateinit var binding: ActivityDetailBinding

    /**
     * On create
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Annotation -> To save another layout for create or edit users, both options are implemented here.
        //               Depending on the selected menu option, the fields are enabled or disabled to allow new data to be entered.
        //               Currently, there are no validations

        lifecycleScope.launch(Dispatchers.Main) {
            // Binding inputs values to user
            bindingUser()

            // Binding data to inputs
            observeUser()
            observeInputEnable()
            observeLoading()

            // Request to load the selected user
            intent.extras?.let { viewModel.loadUser(it.getLong(KEY_USER)) }
        }
    }

    /**
     * On destroy
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Lifecycle - Destroyed")
    }

    /**
     * On create options menu
     *
     * @param menu Menu
     * @return
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)

        lifecycleScope.launch(Dispatchers.Main) {
            // Depending the inputs status, it's visible the edit or save icon
            viewModel.isInputEnabled.onEach {
                menu?.findItem(R.id.action_detail_edit)?.isVisible = !it
                menu?.findItem(R.id.action_detail_save)?.isVisible = it
            }.launchIn(this)
        }

        return true
    }

    /**
     * On options item selected
     *
     * @param item Selected menu item
     */
    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_detail_edit -> { viewModel.enableInput(); true }
        R.id.action_detail_save -> { viewModel.saveUser(); true }
        else -> super.onOptionsItemSelected(item)
    }

    /**
     * On support navigate up
     *
     * @return
     */
    override fun onSupportNavigateUp(): Boolean {
        Log.i(TAG, "Lifecycle - BackPressed")

        // Force the return to main activity
        onBackPressed()
        return true
    }

    /**
     * Function to add listeners to inputs
     */
    private fun bindingUser() {
        Log.i(TAG, " - Binding")
        with(binding) {
            usernameInput.binding(User::username, viewModel.userEditable)
            nameInput.binding(User::name, viewModel.userEditable, getString(R.string.error_input_required))
            emailInput.binding(User::email, viewModel.userEditable)
            phoneInput.binding(User::phone, viewModel.userEditable)
            websiteInput.binding(User::website, viewModel.userEditable)

            addressStreetInput.binding(Address::street, viewModel.userEditable.address)
            addressSuiteInput.binding(Address::suite, viewModel.userEditable.address)
            addressCityInput.binding(Address::city, viewModel.userEditable.address)
            addressZipcodeInput.binding(Address::zipcode, viewModel.userEditable.address)
            addressGeoLatInput.binding(Geo::lat, viewModel.userEditable.address?.geo)
            addressGeoLngInput.binding(Geo::lng, viewModel.userEditable.address?.geo)

            companyNameInput.binding(Company::name, viewModel.userEditable.company)
            companyCatchPhraseInput.binding(Company::catchPhrase, viewModel.userEditable.company)
            companyBsInput.binding(Company::bs, viewModel.userEditable.company)
        }
    }

    /**
     * Function to observe the user stateFlow
     *
     */
    private fun CoroutineScope.observeUser() {
        viewModel.user.onEach {
            // Setting values when user data is updated
            Log.d(TAG, "Loading user - User: $it")
            viewModel.loading()

            if (it.id == null) {
                Log.i(TAG, "Loading user - Adding")
                viewModel.enableInput()
                binding.nameInput.error = getString(R.string.error_input_required)
            } else {
                with(binding) {
                    Log.i(TAG, "Loading user - Setting")
                    usernameInput.setText(it.username ?: "")
                    nameInput.setText(it.name ?: "")
                    emailInput.setText(it.email ?: "")
                    phoneInput.setText(it.phone ?: "")
                    websiteInput.setText(it.website ?: "")

                    addressStreetInput.setText(it.address?.street ?: "")
                    addressSuiteInput.setText(it.address?.suite ?: "")
                    addressCityInput.setText(it.address?.city ?: "")
                    addressZipcodeInput.setText(it.address?.zipcode ?: "")
                    addressGeoLatInput.setText(it.address?.geo?.lat.toString())
                    addressGeoLngInput.setText(it.address?.geo?.lng.toString())

                    companyNameInput.setText(it.company?.name ?: "")
                    companyCatchPhraseInput.setText(it.company?.catchPhrase ?: "")
                    companyBsInput.setText(it.company?.bs ?: "")
                }
            }

            viewModel.finishLoading()
        }.launchIn(this)
    }

    /**
     * Function to observe the input enable stateFlow
     *
     */
    private fun CoroutineScope.observeInputEnable() {
        viewModel.isInputEnabled.onEach {
            // Setting the status of the inputs
            if (binding.username.isEnabled != it) {
                with(binding) {
                    username.isEnabled = it
                    name.isEnabled = it
                    email.isEnabled = it
                    phone.isEnabled = it
                    website.isEnabled = it

                    addressStreet.isEnabled = it
                    addressSuite.isEnabled = it
                    addressCity.isEnabled = it
                    addressZipcode.isEnabled = it
                    addressGeoLat.isEnabled = it
                    addressGeoLng.isEnabled = it

                    companyName.isEnabled = it
                    companyCatchPhrase.isEnabled = it
                    companyBs.isEnabled = it
                }
            }
        }.launchIn(this)
    }

    /**
     * Function to observe the loading stateFlow
     *
     */
    private fun CoroutineScope.observeLoading() {
        viewModel.loading.onEach {
            // Setting the status of the progress bar
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }.launchIn(this)
    }
}