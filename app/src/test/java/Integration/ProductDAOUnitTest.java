package Integration;

import com.example.sylergy.activities.BarcodeProductActivity;
import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
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


public class ProductDAOUnitTest {

    private DatabaseReference database;
    private Product product;
    private Query query;
    private Context context;

    @Before
    public void before(){
        database = Mockito.mock(DatabaseReference.class);
        query = Mockito.mock(Query.class);
        context = Mockito.mock(Context.class);
        product = Mockito.mock(Product.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference("Products")).thenReturn(database);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        /* simulates the behaviour of the database reference */
        when(database.child(anyString())).thenReturn(database);
        when(database.orderByChild(anyString())).thenReturn(database);
        when(database.orderByChild(anyString()).equalTo(anyString())).thenReturn(database);

        when(context.getData()).thenReturn("1234");

        when(product.getBarcode()).thenReturn("1234");
        when(product.getImage()).thenReturn("https://fotos01.lne.es/2018/09/23/690x278" +
                "/el-alimento-con-el-que-adelgazaras-y-quemaras-" +
                "grasas-si-los-comes-todos-los-dias.jpg");
        when(product.getIngredients()).thenReturn(null);
        when(product.getName()).thenReturn("N");
        when(product.getNutrients()).thenReturn(new HashMap<String, Object>(){{put("1","Hola");}});
    }

    @Test
    public void correctReadById(){

    }
}
