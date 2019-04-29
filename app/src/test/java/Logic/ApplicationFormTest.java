package Logic;

import android.app.Activity;

import com.example.sylergy.activities.ProductActivity;
import com.example.sylergy.objects.ApplicationForm;
import com.example.sylergy.objects.Product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
public class ApplicationFormTest {
    private Product correctProduct;
    private Product wrongProduct;
    private ApplicationForm correctApplicationForm;
    private ApplicationForm wrongApplicationForm;
    private Activity activity;

    @Before
    public void before(){
        correctProduct = Mockito.mock(Product.class);
        wrongProduct = Mockito.mock(Product.class);
        //activity = Mockito.mock(Activity.class);
        ProductActivity activity;

        correctProduct = Mockito.mock(Product.class);
        when(correctProduct.getBarcode()).thenReturn("12345678");
        when(correctProduct.getImage()).thenReturn("https://static.openfoodfacts.org/images/products/0/front_en.46.400.jpg");
        when(correctProduct.getIngredients()).thenReturn(new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("Ingredient1", "Ingredient1");}});}});
        when(correctProduct.getName()).thenReturn("N");
        when(correctProduct.getNutrients()).thenReturn(new HashMap<String, Object>(){{put("1","Hola");}});


        wrongProduct = Mockito.mock(Product.class);
        when(wrongProduct.getBarcode()).thenReturn("1234");
        when(wrongProduct.getImage()).thenReturn("");
        when(wrongProduct.getIngredients()).thenReturn(new ArrayList<HashMap<String, Object>>());
        when(wrongProduct.getName()).thenReturn("");
        when(wrongProduct.getNutrients()).thenReturn(new HashMap<String, Object>());

    }

    @Test
    public void checkBarcodeTest(){

       /* this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkBarcode(correctProduct.getBarcode(), activity);
        assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkBarcode(wrongProduct.getBarcode(), activity);
        assertEquals(test, false);*/

    }

    @Test
    public void checkNameTest(){
       /* this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkName(correctProduct.getName(), activity);
        assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkName(wrongProduct.getName(), activity);
        assertEquals(test, false);*/

    }
    @Test
    public void checkIngredientsTest(){
       /* this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkIngredients(correctProduct.getIngredients(), activity);
        assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkIngredients(wrongProduct.getIngredients(), activity);
        assertEquals(test, false);*/
    }
    @Test
    public void checkNutrientsTest(){
       /* this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkNutrients(correctProduct.getNutrients(), activity);
        assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkNutrients(wrongProduct.getNutrients(), activity);
        assertEquals(test, false);*/
    }
    @Test
    public void checkImageTest(){
        /*Product sameProduct = new Product("1234", "https://static.openfoodfacts.org/images/products/0/front_en.46.400.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("1", "Ingredient1");}});}},
                "N", new HashMap<String, Object>(){{put("1","Hola");}} );*/

        /*this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkImage(correctProduct.getImage(), activity);
        assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkImage(wrongProduct.getImage(), activity);
        assertEquals(test, false);*/
    }
    @Test
    public void checkApplicationFormTest(){
        /*this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkApplicationForm(correctProduct, activity);
         assertEquals(test, true);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkApplicationForm(wrongProduct, activity);
        assertEquals(test, false);*/
    }

}
