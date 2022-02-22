
package com.example.digital_journal

import android.app.Application
import com.example.digital_journal.data.PostRoomDatabase


class DigitalJournalApplication : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: PostRoomDatabase by lazy { PostRoomDatabase.getDatabase(this) }
}
