package com.example.example;

import com.example.example.Objects.Product;
import com.example.example.TestClasses.JUnitTestClasses;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})

public class JUnitTestWithMockito {

    DatabaseReference database;
    Product p;

    @Before
    public void before(){
        /* We use Mockito to emulate a Firebase connection */
        database = Mockito.mock(DatabaseReference.class);

        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
        when(mockedFirebaseDatabase.getReference("Productos")).thenReturn(database);

        PowerMockito.mockStatic(FirebaseDatabase.class);
        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);

        /* We emulate the behaviour of the databse reference */
        when(database.child(anyString())).thenReturn(database);
        when(database.orderByChild(anyString())).thenReturn(database);
        when(database.orderByChild(anyString()).equalTo(anyString())).thenReturn(database);

    }

    @Test
    public void correct_upload() {
        p = new Product("Test_Product", 1234, null, null);

        /* These are not real database connections, Mockito emulate it to us */
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference("Productos");

        assertTrue(JUnitTestClasses.upload(ref, p));
    }

    @Test
    public void correct_getProdcutFromQuery() {
        p = new Product("Test_Product", 1234, null, null);

        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fdb.getReference("Productos");

        Query q = ref.orderByChild("Prodcutos").equalTo(p.getBarcode().toString()); //We emulate a query where we get the product that we uploaded

        /* We emulate the behaviour of a query */
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];

                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
                when(mockedDataSnapshot.getValue(Product.class)).thenReturn(p);

                valueEventListener.onDataChange(mockedDataSnapshot);

                return null;
            }
        }).when(q).addListenerForSingleValueEvent(any(ValueEventListener.class));

        assertNotNull(JUnitTestClasses.getProductFromQuery(q));
    }




}
