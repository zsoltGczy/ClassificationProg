package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class SkinSensAutoCorrection {
	
	private static final String skinSens1 = "Skin. Sens. 1 (H317)";
	private static final String skinSens1A = "Skin. Sens. 1A (H317)";
	private static final String skinSens1B = "Skin. Sens. 1B (H317)";
	

	protected static void correctSkinSens(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(skinSens1A)) {
			
			if(list.contains(skinSens1B)) {
				list.remove(skinSens1B);
			}
			if(list.contains(skinSens1)) {
				list.remove(skinSens1);
			}
		}
		else if (list.contains(skinSens1B)) {
			
			if(list.contains(skinSens1)) {
				list.remove(skinSens1);
			}
		}
	}
	
	
	
	
}
