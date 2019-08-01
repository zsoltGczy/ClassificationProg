package com.classproject.classificationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.classproject.domain.MixtureComponent;
import com.classproject.domain.SpecificConcLimit;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AcuteToxicityService.class})
public class TestAcuteToxicityService {

	AcuteToxicityService acuteToxicityService;
	MixtureComponent comp;
	ArrayList<SpecificConcLimit> specList;
	ArrayList<String> acuteToxhazard;
	SpecificConcLimit relevantSpec;
	SpecificConcLimit notRelevantSpec;
	
	@Before
	public void init() {
		acuteToxicityService = Mockito.mock(AcuteToxicityService.class);
		comp = Mockito.mock(MixtureComponent.class);
		specList = new ArrayList<SpecificConcLimit>();
		acuteToxhazard = new ArrayList<String>();
		relevantSpec = new SpecificConcLimit("oral: ATE", 1.0);
		notRelevantSpec = new SpecificConcLimit("somethingElse", 1.0);
	}
	
	@Test
	public void testConcDivAte_3() throws Exception {
		acuteToxhazard.add("Acute Tox. 1 (H300)");
		
		when(comp.getConcentration()).thenReturn(0.2);
		when(comp.getHazards()).thenReturn(acuteToxhazard);
		when(comp.getSpecificConcLimits()).thenReturn(specList);
		
		assertEquals("compHasntAnySpec", Double.valueOf(0.1), Whitebox.invokeMethod(acuteToxicityService, "concDivAte", 
				comp, "Acute Tox. 1 (H300)", "oral: ATE", 2.0));
		
		verify(comp, times(3)).getConcentration();
		verify(comp).getHazards();
		verify(comp).getSpecificConcLimits();
	}
	@Test
	public void testConcDivAte_2() throws Exception {
		specList.add(notRelevantSpec);
		acuteToxhazard.add("Acute Tox. 1 (H300)");
		
		when(comp.getConcentration()).thenReturn(50.0);
		when(comp.getHazards()).thenReturn(acuteToxhazard);
		when(comp.getSpecificConcLimits()).thenReturn(specList);
		
		assertEquals("compHasntRelevantSpec", Double.valueOf(25), Whitebox.invokeMethod(acuteToxicityService, "concDivAte", 
				comp, "Acute Tox. 1 (H300)", "oral: ATE", 2.0));
		
		verify(comp, times(3)).getConcentration();
		verify(comp).getHazards();
		verify(comp, times(2)).getSpecificConcLimits();
	}
	@Test
	public void testConcDivAte_1() throws Exception {
		specList.add(relevantSpec);
		acuteToxhazard.add("Acute Tox. 1 (H300)");
		
		when(comp.getConcentration()).thenReturn(0.1);
		when(comp.getHazards()).thenReturn(acuteToxhazard);
		when(comp.getSpecificConcLimits()).thenReturn(specList);
		
		assertEquals("compHasRelevantSpec", Double.valueOf(0.1), Whitebox.invokeMethod(acuteToxicityService, "concDivAte", 
				comp, "Acute Tox. 1 (H300)", "oral: ATE", 2.0));
		
		verify(comp, times(2)).getConcentration();
		verify(comp).getHazards();
		verify(comp, times(2)).getSpecificConcLimits();
	}
}
