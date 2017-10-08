package com.tuling.util;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.feng.bean.ChatMessage;
import com.example.feng.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by feng on 2017/10/6.
 */

public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mflater;
    private List<ChatMessage> mDatas;
    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        mflater = LayoutInflater.from(context);
        this.mDatas = mDatas;


    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = mDatas.get(position);
        if (chatMessage.getType() == ChatMessage.Type.INCOMING) {
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        ChatMessage chatMessage = mDatas.get(i);
        if (view == null) {

            if (getItemViewType(i) == 0) {
                view = mflater.inflate(R.layout.item_from_msg, viewGroup,
                        false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) view.findViewById(R.id.id_form_msg_date);
                viewHolder.mMsg = (TextView) view.findViewById(R.id.id_from_msg_info);
            } else {

                view = mflater.inflate(R.layout.item_to_msg, viewGroup,
                        false);
                 viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) view.findViewById(R.id.id_to_msg_date);
                viewHolder.mMsg = (TextView) view.findViewById(R.id.id_to_msg_info);
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mDate.setText(df.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        Log.d("derek", "getView: "+chatMessage.getMsg());



        return view;
    }

    private final class ViewHolder {

        TextView mDate;
        TextView mMsg;


    }

}
