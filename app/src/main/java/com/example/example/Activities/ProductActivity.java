package com.example.example.Activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;

import com.example.example.Objects.Product;
import com.example.example.R;

import cn.lankton.flowlayout.FlowLayout;

public class ProductActivity extends AppCompatActivity {

    FlowLayout flowlayoutAdaptTag;
    FlowLayout flowLayoutIngredientTag;

    TextView titleInformation;
    TextView informationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        titleInformation = findViewById(R.id.textViewProductName);
        informationView = findViewById(R.id.flowlayoutInformationTag);

        Product p = (Product)getIntent().getSerializableExtra(MainActivity.OBJ); //We capture the intention and obtain the object we sent from MainActivity
        titleInformation.setText(p.getName().toUpperCase()); //We update the fields

        flowlayoutAdaptTag= findViewById(R.id.flowlayoutAdaptTag);
        flowlayoutAdaptTag.relayoutToAlign();

        for(String str: p.getAdaptedFor()){
           addAdaptTag(flowlayoutAdaptTag,str);
        }

        flowLayoutIngredientTag = findViewById(R.id.flowlayoutIngredientTag);
        flowLayoutIngredientTag.relayoutToCompress();

        for(String str: p.getIngredients()){
            addAdaptTag(flowLayoutIngredientTag,str);
        }
    }

    protected void addAdaptTag(FlowLayout flowLayout,String str){
        int ranHeight = dip2px(this, 30);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
        lp.setMargins(dip2px(this, 10), 0, dip2px(this, 10), 0);
        TextView tv = new TextView(this);
        tv.setPadding(dip2px(this, 15), 0, dip2px(this, 15), 0);
        tv.setTextColor(Color.parseColor("#059011"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        tv.setText(str);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setLines(1);
        tv.setBackgroundResource(R.drawable.bg_tag);
        flowLayout.addView(tv, lp);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
