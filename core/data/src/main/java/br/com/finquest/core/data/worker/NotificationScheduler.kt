package br.com.finquest.core.data.worker

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters
import java.util.concurrent.TimeUnit

fun scheduleGoalReminder(context: Context, name: String, deadline: LocalDate?) {
    val workManager = WorkManager.getInstance(context)

    if (deadline != null) {
        val delay = getTimeUntilDeadline(deadline)
        Log.d("WorkManager", "Agendado notificação para a meta '$name' em '$deadline' (delay: $delay ms)")

        val workRequest = OneTimeWorkRequestBuilder<GoalReminderWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("goalName" to name))
            .build()

        workManager.enqueueUniqueWork("goal_reminder_$name", ExistingWorkPolicy.REPLACE, workRequest)
    } else {
        Log.d("WorkManager", "Agendado notificação semanal para as metas")

        val workRequest = PeriodicWorkRequestBuilder<GoalReminderWorker>(7, TimeUnit.DAYS)
            .setInitialDelay(getInitialDelayForWeeklyReminder(), TimeUnit.MILLISECONDS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "weekly_goal_reminder",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}

private fun getTimeUntilDeadline(deadline: LocalDate): Long {
    val now = LocalDateTime.now()
    val targetTime = deadline.atTime(11, 20)

    return if (now.isBefore(targetTime)) {
        Duration.between(now, targetTime).toMillis()
    } else {
        0L
    }
}

private fun getInitialDelayForWeeklyReminder(): Long {
    val now = LocalDateTime.now()
    val nextMonday = now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).withHour(12).withMinute(0).withSecond(0)

    return Duration.between(now, nextMonday).toMillis()
}