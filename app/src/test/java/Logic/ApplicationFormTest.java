package Logic;

import android.app.Activity;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
public class ApplicationFormTest {
    private Product correctProduct;
    private Product wrongProduct;
    private ApplicationForm correctApplicationForm;
    private ApplicationForm wrongApplicationForm;

    @Before
    public void before(){
        correctProduct = Mockito.mock(Product.class);
        wrongProduct = Mockito.mock(Product.class);

        correctProduct = Mockito.mock(Product.class);
        when(correctProduct.getBarcode()).thenReturn("12345678");
        when(correctProduct.getImage()).thenReturn("https://static.openfoodfacts.org/images/products/0/front_en.46.400.jpg");
        when(correctProduct.getIngredients()).thenReturn(new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}});
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

        this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkBarcode(correctProduct.getBarcode()).getCorrect();
        assertTrue(test);


        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkBarcode(wrongProduct.getBarcode()).getCorrect();
        assertFalse(test);

    }

    @Test
    public void checkNameTest(){
        this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkName(correctProduct.getName()).getCorrect();
        assertTrue(test);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkName(wrongProduct.getName()).getCorrect();
        assertFalse(test);

    }
    @Test
    public void checkIngredientsTest(){
        this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkIngredients(correctProduct.getIngredients()).getCorrect();
        assertTrue(test);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkIngredients(wrongProduct.getIngredients()).getCorrect();
        assertFalse(test);
    }
    @Test
    public void checkNutrientsTest(){
        this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkNutrients(correctProduct.getNutrients()).getCorrect();
        assertTrue(test);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkNutrients(wrongProduct.getNutrients()).getCorrect();
        assertFalse(test);
    }
    @Test
    public void checkImageTest(){

        /*this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkImage(correctProduct.getImage(), activity);
        assertTrue(test);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkImage(wrongProduct.getImage(), activity);
        assertFalse(test);*/
    }
    @Test
    public void checkApplicationFormTest(){
        this.correctApplicationForm = new ApplicationForm();
        boolean test = this.correctApplicationForm.checkApplicationForm(correctProduct).getCorrect();
        assertTrue(test);

        this.wrongApplicationForm = new ApplicationForm();
        test = this.wrongApplicationForm.checkApplicationForm(wrongProduct).getCorrect();
        assertFalse(test);
    }

}
