package org.d3if0098.kalkulatorzakatemas.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if0098.kalkulatorzakatemas.R
import org.d3if0098.kalkulatorzakatemas.ui.MainActivity

class UpdateWorker (
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams){
    private  val notifID = 44
    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingintent = PendingIntent.getActivity(applicationContext,0,intent,0)
        val builder = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(R.string.notif_title))
            .setContentText(applicationContext.getText(R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingintent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(applicationContext)
            .notify(notifID,builder.build())
        return Result.success()
    }
}