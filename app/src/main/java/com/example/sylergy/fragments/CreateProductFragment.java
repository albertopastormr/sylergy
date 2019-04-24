package com.example.sylergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sylergy.R;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.logs.LogException;

import com.example.sylergy.objects.Context;

import java.util.ArrayList;

public class CreateProductFragment extends Fragment implements UpdateActivity {

    ImageView productImage;
    EditText productName;
    EditText productBarcode;

    Button uploadPicture;
    Button scanBarcode;

    Button button_addNutriments;
    TextView nutriments;
    String[] listNutriments;
    boolean[] checkedNutriments;
    ArrayList<Integer> selectedNutriments;

    EditText ingredients;

    Button checkNewProduct;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_product, container, false);


        nutriments = view.findViewById(R.id.selectedNutrients);
        productImage = view.findViewById(R.id.productImage);
        productName = view.findViewById(R.id.editText_productName);
        productBarcode = view.findViewById(R.id.editText_productBarcode);
        uploadPicture = view.findViewById(R.id.button_uploadPicture);
        scanBarcode = view.findViewById(R.id.button_scanBarcode);

        button_addNutriments =  view.findViewById(R.id.button_addNutrients);
        listNutriments = getResources().getStringArray(R.array.nutrients);
        checkedNutriments = new boolean[listNutriments.length];
        selectedNutriments = new ArrayList<>();

        ingredients = view.findViewById(R.id.ingredients);

       // checkNewProduct = view.findViewById(R.id.button_checkNewProduct)

        button_addNutriments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle((R.string.select_nutrients_title));
                builder.setMultiChoiceItems(listNutriments, checkedNutriments, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedNutriments.contains(position)) {
                                selectedNutriments.add(position);
                            }
                        } else  if (selectedNutriments.contains(position)){

                            selectedNutriments.remove(selectedNutriments.indexOf(position));
                        }
                    }
                });

                    builder.setCancelable(false);
                    builder.setPositiveButton((R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String alergeno = "";
                            for (int i = 0; i < selectedNutriments.size(); i++) {
                                if (i < selectedNutriments.size()-1) {
                                    alergeno = alergeno + listNutriments[selectedNutriments.get(i)] + ", ";
                                } else {
                                    alergeno = alergeno + listNutriments[selectedNutriments.get(i)];
                                }

                            }
                            nutriments.setText(alergeno);
                        }

                    });

                    builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 0; i < checkedNutriments.length; i++) {
                                checkedNutriments[i] = false;
                                selectedNutriments.clear();
                                nutriments.setText("");

                            }
                        }
                    });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });





        return view;
    }

    @Override
    public void updateWithCommandResult(Context context) throws LogException {


    }
}
