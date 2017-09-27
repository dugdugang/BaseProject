package com.test.baseproject.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.test.baseproject.R;

import java.util.ArrayList;

/**
 * Author: duguang
 * Date 2017/9/26 0026.
 */

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView mRecycle;
    private ArrayList<String> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_common);
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add(i + "");
        }
        mRecycle = (RecyclerView) findViewById(R.id.recycle);
        mRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycle.setAdapter(new CommonAdapter<String>(this, R.layout.item_common_adapter, mDatas) {
            @Override
            public void convert(final CommonViewHolder holder, String s) {
//                TextView view = holder.getView(R.id.item_com_tv);
//                view.setText(s);
                holder.setText(R.id.item_com_tv, s);
                holder.setOnClickListener(R.id.item_com_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, ""+mDatas.get(holder.getmPosition()), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
