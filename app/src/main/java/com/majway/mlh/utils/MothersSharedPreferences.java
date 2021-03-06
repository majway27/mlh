package com.majway.mlh.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.majway.mlh.rugrat.Child;
import com.google.gson.Gson;

/** Manage data persistence of End User Instance Settings and Children **/
public class MothersSharedPreferences {

    public SharedPreferences sharedpreferences;
    public static final String MLHINSTANCEPREFERENCES = "mlhInstancePreferencesFile";
    public static final String CHILDREN = "Children";
    private final Context ctx;
    // Set Child Test
    List<Child> children;

    public MothersSharedPreferences(Context ctx) {
        super();
        // Hook in passed in application context
        this.ctx = ctx;
        // Init SP or SPs
        sharedpreferences = ctx.getSharedPreferences(MLHINSTANCEPREFERENCES,
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
            case "Children": return sharedpreferences.getString(sharedprefkey,
                    "No Children");
            default: return sharedpreferences.getString(sharedprefkey,
                    "Value Missing");
        }
    }
    
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

    public void wipeChildren() {
        // Wipe Children in stored preferences
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(CHILDREN).commit();
        Toast.makeText(ctx, "Children Cleared!", Toast.LENGTH_LONG).show();
    }

    /** Child Data Management **/
    public void saveChildren(Context context, List<Child> children) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(MLHINSTANCEPREFERENCES,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonChildren = gson.toJson(children);

        editor.putString(CHILDREN, jsonChildren);

        editor.commit();
    }

    public void addChild(Context context, Child child) {
        List<Child> children = getChildren(context);
        if (children == null)
            children = new ArrayList<Child>();
        children.add(child);
        saveChildren(context, children);
    }

    public void removeChild(Context context, Child child) {
        ArrayList<Child> children = getChildren(context);
        if (children != null) {
            children.remove(child);
            saveChildren(context, children);
        }
    }

    public ArrayList<Child> getChildren(Context ctx) {
        Log.d("myTag", "Starting");
        SharedPreferences sharedpreferences;
        List<Child> children;

        sharedpreferences = ctx.getSharedPreferences(MLHINSTANCEPREFERENCES,
                Context.MODE_PRIVATE);

        if (sharedpreferences.getString("Children", "No Children") == "No Children") {
            children = new ArrayList<Child>();
            Log.d("myTag", "Children SP is wiped");
        } else if (sharedpreferences.contains(CHILDREN)) {
            Log.d("myTag", "containsChildren");
            String jsonChildren = sharedpreferences.getString(CHILDREN, null);
            Gson gson = new Gson();
            Child[] childRoster = gson.fromJson(jsonChildren,
                    Child[].class);

            children = Arrays.asList(childRoster);
            children = new ArrayList<Child>(children);
            //return (ArrayList<Child>) children;
        } else
            return null;
        Log.d("myTag", "Returning children ArrayList");
        return (ArrayList<Child>) children;
    }

    /**public void addChildTest() {
        Child child = new Child("3", "Test Child3", "Child");

        List<Child> children = getChildren(ctx);
        if (children == null)
            children = new ArrayList<Child>();
        children.add(child);
        saveChildren(ctx, children);
    }**/

}
