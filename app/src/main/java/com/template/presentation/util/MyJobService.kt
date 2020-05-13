package com.template.presentation.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyJobService (appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Result.success()
    }

    companion object {
        private const val TAG = "MyJobService"
    }
}
