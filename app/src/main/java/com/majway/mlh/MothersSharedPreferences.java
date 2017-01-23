package com.majway.mlh;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.Map;


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
            // Instance is intended to hold full url with trailing instance value in uri
            case "instanceKey": return sharedpreferences.getString(sharedprefkey,
                    "Full Address for Appointment Service");
            case "customerIdKey": return sharedpreferences.getString(sharedprefkey,
                    "Customer ID for Appointment Service");
            case "locationIdKey": return sharedpreferences.getString(sharedprefkey,
                    "Location ID for Appointment Service");
            default: return sharedpreferences.getString(sharedprefkey,
                    "Value Missing");
        }
    }

    /** public String getAllSetting() {
        Map<String,?> keys = sharedpreferences.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            return this.getSetting(entry.getValue().toString());
        }
    } */

    // Setter, see switch above for quick reference on keys.
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
