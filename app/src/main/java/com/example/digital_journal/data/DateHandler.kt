package com.example.digital_journal.data

import android.app.Application
import java.time.LocalDate

class DateHandler: Application() {
    companion object {
        var currentDate = LocalDate.now()
    }

    override fun onCreate() {
        super.onCreate()
    }
}