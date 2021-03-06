package com.example.wjdck.hakerton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import static com.example.wjdck.hakerton.loginActivity.Uid;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listviewItemList = new ArrayList<ListViewItem>();
    private final static int PROG_REC = 1;
    private final static int PROG_LAT = 2;
    private final static int EXPR_REC = 3;
    private final static int EXPR_LAT = 4;

    private int sort = PROG_REC;
    private String category = "전체";

    public ListViewAdapter(){}

    @Override
    public int getCount() {
        int cnt = 0;
        for(int i = 0; i < listviewItemList.size(); i++) {
            ListViewItem item = listviewItemList.get(i);
            if(filter(item)){
                cnt++;
            }
        }
        return cnt;
    }

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent,false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.agenda_title);
        TextView recommendTextView = (TextView) convertView.findViewById(R.id.agenda_num);
        TextView dateTextView = (TextView) convertView.findViewById(R.id.agenda_date);
        TextView endDateTextView = (TextView) convertView.findViewById(R.id.agenda_enddate);

        ListViewItem listViewItem = getItem(pos);

        if(listViewItem != null){
            titleTextView.setText(listViewItem.getTitle());
            recommendTextView.setText(Long.toString(listViewItem.getRecommend()) + " 명");
            dateTextView.setText(mSimpleDateFormat.format(Long.parseLong(listViewItem.getDate())));
            endDateTextView.setText(mSimpleDateFormat.format((Long.parseLong(listViewItem.getDate()))+(2592000000L)));
            if(listViewItem.getClicked().containsKey(Uid)){
                clickedList(convertView);
            }else{
                unClickedList(convertView);
            }
        }

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ListViewItem getItem(int position){
        ListViewItem item;
        int fullSize = listviewItemList.size();
        int itemIndex = 0;
        for(int i = 0; i < fullSize; i++) {
            item = listviewItemList.get(i);
            if (filter(item)) {
                if(itemIndex == position){
                    return item;
                }
                itemIndex++;
            }
        }
        return null;
    }

    public void clickedList(View view){
        RelativeLayout relative = (RelativeLayout) view.findViewById(R.id.clickedFlag);
        relative.setBackgroundResource(R.color.clicked);
    };
    public void  unClickedList(View view){
        RelativeLayout relative = (RelativeLayout) view.findViewById(R.id.clickedFlag);
        relative.setBackgroundResource(R.color.unclicked);
    }

    public void addItem(ListViewItem item){
        listviewItemList.add(item);
    }

    public void listSort(){
        Comparator<ListViewItem> noAsc = null;
        switch(sort) {
            case PROG_REC:
            case EXPR_REC:noAsc = new Comparator<ListViewItem>() {
                @Override
                public int compare(ListViewItem item1, ListViewItem item2) {
                    return (int)(item2.getRecommend() - item1.getRecommend());
                }
            };
                break;
            case PROG_LAT:
            case EXPR_LAT:noAsc = new Comparator<ListViewItem>() {
                @Override
                public int compare(ListViewItem item1, ListViewItem item2) {
                    return (int)(Long.parseLong(item2.getDate()) - Long.parseLong(item1.getDate()));
                }
            };
                break;
        }
        Collections.sort(listviewItemList, noAsc);
    }
    public int findItem(String key) {
        for(int i=0; i<listviewItemList.size(); i++) {
            if(listviewItemList.get(i).getKey().equals(key)){
                return i;
            }
        }
        return -1;
    }
    public void removeItem(int position) {
        listviewItemList.remove(position);
    }

    public void replaceItem(ListViewItem newItem) {
        int index = findItem(newItem.getKey());
        listviewItemList.remove(index);
        listviewItemList.add(index, newItem);
    }

    private boolean filter(ListViewItem item){
        boolean flag = false;
        switch (sort){
            case PROG_REC:
            case PROG_LAT:
                if(Calendar.getInstance().getTimeInMillis() - Long.parseLong(item.getDate()) <= (2592000000L)){
                    if(category.equalsIgnoreCase("전체") || category.equalsIgnoreCase(item.getCategory())) {
                        flag = true;
                    }
                }
                break;
            case EXPR_REC:
            case EXPR_LAT:
                if(Calendar.getInstance().getTimeInMillis() - Long.parseLong(item.getDate()) > (2592000000L)){
                    if(category.equalsIgnoreCase("전체") || category.equalsIgnoreCase(item.getCategory())) {
                        flag = true;
                    }
                }
                break;
        }
        return flag;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
        listSort();
        notifyDataSetChanged();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        notifyDataSetChanged();
    }

}
