package integrationTest.IntegrationLayer;


import com.example.sylergy.fragments.SearchFragment;
import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.product.dao.imp.DAOProductImp;
import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})


public class ProductDAOIntegrationTest {
    private Context context;

    @Before
    public void prepareTest(){
        context = Mockito.mock(Context.class);
        when(context.getEvent()).thenReturn(Events.SEARCH_PRODUCT_NAME);
        when(context.getActivity()).thenReturn();
    }

    @Test
    public void readByNameOK(){
        Product p = DAOProductFactory.getInstance().generateDAOProduct().readByName()
        assertEquals();
    }
}
