package com.majway.mlh.rugrat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.majway.mlh.R;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.majway.mlh.utils.MothersSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class ChildManagerActivity extends Activity {

    Button b2;
    List<Child> children;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_manager);
        populateChildrenList();

        b2=(Button)findViewById(R.id.btnWipeChildren);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wipeChildren();
            }
        });

    }

    @Override
    public void onResume()
    {  // Refresh in case Children were edited, etc.
        super.onResume();
        populateChildrenList();
    }

    public void createChild(View view) {
        Intent intent = new Intent(this, CreateChildActivity.class);
        startActivity(intent);
    }

    private void populateChildrenList() {
        MothersSharedPreferences msp = new MothersSharedPreferences(ChildManagerActivity.this);
        // Construct the data source
        ArrayList<Child> arrayOfChildren = msp.getChildren(ChildManagerActivity.this);
        // Create the adapter to convert the array to views
        // Check to see if Children are wiped.
        if (arrayOfChildren != null){
            Log.d("myTag", "populateChildList-arrayOfChildren not null");
            CustomChildAdapter adapter = new CustomChildAdapter(this, arrayOfChildren);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.lvChildren);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    TextView txtview = ((TextView) view.findViewById(R.id.tvName));
                    String product = txtview.getText().toString();
                    Toast.makeText(getBaseContext(), product, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Log.d("myTag", "populateChildList-arrayOfChildren was null");
            arrayOfChildren = new ArrayList<Child>();
            CustomChildAdapter adapter = new CustomChildAdapter(this, arrayOfChildren);
            // Attach the adapter to a ListView
            ListView listView = (ListView) findViewById(R.id.lvChildren);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    TextView txtview = ((TextView) view.findViewById(R.id.tvName));
                    String product = txtview.getText().toString();
                    Toast.makeText(getBaseContext(), product, Toast.LENGTH_LONG).show();
                }
            });
        }
        // Attach the adapter to a ListView
        //ListView listView = (ListView) findViewById(R.id.lvChildren);
        //listView.setAdapter(adapter);
    }

    private void wipeChildren() {
        MothersSharedPreferences msp = new MothersSharedPreferences(ChildManagerActivity.this);
        msp.wipeChildren();
        populateChildrenList();
    }

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) {
        //    case R.id.menu_favorites:
        //        setFragmentTitle(R.string.favorites);
        //        favListFragment = new FavoriteListFragment();
        //        switchContent(favListFragment, FavoriteListFragment.ARG_ITEM_ID);
        //
        //        return true;
        //}
    //    return super.onOptionsItemSelected(item);
    //}

    public void switchContent(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment, tag);
            transaction.commit();
            contentFragment = fragment;
    }

    protected void setFragmentTitle(int resourseId) {
        setTitle(resourseId);
        getActionBar().setTitle(resourseId);

    }

    protected void setFragmentTitle(int resourseId) {
        //String resourseId = "Child";
        setTitle(resourseId);
        getActionBar().setTitle(resourseId);

    }**/
}
