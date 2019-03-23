package Logic;


import com.example.sylergy.objects.Product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;


import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)

public class ProductUnitTest {
    private Product product;


    @Before
    public void before(){
        product = Mockito.mock(Product.class);

        when(product.getBarcode()).thenReturn("1234");
        when(product.getImage()).thenReturn("https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg");
        when(product.getIngredients()).thenReturn(null);
        when(product.getName()).thenReturn("N");
        when(product.getNutrients()).thenReturn(new HashMap<String, Object>(){{put("1","Hola");}});
    }

    @Test
    public void TryToString(){
        assertEquals(("Name: " + product.getName()
                + "\n -Barcode: " + product.getBarcode()
                + "\n -Ingredients: " + product.getIngredients().toString()
                + "\n -Nutrients: " + product.getNutrients().toString()
                + "\n -URLimage: " + product.getImage() + "\n"), product.toString());
    }


}
