package com.agarwal.googleplaces;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetworkCheck {

    public static boolean isConnected(final Context context) {

        if (isOnline(context)) {
            return true;
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Please enable WiFi or Mobile Data to continue");
            builder1.setCancelable(false);
            builder1.setTitle("No Internet Connection");
            builder1.setIcon(R.drawable.ic_no_signal);

            builder1.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    /*Activity activity = (Activity) context;
                    activity.finish();*/
                    context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                }
            });

            builder1.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isConnected(context);
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        return false;
    }

    private static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean b = cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
        return b;
    }
}
