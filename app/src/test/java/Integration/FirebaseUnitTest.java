package Integration;

import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({FirebaseDatabase.class})

public class FirebaseUnitTest {

    private DatabaseReference databaseRef;
    private Product product;
    private Query query;

    @Before
    public void before(){
        /* We use Mockito to emulate a Firebase connection */
        databaseRef = Mockito.mock(DatabaseReference.class);
        query = Mockito.mock(Query.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference("Products")).thenReturn(databaseRef);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        when(databaseRef.orderByChild(anyString())).thenReturn(query);
    }

    @Test
    public void getQueryByChild(){
        assertEquals(query, FirebaseUtil.getQueryByChild(databaseRef,anyString()));
        assertEquals(query,FirebaseUtil.getQueryByChild(databaseRef, "name"));
    }
}
