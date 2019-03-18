package com.example.sylergy.product;

import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})

public class UnifiedTest {

    private DatabaseReference database;
    private Product product;
    private Query query;

    @Before
    public void before(){
        /* We use Mockito to emulate a Firebase connection */
        database = Mockito.mock(DatabaseReference.class);
        query = Mockito.mock(Query.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference("Products")).thenReturn(database);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        /* We emulate the behaviour of the database reference */
        when(database.child(anyString())).thenReturn(database);
        when(database.orderByChild(anyString())).thenReturn(database);
        when(database.orderByChild(anyString()).equalTo(anyString())).thenReturn(database);
    }

    @Test
    public void correctConexionFirebase() {
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "N" , new HashMap<String, Object>(){{put("1","Hola");}});

        /* These are not real database connections, Mockito emulate it to us */
        DatabaseReference ref = FirebaseUtil.getSpecifiedReference("Products");

        assertTrue(FirebaseUtil.upload(ref, product));
    }

    @Test
    public void correctSimulateReadProduct() {
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "N" , new HashMap<String, Object>(){{put("1","Hola");}});
        DatabaseReference ref = FirebaseUtil.getSpecifiedReference("Products");

        Query q = FirebaseUtil.getQueryByChild(ref, "barcode").equalTo(product.getBarcode().toString()); //We emulate a query where we get the product that we uploaded

        /* We emulate the behaviour of a query */
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];

                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                when(mockedDataSnapshot.getValue(Product.class)).thenReturn(product);

                valueEventListener.onDataChange(mockedDataSnapshot);
                return null;
            }
        }).when(q).addListenerForSingleValueEvent(any(ValueEventListener.class));

        assertNotNull(FirebaseUtil.getProductFromQuery(q));
    }

    @Test
    public void notExistSimulateReadProduct() {
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "N" , new HashMap<String, Object>(){{put("1","Hola");}});
        DatabaseReference ref = FirebaseUtil.getSpecifiedReference("Products");

        Query q = FirebaseUtil.getQueryByChild(ref, "barcode").equalTo(0); //We emulate a query where we get the product that we uploaded

        assertNull(FirebaseUtil.getProductFromQuery(q));
    }

    // return QUERY
    @Test
    public void correctReadProduct() {
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "N" , new HashMap<String, Object>(){{put("1","Hola");}});
        DAOProduct daoMocked = Mockito.mock(DAOProduct.class);
        when(daoMocked.readById(1234L)).thenReturn(query);

        Query q = daoMocked.readById(1234L);

        /* We emulate the behaviour of a query */
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];

                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                when(mockedDataSnapshot.getValue(Product.class)).thenReturn(product);

                valueEventListener.onDataChange(mockedDataSnapshot);
                return null;
            }
        }).when(q).addListenerForSingleValueEvent(any(ValueEventListener.class));

        assertNotNull(FirebaseUtil.getProductFromQuery(q));
    }

    // return QUERY
    @Test
    public void notExistReadProduct() {
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "N" , new HashMap<String, Object>(){{put("1","Hola");}});

        DAOProduct daoMocked = Mockito.mock(DAOProduct.class);
        when(daoMocked.readById(1234L)).thenReturn(query);

        Query q = daoMocked.readById(1234L);

        assertNull(FirebaseUtil.getProductFromQuery(q));
    }

    /**@Test
    public void correctReadProduct() {
        product = new Product("Test_Product", 1234, Arrays.asList("IngredientOne", "IngredientTwo"), Arrays.asList("adaptedForOne", "adaptedForTwo"));

        DAOProduct daoMocked = Mockito.mock(DAOProduct.class);
        when(daoMocked.readById(1234L)).thenReturn(product);

        Product p = daoMocked.readById(1234L);

        assertNotNull(p);
    }*/

    /**@Test
    public void notExistReadProduct() {
        product = new Product("Test_Product", 1234, Arrays.asList("IngredientOne", "IngredientTwo"), Arrays.asList("adaptedForOne", "adaptedForTwo"));

        DAOProduct daoMocked = Mockito.mock(DAOProduct.class);
        when(daoMocked.readById(1234L)).thenReturn(product);

        Product p = daoMocked.readById(0L);

        assertNull(p);
    }*/

}
