package com.example.sylergy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.sylergy.logs.LogException;
import com.example.sylergy.presenter.Presenter;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.R;

import java.util.ArrayList;
import java.util.List;

public class BarcodeProductFragment extends Fragment implements UpdateActivity{
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    Button btnSearch;
    EditText numberCodeText;
    ProgressDialog draw;
    List<Product> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_barcode, container, false);

        btnSearch = view.findViewById(R.id.btnSearch);
        numberCodeText = view.findViewById(R.id.barcodeText);
        draw = new ProgressDialog(getActivity());
        draw.setMessage("Searching...");
        draw.setCancelable(false);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberCode = numberCodeText.getText().toString();
                if(numberCode.equals("")){
                    LogsView advise = new LogsView(Logs.NO_BARCODE);
                    advise.showInfo(getActivity());
                }
                else{
                    draw.show();
                    Presenter.getInstance()
                            .action(new Context(Events.SEARCH_PRODUCT_BARCODE,
                                    numberCode,
                                    BarcodeProductFragment.this));
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_search_barcode);
    }

    @Override
    public void updateWithCommandResult(Context context)throws LogException {
        draw.hide();
        if(context.getEvent().compareToIgnoreCase(Events.SEARCH_PRODUCT_OK) == 0) {
            Intent intent = new Intent(getActivity(), ProductActivity.class);
            intent.putExtra(OBJ, (Product) context.getData());
            numberCodeText.setText("");
            startActivity(intent);
        }else{

            throw new LogException(Logs.PRODUCT_NOT_FOUND, getActivity());

            /*LogsView advise = new LogsView(Logs.PRODUCT_NOT_FOUND);
            advise.showInfo(this);*/
        }

    }
}
