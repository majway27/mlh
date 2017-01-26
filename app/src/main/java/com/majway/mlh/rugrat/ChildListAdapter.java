package com.majway.mlh.rugrat;

import java.util.List;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.majway.mlh.R;
import com.majway.mlh.utils.MothersSharedPreferences;


public class ChildListAdapter extends ArrayAdapter {

    private Context context;
    List<Child> children;
    MothersSharedPreferences sharedPreference;

    public ChildListAdapter(Context context, List<Child> children) {
        super(context, R.layout.child_list_item, children);
        this.context = context;
        this.children = children;
        sharedPreference = new MothersSharedPreferences(context);
    }

    private class ViewHolder {
        TextView childNameTxt;
        TextView childTypeTxt;
        TextView childIdTxt;
        ImageView favoriteImg;
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Child getItem(int position) {
        return children.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_list_item, null);
            holder = new ViewHolder();
            holder.childNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_name);
            holder.childTypeTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_desc);
            holder.childIdTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_price);
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.imgbtn_favorite);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Child child = (Child) getItem(position);
        holder.childNameTxt.setText(child.getName());
        holder.childTypeTxt.setText(child.getType());
        holder.childIdTxt.setText(child.getId() + "");

		/*If a child exists in shared preferences then set heart_red drawable
		 * and set a tag*/
        if (checkFavoriteItem(child)) {
            holder.favoriteImg.setImageResource(R.drawable.heart_red);
            holder.favoriteImg.setTag("red");
        } else {
            holder.favoriteImg.setImageResource(R.drawable.heart_grey);
            holder.favoriteImg.setTag("grey");
        }

        return convertView;
    }

    /*Checks whether a particular child exists in MothersSharedPreferences*/
    public boolean checkFavoriteItem(Child checkChild) {
        boolean check = false;
        List<Child> favorites = sharedPreference.getChildren(context);
        if (favorites != null) {
            for (Child child : favorites) {
                if (child.equals(checkChild)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    //@Override
    public void add(Child child) {
        super.add(child);
        children.add(child);
        notifyDataSetChanged();
    }

    //@Override
    public void remove(Child child) {
        super.remove(child);
        children.remove(child);
        notifyDataSetChanged();
    }
}
