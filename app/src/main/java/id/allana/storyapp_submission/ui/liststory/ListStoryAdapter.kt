package id.allana.storyapp_submission.ui.liststory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.allana.storyapp_submission.data.network.model.response.story.StoryItem
import id.allana.storyapp_submission.databinding.ItemListStoryBinding

class ListStoryAdapter(private val itemClick: (StoryItem) -> Unit): RecyclerView.Adapter<ListStoryAdapter.ListStoryViewHolder>() {

    private val items = ArrayList<StoryItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<StoryItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListStoryViewHolder {
        val binding = ItemListStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListStoryViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: ListStoryViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ListStoryViewHolder(private val binding: ItemListStoryBinding, val itemClick: (StoryItem) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: StoryItem) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.ivStory.load(photoUrl)
                binding.tvUsername.text = name
                binding.tvDescription.text = description
            }
        }
    }
}