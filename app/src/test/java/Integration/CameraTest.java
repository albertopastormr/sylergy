package Integration;

import com.example.sylergy.fragments.BarcodeProductFragment;
import com.google.zxing.integration.android.IntentIntegrator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({IntentIntegrator.class})
public class CameraTest {

    private BarcodeProductFragment barcodeFragment;
    private IntentIntegrator integratorMock;

    @Before
    public void before(){
        barcodeFragment = Mockito.mock(BarcodeProductFragment.class);
    }

    @Test
    public void correctConnectionTotalMock(){

        integratorMock = Mockito.mock(IntentIntegrator.class);
        PowerMockito.mockStatic(IntentIntegrator.class);

        when(IntentIntegrator.forSupportFragment(barcodeFragment)).thenReturn(integratorMock);

        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(barcodeFragment);
        Assert.assertNotNull(integrator);
    }

    @Test
    public void correctConnectionApi(){
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(barcodeFragment);
        Assert.assertNotNull(integrator);
    }

    @Test
    public void correctAttributeApi(){
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(barcodeFragment);

        Assert.assertNotNull(integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES));
        Assert.assertNotNull(integrator.setPrompt("Scan Barcode"));
        Assert.assertNotNull(integrator.setCameraId(0)); // camera default
        Assert.assertNotNull(integrator.setBeepEnabled(false)); // sound
    }
}
