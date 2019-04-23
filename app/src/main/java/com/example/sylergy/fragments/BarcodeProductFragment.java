package com.example.sylergy.fragments;

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
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.ProductActivity;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.presenter.Presenter;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeProductFragment extends Fragment implements UpdateActivity {
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    Button btnSearch;
    ImageButton btnCamera;
    EditText numberCodeText;
    ProgressDialog draw;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_barcode, container, false);

        btnSearch = view.findViewById(R.id.btnSearch);
        btnCamera = view.findViewById(R.id.btnCamera);
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
                    // mIdlingResource!=null is in test, and stop it
                    if(MainActivity.mIdlingResource!=null)
                        MainActivity.mIdlingResource.setIdleState(false);

                    draw.show();
                    Presenter.getInstance()
                            .action(new Context(Events.SEARCH_PRODUCT_BARCODE,
                                    numberCode,
                                    BarcodeProductFragment.this));



                }
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(BarcodeProductFragment.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan Barcode");
                integrator.setCameraId(0); // camera default
                integrator.setBeepEnabled(false); // sound

                integrator.initiateScan();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Toast.makeText(this.getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                draw.show();
                Presenter.getInstance()
                        .action(new Context(Events.SEARCH_PRODUCT_BARCODE,
                                result.getContents(),
                                BarcodeProductFragment.this));

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void updateWithCommandResult(Context context) {
        draw.dismiss();

        if(context.getEvent().compareToIgnoreCase(Events.SEARCH_PRODUCT_OK) == 0) {
            Intent intent = new Intent(getActivity(), ProductActivity.class);
            intent.putExtra(OBJ, (Product) context.getData());
            numberCodeText.setText("");
            startActivity(intent);
        }else{
            //throw new LogException(Logs.PRODUCT_NOT_FOUND, getActivity());
            LogsView advise = new LogsView(Logs.PRODUCT_NOT_FOUND);
            advise.showInfo(getActivity());
        }

        // mIdlingResource!=null is in test, and continue it
        if(MainActivity.mIdlingResource!=null)
            MainActivity.mIdlingResource.setIdleState(true);
    }
}
