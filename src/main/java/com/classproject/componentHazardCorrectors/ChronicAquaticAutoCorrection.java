package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class ChronicAquaticAutoCorrection {
	
	private static final String aquaticChronic1 = "Aquatic Chronic 1 (H410)";
	private static final String aquaticChronic2 = "Aquatic Chronic 2 (H411)";
	private static final String aquaticChronic3 = "Aquatic Chronic 3 (H412)";
	private static final String aquaticChronic4 = "Aquatic Chronic 4 (H413)";
	
	
	protected static void correctChronicAquaticComponentHazards(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(aquaticChronic1)) {
			
			if(list.contains(aquaticChronic2)) {
				list.remove(aquaticChronic2);
			}
			if(list.contains(aquaticChronic3)) {
				list.remove(aquaticChronic3);
			}
			if(list.contains(aquaticChronic4)) {
				list.remove(aquaticChronic4);
			}
		}
		else if (list.contains(aquaticChronic2)) {
			
			if(list.contains(aquaticChronic3)) {
				list.remove(aquaticChronic3);
			}
			if(list.contains(aquaticChronic4)) {
				list.remove(aquaticChronic4);
			}
		}
		else if (list.contains(aquaticChronic3)) {
			
			if(list.contains(aquaticChronic4)) {
				list.remove(aquaticChronic4);
			}
		}
		
	}
	
	
	
	
}
