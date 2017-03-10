package com.quadriyanney.aacc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quadriy on 3/8/17.
 */

public class DevAdapter extends ArrayAdapter{

    private List<DeveloperInfo> list = new ArrayList<DeveloperInfo>();

    public DevAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(DeveloperInfo object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View list;
        list = convertView;
        DevInfoHolder devInfoHolder;

        if (list == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = layoutInflater.inflate(R.layout.list_layout, parent, false);

            devInfoHolder = new DevInfoHolder();
            devInfoHolder.username = (TextView) list.findViewById(R.id.name);
            devInfoHolder.user_photo = (ImageView) list.findViewById(R.id.icon);
            list.setTag(devInfoHolder);

        }
        else {
            devInfoHolder = (DevInfoHolder) list.getTag();
        }

        DeveloperInfo developerInfo = (DeveloperInfo) this.getItem(position);
        assert developerInfo != null;
        devInfoHolder.username.setText(developerInfo.getUsername());
        Glide.with(getContext()).load(developerInfo.getUser_photo()).placeholder(R.drawable.ic_person_black_24dp).into(devInfoHolder.user_photo);

        return list;
    }

    private static class DevInfoHolder{
        TextView username;
        ImageView user_photo;
    }
}