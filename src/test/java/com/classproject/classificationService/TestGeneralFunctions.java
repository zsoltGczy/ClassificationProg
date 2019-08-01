package com.classproject.classificationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;
import com.classproject.domain.MixtureHazard;
import com.classproject.domain.SpecificConcLimit;

public class TestGeneralFunctions {

	MixtureComponent comp;
	MixtureComponent comp2;
	SpecificConcLimit spec;
	Mixture mixture;
	MixtureHazard mixtureHazard;
	ArrayList<String> testHazards;
	List<SpecificConcLimit> testSpec;
	List<MixtureComponent> testComponents;
	List<MixtureHazard> testMixHazards;
	
	
	
	@Before
	public void init() {
		
		comp = Mockito.mock(MixtureComponent.class);
		comp2 = new MixtureComponent();
		spec = Mockito.mock(SpecificConcLimit.class);
		mixture = Mockito.mock(Mixture.class);
		mixtureHazard = Mockito.mock(MixtureHazard.class);
		
		testHazards = new ArrayList<>();
		testHazards.add("test");
		
		testSpec = new ArrayList<SpecificConcLimit>();
		testSpec.add(spec);
		
		testComponents = new ArrayList<MixtureComponent>();
		testComponents.add(comp);
		
		testMixHazards = new ArrayList<MixtureHazard>();
	}
	@After
	public void destroy() {
		testHazards.clear();
		testSpec.clear();
		testComponents.clear();
		testMixHazards.clear();
	}
	
	
	
	
	
	@Test
	public void testAquaticAcute1AndChronic1_3() {
		comp2.setSpecificConcLimits(new ArrayList<SpecificConcLimit>());
		comp2.setHazards(testHazards);
		comp2.setConcentration(2.5);
		
		assertEquals("compHasntAnySpec", 2.5, GeneralFunctions.aquaticAcute1AndChronic1(comp2, "test", "test"), 0.0001);
	}
	@Test
	public void testAquaticAcute1AndChronic1_2() {
		comp2.setSpecificConcLimits(testSpec);
		comp2.setHazards(testHazards);
		comp2.setConcentration(2.5);
		when(spec.getName()).thenReturn("somethingElse"); 

		assertEquals("compHasntRelevantSpec", 2.5, GeneralFunctions.aquaticAcute1AndChronic1(comp2, "test", "test"), 0.0001);

		verify(spec).getName();
	}
	@Test
	public void testAquaticAcute1AndChronic1() {
		comp2.setSpecificConcLimits(testSpec);
		comp2.setHazards(testHazards);
		comp2.setConcentration(2.5);;
		when(spec.getName()).thenReturn("test"); 
		when(spec.getValue()).thenReturn(10.0);
		
		assertEquals("compHasRelevantSpec", 25, GeneralFunctions.aquaticAcute1AndChronic1(comp2, "test", "test"), 0.0001);

		verify(spec).getName();
		verify(spec, times(2)).getValue();
	}
	
	
	
	
	@Test
	public void testAdditiveHazardClassCalculation_4() {
		when(comp.getSpecificConcLimits()).thenReturn(new ArrayList<SpecificConcLimit>());
		when(comp.getConcentration()).thenReturn(0.5);

		assertEquals("compHasntAnySpecAndBelowCutOff", 0.0, 
				GeneralFunctions.additiveHazardClassCalculation(comp, "test", 5.0, 1.0), 0.0001);

		verify(comp).getSpecificConcLimits();
		verify(comp).getConcentration();
	}
	@Test
	public void testAdditiveHazardClassCalculation_3() {
		when(comp.getSpecificConcLimits()).thenReturn(new ArrayList<SpecificConcLimit>());
		when(comp.getConcentration()).thenReturn(1.0);
		
		assertEquals("compHasntAnySpec", 0.2, GeneralFunctions.additiveHazardClassCalculation(comp, "test", 5.0, 1.0), 0.0001);

		verify(comp).getSpecificConcLimits();
		verify(comp, times(2)).getConcentration();
	}
	@Test
	public void testAdditiveHazardClassCalculation_2() {
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(spec.getName()).thenReturn("somethingElse");
		when(comp.getConcentration()).thenReturn(2.0);
		
		assertEquals("compHasntRelevantSpec", 0.4, GeneralFunctions.additiveHazardClassCalculation(comp, "test", 5.0, 1.0), 0.0001);

		verify(comp, times(2)).getSpecificConcLimits();
		verify(spec).getName();
		verify(comp, times(2)).getConcentration();
	}
	@Test
	public void testAdditiveHazardClassCalculation_1() {
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(spec.getName()).thenReturn("test");
		when(comp.getConcentration()).thenReturn(2.0);
		when(spec.getValue()).thenReturn(1.0);
		
		assertEquals("compHasRelevantSpec", 2.0, GeneralFunctions.additiveHazardClassCalculation(comp, "test", 5.0, 1.0), 0.0001);
		
		verify(comp, times(2)).getSpecificConcLimits();
		verify(spec).getName();
		verify(comp).getConcentration();
		verify(spec).getValue();
	}
	
	
	
	
	
	@Test
	public void testSpecialCase() {
		when(mixture.getMixtureComponents()).thenReturn(testComponents);
		when(comp.getHazards()).thenReturn(testHazards);
		
		assertFalse(GeneralFunctions.specialCase(mixture, "test", "something"));
		
		verify(mixture).getMixtureComponents();
		verify(comp).getHazards();
	}
	
	
	
	
	
	@Test
	public void testNonAdditiveHazardClassCalculation_3() {
		when(mixture.getMixtureComponents()).thenReturn(testComponents);
		when(comp.getHazards()).thenReturn(testHazards);
		when(comp.getSpecificConcLimits()).thenReturn(new ArrayList<SpecificConcLimit>());
		when(comp.getConcentration()).thenReturn(3.0);

		assertTrue("compHasntAnySpec", GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, "test", 3.0, "generic"));

		verify(mixture).getMixtureComponents();
		verify(comp).getHazards();
		verify(comp).getSpecificConcLimits();
		verify(comp).getConcentration();
	}
	@Test
	public void testNonAdditiveHazardClassCalculation_2() {
		when(mixture.getMixtureComponents()).thenReturn(testComponents);
		when(comp.getHazards()).thenReturn(testHazards);
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(spec.getName()).thenReturn("else");
		when(comp.getConcentration()).thenReturn(3.0);
		
		assertTrue("compHasntRelevantSpec", GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, "test", 3.0, "generic"));
		
		verify(mixture).getMixtureComponents();
		verify(comp).getHazards();
		verify(comp, times(2)).getSpecificConcLimits();
		verify(spec).getName();
		verify(comp).getConcentration();
	}
	@Test
	public void testNonAdditiveHazardClassCalculation_1() {
		when(mixture.getMixtureComponents()).thenReturn(testComponents);
		when(comp.getHazards()).thenReturn(testHazards);
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(spec.getName()).thenReturn("test");
		when(spec.getValue()).thenReturn(2.0);
		when(comp.getConcentration()).thenReturn(2.0);
		
		assertTrue("compHasRelevantSpec", GeneralFunctions.nonAdditiveHazardClassCalculation(mixture, "test", 3.0, "generic"));
		
		verify(mixture).getMixtureComponents();
		verify(comp).getHazards();
		verify(comp, times(2)).getSpecificConcLimits();
		verify(spec).getName();
		verify(spec).getValue();
		verify(comp, times(2)).getConcentration();
	}
	
	
	
	
	@Test
	public void testComponentConcHigherThanSpecificConc_3() {
		when(comp.getSpecificConcLimits()).thenReturn(new ArrayList<SpecificConcLimit>());
		assertFalse("compHasntAnySpec", GeneralFunctions.componentConcHigherThanSpecificConc(comp, "test", false));
		verify(comp).getSpecificConcLimits();
	}
	@Test
	public void testComponentConcHigherThanSpecificConc_2() {
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(spec.getName()).thenReturn("else");
		
		assertFalse("compHasntRelevantSpec", GeneralFunctions.componentConcHigherThanSpecificConc(comp, "test", false));
		
		verify(comp, times(2)).getSpecificConcLimits();
		verify(spec).getName();
	}
	@Test
	public void testComponentConcHigherThanSpecificConc_1() {
		when(comp.getSpecificConcLimits()).thenReturn(testSpec);
		when(comp.getConcentration()).thenReturn(0.1);
		when(spec.getName()).thenReturn("test");
		when(spec.getValue()).thenReturn(0.1);
		
		assertTrue("compHasRelevantSpec", GeneralFunctions.componentConcHigherThanSpecificConc(comp, "test", false));
		
		verify(comp, times(2)).getSpecificConcLimits();
		verify(comp).getConcentration();
		verify(spec).getName();
		verify(spec).getValue();
	}
	
	
	
	
	@Test
	public void testComponentHasHazard() {
		when(comp.getHazards()).thenReturn(testHazards);
		assertTrue(GeneralFunctions.componentHasHazard(comp, "test"));
		verify(comp).getHazards();
	}
	
	
	
	@Test
	public void testAddNewHazard() {
		when(mixture.getHazardsOfMixture()).thenReturn(testMixHazards);
		assertEquals(0, testMixHazards.size());
		
		GeneralFunctions.addNewHazard(mixture, "name", "pictogram", "signalword",
				"hazardStatement", "precautStatement");
		
		assertEquals(1, testMixHazards.size());
		
		verify(mixture).getHazardsOfMixture();
	}
}
