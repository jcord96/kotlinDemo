package es.jco.demo.presentation.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.jco.demo.R
import es.jco.demo.databinding.ViewMediaItemMainBinding
import es.jco.demo.presentation.common.inflate
import es.jco.domain.User
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.reflect.KFunction1

/**
 * Recycler view adapter
 *
 * @property onClick
 * @constructor
 *
 * @param users
 */
class RecyclerViewAdapter(users: List<User> = emptyList(), private val onClick: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    companion object {
        private val TAG = RecyclerViewAdapter::class.qualifiedName
    }

    /** Users list */
    var users by Delegates.observable(users) { _, _, _ ->
        Log.i(TAG, "RecyclerView - Users updated")
        // TODO: Make more efficient
        notifyDataSetChanged()
    }

    /**
     * On create view holder
     *
     * @param parent
     * @param viewType
     * @return custom view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item_main)
        return ViewHolder(view)
    }

    /**
     * On bind view holder
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], onClick)
    }

    /**
     * Get item count
     *
     * @return list size
     */
    override fun getItemCount(): Int = users.size

    /**
     * Inner Custom View holder class
     *
     * @constructor
     *
     * @param view
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /** View binding */
        private val binding = ViewMediaItemMainBinding.bind(view)
        /** Variable to identified the view holder */
        private var user: User? = null

        /**
         * Function to bind the user data with the view
         *
         * @param user
         * @param onClick
         * @receiver
         */
        fun bind(user: User, onClick: (User) -> Unit) {
            Log.i(TAG, "ViewHolder - Binding users")

            // Saving user data internally
            this.user = user

            with(binding) {
                // Get the first letter of list item
                val letters = user.name?.split(" ").let { name ->
                    var aux = ""
                    name?.forEach { aux = aux.plus(it[0]) }
                    aux
                }

                // Generate random color
                val rnd = Random(if (user.id != null) user.id!! else Random.nextLong())
                val color: Int =
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

                // Binding data to fields
                mediaImage.apply {
                    text = letters
                    (background as GradientDrawable).color = ColorStateList.valueOf(color)
                }

                mediaUsername.text = user.name
                mediaCompany.text = user.company?.name

                cardView.setOnClickListener { onClick(user) }
            }
        }

        /** Reflection function to delete user while keeping sensitive data hidden */
        fun remove(deleteUser: KFunction1<Long, Unit>) {
            user?.id?.let { deleteUser.call(it) }
        }
    }
}