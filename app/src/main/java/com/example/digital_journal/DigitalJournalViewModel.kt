

package com.example.digital_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.digital_journal.data.Post
import com.example.digital_journal.data.PostDao
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the Inventory repository and an up-to-date list of all items.
 *
 */
class DigitalJournalViewModel(private val postDao: PostDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Post>> = postDao.getItems().asLiveData()


    /**
     * Updates an existing Item in the database.
     */
    fun updateItem(
        itemId: Int,
        itemPost: String,
        itemDate: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemPost, itemDate)
        updateItem(updatedItem)
    }


    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateItem(post: Post) {
        viewModelScope.launch {
            postDao.update(post)
        }
    }

    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(itemPost: String) {
        val newPost = getNewItemEntry(itemPost)
        insertItem(newPost)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(post: Post) {
        viewModelScope.launch {
            postDao.insert(post)
        }
    }

    /**
     * Launching a new coroutine to delete an item in a non-blocking way
     */
    fun deleteItem(post: Post) {
        viewModelScope.launch {
            postDao.delete(post)
        }
    }

    /**
     * Retrieve an item from the repository.
     */
    fun retrieveItem(id: Int): LiveData<Post> {
        return postDao.getItem(id).asLiveData()
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(itemPost: String): Boolean {
        if (itemPost.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Post] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(itemPost: String): Post {
        return Post(
            itemPost = itemPost
        )
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [Post] entity class with the item info updated by the user.
     */
    private fun getUpdatedItemEntry(
        itemId: Int,
        itemPost: String,
        itemDate: String,
    ): Post {
        return Post(
            id = itemId,
            itemPost = itemPost,
            itemDate = itemDate,
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class DigitalJournalViewModelFactory(private val postDao: PostDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DigitalJournalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DigitalJournalViewModel(postDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

