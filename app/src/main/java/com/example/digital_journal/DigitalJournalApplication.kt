
package com.example.digital_journal

import android.app.Application
import com.example.digital_journal.data.PostRoomDatabase


class DigitalJournalApplication : Application() {

    val database: PostRoomDatabase by lazy { PostRoomDatabase.getDatabase(this) }
}
