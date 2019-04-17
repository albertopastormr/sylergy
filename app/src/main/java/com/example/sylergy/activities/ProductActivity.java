package com.example.sylergy.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;

import com.example.sylergy.fragments.BarcodeProductFragment;
import com.example.sylergy.logs.LogException;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Product;
import com.example.sylergy.R;
import com.squareup.picasso.Picasso;

import cn.lankton.flowlayout.FlowLayout;

public class ProductActivity extends AppCompatActivity implements UpdateActivity {

    FlowLayout flowlayoutAdaptTag;
    FlowLayout flowLayoutIngredientTag;

    TextView titleInformation;
    TextView informationView;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        titleInformation = findViewById(R.id.textViewProductName);
        informationView = findViewById(R.id.flowlayoutInformationTag);

        imageView = findViewById(R.id.imageViewProductImage);

        Product p = (Product)getIntent().getSerializableExtra(BarcodeProductFragment.OBJ); //We capture the intention and obtain the object we sent from BarcodeProductFragment

        titleInformation.setText(p.getName().toUpperCase()); //We update the fields
        setCustomImage(p.getImage()); //We update the image, if it exists

        /*flowlayoutAdaptTag = findViewById(R.id.flowlayoutAdaptTag);
        flowlayoutAdaptTag.relayoutToAlign();

        for(String str: p.getNutriments()){
            addAdaptTag(flowlayoutAdaptTag,str);
        }*/

        flowLayoutIngredientTag = findViewById(R.id.flowlayoutIngredientTag);
        flowLayoutIngredientTag.relayoutToCompress();

        //This belongs to another user history
        for(String str: p.getIngredients()) {
            addAdaptTag(flowLayoutIngredientTag, str);
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

    public void setCustomImage(String image) {
        if(!image.equals("")) {
            try {
               Picasso.with(this).load(image).into(imageView);
            } catch (Exception e) {
                LogsView advise = new LogsView(Logs.IMAGE_ERROR);
                imageView.setImageResource(R.drawable.logo_v3);
                throw new LogException(Logs.IMAGE_ERROR, ProductActivity.this);
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void updateWithCommandResult(com.example.sylergy.objects.Context context) { /* DO NOTHING */ }
}