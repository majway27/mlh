package com.majway.mlh;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;


public class MothersSharedPreferences {

    public SharedPreferences sharedpreferences;
    public static final String mlhInstancePreferences = "mlhInstancePreferencesFile";
    private final Context ctx;

    public MothersSharedPreferences(Context ctx) {
        super();
        // Hook in passed in application context
        this.ctx = ctx;
        // Init SP or SPs
        sharedpreferences = ctx.getSharedPreferences(mlhInstancePreferences,
                Context.MODE_PRIVATE);
    }

    // Getter
    public String getSetting(String sharedprefkey) {
        switch (sharedprefkey) {
            case "friendlyNameKey": return sharedpreferences.getString(sharedprefkey,
                    "Your Preferred Name");
            case "userNameKey": return sharedpreferences.getString(sharedprefkey,
                    "Login User Name for Appointment Service");
            case "passwordKey": return sharedpreferences.getString(sharedprefkey,
                    "Login Password for Appointment Service");
            default: return sharedpreferences.getString(sharedprefkey,
                    "Value Missing");
        }
    }

    // Setter
    public void setSetting(String sharedprefkey, String sharedprefvalue) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(sharedprefkey, sharedprefvalue);
        editor.commit();
        Toast.makeText(ctx, "Settings Set!", Toast.LENGTH_LONG).show();
    }

    public void clearSetting() {
        // Wipe all stored preferences
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear().commit();
        Toast.makeText(ctx, "Settings Cleared!", Toast.LENGTH_LONG).show();
    }

}
