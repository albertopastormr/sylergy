package com.example.sylergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.logs.LogException;

import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.ApplicationForm;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.presenter.Presenter;
import com.google.firebase.database.DatabaseException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateProductFragment extends Fragment implements UpdateActivity {

    ImageView productImage;
    EditText productName;
    EditText productBarcode;

    Button uploadPicture;
    private static final int PICK_IMAGE = 100;
    private static final int UPLOAD_IMAGE = 101;
    Uri imageUri;
    Button scanBarcode;

    Button button_addNutriments;
    TextView nutriments;
    String[] listNutriments;
    boolean[] checkedNutriments;
    ArrayList<Integer> selectedNutriments;

    EditText ingredients;

    ImageButton checkNewProduct;


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

        button_addNutriments = view.findViewById(R.id.button_addNutrients);
        listNutriments = getResources().getStringArray(R.array.nutrients);
        checkedNutriments = new boolean[listNutriments.length];
        selectedNutriments = new ArrayList<>();

        ingredients = view.findViewById(R.id.ingredients);

        checkNewProduct = view.findViewById(R.id.button_checkNewProduct);


        uploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(CreateProductFragment.this.getContext());
                pictureDialog.setTitle("Select Action");
                String[] pictureDialogItems = {
                        "Select photo from gallery",
                        "Capture photo from camera"};
                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                        startActivityForResult(gallery, PICK_IMAGE);
                                        break;
                                    case 1:
                                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(camera, UPLOAD_IMAGE);
                                        break;
                                }
                            }
                        });
                pictureDialog.show();
            }
        });


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
                        } else if (selectedNutriments.contains(position)) {

                            selectedNutriments.remove(selectedNutriments.indexOf(position));
                        }
                    }
                });

                builder.setCancelable(false);
                builder.setPositiveButton((R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nutrients = "";
                        for (int i = 0; i < selectedNutriments.size(); i++) {
                            if (i < selectedNutriments.size() - 1) {
                                nutrients = nutrients + listNutriments[selectedNutriments.get(i)] + ", ";
                            } else {
                                nutrients = nutrients + listNutriments[selectedNutriments.get(i)];
                            }

                        }
                        nutriments.setText(nutrients);
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


        checkNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a new product for test
               /* ArrayList<HashMap<String, Object>> ingredientsList= new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> map1=new HashMap<String, Object>();
                map1.put("id","test ID 1");
                map1.put("rank","test rank 1");
                map1.put("text","test text 1");
                HashMap<String, Object> map2=new HashMap<String, Object>();
                map2.put("id","test ID 2");
                map2.put("rank","test rank 2");
                map2.put("text","test text 2");

                ingredientsList.add(map1);
                ingredientsList.add(map2);
                Product product = new Product("123456","test url",ingredientsList,"Test Product",map1);*/


                ApplicationForm applicationForm = new ApplicationForm();

                //Convertir EditTest en List<String>
                String ingredients1 = ingredients.getText().toString();
                ArrayList<HashMap<String, Object>> arrayListIngredients = new ArrayList<HashMap<String, Object>>();
                if (!ingredients1.isEmpty()) {
                    String[] ingredientsArray = ingredients1.split("\n");

                    List<String> ingredientsList = Arrays.asList(ingredientsArray);

                    for (int i = 0; i < ingredientsList.size(); i++) {
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("id", ingredientsList.get(i));
                        map.put("rank", i);
                        map.put("text", ingredientsList.get(i));
                        arrayListIngredients.add(map);
                    }
                }

                HashMap<String, Object> nutrientsMap = new HashMap<String, Object>();
                for (int i : selectedNutriments) {
                    //AQUI DEBE IR EL ARGUMENTO DE LOS NUTRIENTES
                    nutrientsMap.put(listNutriments[i], listNutriments[i]);
                }


                Product product = null;

                //Si pasa los criterios del formulario
                if (applicationForm.checkName(productName.getText().toString(), getActivity()) &&
                        applicationForm.checkBarcode(productBarcode.getText().toString(), getActivity()) &&
                        applicationForm.checkIngredients(arrayListIngredients, getActivity())) {
                    //&& applicationForm.checkNutrients(productName.getText().toString(), getActivity())){
                    product = new Product(productBarcode.getText().toString(), productImage.toString(), arrayListIngredients,
                            productName.getText().toString(), nutrientsMap);
                    Presenter.getInstance().action(new Context(Events.CREATE_PRODUCT, product, CreateProductFragment.this));

                }
            }
        });


        return view;
    }


    @Override
    public void updateWithCommandResult(Context context) throws LogException {
        if (context.getEvent().compareToIgnoreCase(Events.CREATE_PRODUCT_OK) == 0) {
            Toast.makeText(MainActivity.context, ((Product) context.getData()).toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.context, (String) context.getData(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            try {
                InputStream imageStream = this.getContext().getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                selectedImage = getResizedBitmap(selectedImage, 250);// tamaño de la imagen
                Toast.makeText(CreateProductFragment.this.getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                productImage.setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == UPLOAD_IMAGE) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            productImage.setImageBitmap(thumbnail);
            //saveImage(thumbnail);
            Toast.makeText(CreateProductFragment.this.getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = maxSize;
        int height = maxSize;
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
