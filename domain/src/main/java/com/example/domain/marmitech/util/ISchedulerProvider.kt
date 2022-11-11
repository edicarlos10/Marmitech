package com.example.domain.marmitech.util

import io.reactivex.Scheduler

interface ISchedulerProvider {
    fun mainThread(): Scheduler
    fun backgroundThread(): Scheduler
}