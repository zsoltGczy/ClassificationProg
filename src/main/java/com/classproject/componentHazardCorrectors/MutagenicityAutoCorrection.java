package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class MutagenicityAutoCorrection {
	
	private final String mutagenicity1A = "Muta. 1A (H340)";
	private final String mutagenicity1B = "Muta. 1B (H340)";
	private final String mutagenicity2 = "Muta. 2 (H341)";
	

	protected void correctMutagenicity(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(mutagenicity1A)) {
			
			if(list.contains(mutagenicity1B)) {
				list.remove(mutagenicity1B);
			}
			if(list.contains(mutagenicity2)) {
				list.remove(mutagenicity2);
			}
		}
		else if (list.contains(mutagenicity1B)) {
			
			if(list.contains(mutagenicity2)) {
				list.remove(mutagenicity2);
			}
		}
	}
	
	
	
	
}
