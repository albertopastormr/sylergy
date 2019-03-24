package Logic;


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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)

public class ProductUnitTest {
    private Product product;
    private Product sameProduct;


    @Before
    public void before(){
        product = Mockito.mock(Product.class);
        sameProduct = new Product("1234",
                "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>()
                {{put("1", "Ingredient1");}});}},
                "N", new HashMap<String, Object>(){{put("1","Hola");}} );

        when(product.getBarcode()).thenReturn("1234");
        when(product.getImage()).thenReturn("https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg");
        when(product.getIngredients()).thenReturn(new ArrayList<String>()
        {{add("{1=Ingredient1}");}});
        when(product.getName()).thenReturn("N");
        when(product.getNutrimets()).thenReturn(new HashMap<String, Object>(){{put("1","Hola");}});
    }

    @Test
    public void TryToString(){
        assertEquals(("Name: " + product.getName()
                + "\n -Barcode: " + product.getBarcode()
                + "\n -Ingredients: " + product.getIngredients().toString()
                + "\n -Nutrients: " + product.getNutrimets().toString()
                + "\n -URLimage: " + product.getImage() + "\n"), sameProduct.toString());
    }


}
