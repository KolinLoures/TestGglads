package com.example.kolin.testgglads.presentation.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kolin on 26.01.2017.
 */

public class SpinnerCategoryAdapter extends ArrayAdapter<Category> {

    private List<Category> categoryList = new ArrayList<>();

    public SpinnerCategoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class ViewHolder {
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.item_spinner, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_spinner_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(getItem(position).getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public void addAll(Collection<? extends Category> collection) {
        if (collection != null) {
            categoryList.clear();
            categoryList.addAll(collection);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        categoryList.clear();
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    public List<Category> getCategoryList(){
        return categoryList;
    }
}
