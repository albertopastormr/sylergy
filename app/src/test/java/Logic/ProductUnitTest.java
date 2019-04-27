package Logic;


import com.example.sylergy.objects.Product;
import com.example.sylergy.utils.ArrayListUtils;

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
        sameProduct = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("1", "Ingredient1");}});}},
                "N", new HashMap<String, Object>(){{put("1","Hola");}} );

        when(product.getBarcode()).thenReturn("1234");
        when(product.getImage()).thenReturn("https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg");
        when(product.getIngredientsTags()).thenReturn(new ArrayList<String>() {{add("{1=Ingredient1}");}});
        when(product.getName()).thenReturn("N");
        when(product.getNutrientsTags()).thenReturn(new HashMap<String, Object>(){{put("1","Hola");}});
    }

    @Test
    public void toStringTest(){
        assertEquals(("Name: " + product.getName()
                + "\n -Barcode: " + product.getBarcode()
                + "\n -Ingredients: " + product.getIngredientsTags().toString()
                + "\n -Nutrients: " + product.getNutrientsTags().toString()
                + "\n -URLimage: " + product.getImage() + "\n"), sameProduct.toString());
    }

    @Test
    public void getIngredientsTest() {
        Product productToTest = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}},
                "N", new HashMap<String, Object>(){{put("1","Hola");}} );

        List<String> expectedIngredients = new ArrayList<>();
        expectedIngredients.add("Ingredient1");

        assertEquals(expectedIngredients, productToTest.getIngredientsTags());
    }

    @Test
    public void getNutrientsTest() {
        Product p1 = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>>() {{
                    add(new HashMap<String, Object>() {{
                        put("text", "Ingredient1");
                    }});
                }},
                "b", new HashMap<String, Object>() {{
            put("fat", "Value1");
            put("fat_100g", "Value2"); //We want this nutrient
            put("fat_200g", "Value3");
            put("carbohydrates_100g", "Value4"); //We want this too.
        }});

        HashMap<String, Object> expected = new HashMap<>();
        expected.put("fat", "Value2");
        expected.put("carbohydrates", "Value4");

        assertEquals(expected, p1.getNutrientsTags());

        p1 = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>>() {{
                    add(new HashMap<String, Object>() {{
                        put("text", "Ingredient1");
                    }});
                }},
                "b", new HashMap<String, Object>() {{
            put("fat", "Value1");
            put("fat_200g", "Value2");
            put("carbohydrates", "Value3");
            put("nutrition-score-uk_100g", "Value5");
            put("nova-group_100g", "Value6");
            put("nutrition-score-fr_100g", "Value7");
        }});

        assertEquals(new HashMap<String, Object>(), p1.getNutrientsTags());

    }

    @Test
    public void sortByNameTest() {
        Product p1 = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}},
                "b", new HashMap<String, Object>(){{put("1","Hola");}} );

        Product p2 = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}},
                "c", new HashMap<String, Object>(){{put("1","Hola");}} );

        Product p3 = new Product("1234", "https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}},
                "a", new HashMap<String, Object>(){{put("1","Hola");}} );

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

        //Now the list is : {b, c, a}

        productList = ArrayListUtils.sortByName(productList);

        //Now it should be {a, b, c}

        ArrayList<Product> exptectedList = new ArrayList<>();
        exptectedList.add(p3);
        exptectedList.add(p1);
        exptectedList.add(p2);

        assertEquals(exptectedList, productList);
    }




}
