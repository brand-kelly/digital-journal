

package com.example.digital_journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.digital_journal.data.Post
import com.example.digital_journal.data.PostDao
import kotlinx.coroutines.launch


class DigitalJournalViewModel(private val postDao: PostDao) : ViewModel() {

    val allItems: LiveData<List<Post>> = postDao.getItems().asLiveData()



    fun updateItem(
        itemId: Int,
        itemPost: String,
        itemDate: String
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemPost, itemDate)
        updateItem(updatedItem)
    }



    private fun updateItem(post: Post) {
        viewModelScope.launch {
            postDao.update(post)
        }
    }


    fun addNewItem(itemPost: String) {
        val newPost = getNewItemEntry(itemPost)
        insertItem(newPost)
    }


    private fun insertItem(post: Post) {
        viewModelScope.launch {
            postDao.insert(post)
        }
    }


    fun deleteItem(post: Post) {
        viewModelScope.launch {
            postDao.delete(post)
        }
    }


    fun retrieveItem(id: Int): LiveData<Post> {
        return postDao.getItem(id).asLiveData()
    }


    fun isEntryValid(itemPost: String): Boolean {
        if (itemPost.isBlank()) {
            return false
        }
        return true
    }


    private fun getNewItemEntry(itemPost: String): Post {
        return Post(
            itemPost = itemPost
        )
    }


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


class DigitalJournalViewModelFactory(private val postDao: PostDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DigitalJournalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DigitalJournalViewModel(postDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

