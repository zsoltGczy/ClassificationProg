package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class ReproductiveToxicityAutoCorrection {
	
	private static final String reproductiveToxicity1A = "Repr. 1A (H360)";
	private static final String reproductiveToxicity1B = "Repr. 1B (H360)";
	private static final String reproductiveToxicity2 = "Repr. 2 (H361)";
	

	protected static void correctReproductiveToxicity(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(reproductiveToxicity1A)) {
			
			if(list.contains(reproductiveToxicity1B)) {
				list.remove(reproductiveToxicity1B);
			}
			if(list.contains(reproductiveToxicity2)) {
				list.remove(reproductiveToxicity2);
			}
		}
		else if (list.contains(reproductiveToxicity1B)) {
			
			if(list.contains(reproductiveToxicity2)) {
				list.remove(reproductiveToxicity2);
			}
		}
	}
	
	
	
	
}
