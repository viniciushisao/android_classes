package com.hisao.adpter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by viniciushisao
 */
public class NumberAdapter extends BaseAdapter{

    private ArrayList<Integer> numbers;
    private Activity activity;

    public NumberAdapter(ArrayList<Integer> numbers, Activity activity){
        this.numbers = numbers;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int i) {
        return numbers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.number_item, viewGroup, false);

        TextView tvNumber = (TextView) row.findViewById(R.id.tv_number);
        TextView tvIsPair = (TextView) row.findViewById(R.id.tv_ispair);

        int myNumber = this.numbers.get(i);

        tvNumber.setText(Integer.toHexString(myNumber));
        if ((myNumber%2) == 0){
            tvIsPair.setText("Is pair");
        }else{
            tvIsPair.setText("Is even");
        }

        return row;
    }
}
