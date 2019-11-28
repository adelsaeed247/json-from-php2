package com.example.json_from_php;

import android.widget.BaseAdapter;
import android.content.Context;
import java.util.List;
import android.app.Activity;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapter extends BaseAdapter {

    Context context;
    List<users> valueList;

    public ListAdapter(List<users> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.layout_items, null);

            viewItem.TextViewName = (TextView)convertView.findViewById(R.id.tx_name);
            viewItem.TextViewPhone = (TextView)convertView.findViewById(R.id.tx_phone);
            viewItem.TextViewEmail = (TextView)convertView.findViewById(R.id.tx_email);



            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewName.setText(valueList.get(position).TextViewName);
        viewItem.TextViewPhone.setText(valueList.get(position).TextViewPhone);
        viewItem.TextViewEmail.setText(valueList.get(position).TextViewEmail);


        return convertView;
    }
}

class ViewItem
{
    TextView TextViewName;
    TextView TextViewPhone;
    TextView TextViewEmail;



}

