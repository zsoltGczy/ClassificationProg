package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class CarcinogenicityAutoCorrection {
	
	private static final String carcinogenicity1A = "Carc. 1A (H350)";
	private static final String carcinogenicity1B = "Carc. 1B (H350)";
	private static final String carcinogenicity2 = "Carc. 2 (H351)";
	

	protected static void correctCarcinogenicity(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(carcinogenicity1A)) {
			
			if(list.contains(carcinogenicity1B)) {
				list.remove(carcinogenicity1B);
			}
			if(list.contains(carcinogenicity2)) {
				list.remove(carcinogenicity2);
			}
		}
		else if (list.contains(carcinogenicity1B)) {
			
			if(list.contains(carcinogenicity2)) {
				list.remove(carcinogenicity2);
			}
		}
	}
	
	
	
	
}
