package com.example.tryapp

import MyForegroundService
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.content.ContextCompat

/**
 * Implementation of App Widget functionality.
 */
class FirstWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

@SuppressLint("RemoteViewLayout")
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {


    val serviceIntent = Intent(context, MyForegroundService::class.java)
    ContextCompat.startForegroundService(context, serviceIntent)

    val shortcuttext = R.id.openWebButton

    val remoteViews =RemoteViews(context.packageName,R.layout.first_widget)

    val intent = Intent(Intent.ACTION_VIEW)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.parse("www.google.com")
    /*intent.data = Uri.parse(shortcuttext)*/

    val pendingIntent = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)

    remoteViews.setOnClickPendingIntent(R.id.openWebButton, pendingIntent)

    appWidgetManager.updateAppWidget(appWidgetId,remoteViews)


}