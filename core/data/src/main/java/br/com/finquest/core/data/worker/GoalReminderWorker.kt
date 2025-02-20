package br.com.finquest.core.data.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.finquest.core.ui.R

class GoalReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("GoalReminderWorker", "doWork: Worker executado!")

        val goalName = inputData.getString("goalName") ?: "sua meta"
        sendNotification(goalName)

        return Result.success()
    }

    private fun sendNotification(name: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Permission", "Permissão de notificação não concedida!")
            return
        }

        Log.d("GoalReminderWorker", "sendNotification: sendNotification() chamado")
        val channelId = "goal_reminder_channel"
        val notificationId = 1

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Lembretes de Metas",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notificações para lembrar das metas"
        }
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_savings)
            .setContentTitle("Lembrete de Meta")
            .setContentText("Hoje é o último dia para alcançar a meta: $name!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}