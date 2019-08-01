package com.classproject.classificationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.classproject.domain.Mixture;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AspirationService.class})
public class TestAspirationService {

	AspirationService aspirationService;
	Mixture mixture;
	
	@Before
	public void init() {
		aspirationService = new AspirationService();
		mixture = Mockito.mock(Mixture.class);
	}
	
	@Test
	public void testKinematicViscosity_2() throws Exception {
		when(mixture.getKinematicViscosity()).thenReturn(null);
		when(mixture.getDynamicViscosity()).thenReturn(4.0);
		when(mixture.getDensity()).thenReturn(2.0);
		
		assertEquals("mixtureHasntKinematicVisc", Double.valueOf(2), Whitebox.invokeMethod(aspirationService, "kinematicViscosity", mixture));
		
		verify(mixture).getKinematicViscosity();
		verify(mixture, times(2)).getDynamicViscosity();
		verify(mixture, times(2)).getDensity();
	}
	@Test
	public void testKinematicViscosity_1() throws Exception {
		when(mixture.getKinematicViscosity()).thenReturn(2.0);
		assertEquals("mixtureHasKinematicVisc", Double.valueOf(2), Whitebox.invokeMethod(aspirationService, "kinematicViscosity", mixture));
		verify(mixture, times(2)).getKinematicViscosity();
	}
}
