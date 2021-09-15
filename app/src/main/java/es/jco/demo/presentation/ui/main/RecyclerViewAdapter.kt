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

class RecyclerViewAdapter(users: List<User> = emptyList(), val onClick: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    companion object {
        private val TAG = RecyclerViewAdapter::class.qualifiedName
    }

    var users by Delegates.observable(users) { _, _, _ ->
        Log.i(TAG, "RecyclerView - Users updated")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_media_item_main)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener { onClick(users[position]) }
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewMediaItemMainBinding.bind(view)

        fun bind(item: User) {
            Log.i(TAG, "ViewHolder - Binding users")

            with(binding) {
                //Get the first letter of list item
                val letters = item.name?.split(" ").let { name ->
                    var aux = ""
                    name?.forEach { aux = aux.plus(it[0]) }
                    aux
                }

                // Generate random color
                val rnd = Random(if (item.id != null) item.id!! else Random.nextLong())
                val color: Int =
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

                // Binding data to fields
                mediaImage.apply {
                    text = letters
                    (background as GradientDrawable).color = ColorStateList.valueOf(color)
                }

                mediaUsername.text = item.name
                mediaCompany.text = item.company?.name
            }
        }
    }
}