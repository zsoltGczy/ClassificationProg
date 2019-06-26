package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class SkinCorrIrritAutoCorrection {
	
	private final String skinCorr1A = "Skin Corr. 1A (H314)";
	private final String skinCorr1B = "Skin Corr. 1B (H314)";
	private final String skinCorr1C = "Skin Corr. 1C (H314)";
	private final String skinCorr1 = "Skin Corr. 1 (H314)";
	private final String skinIrrit = "Skin Irrit. 2 (H315)";
	
	
	protected void correctSkinCorrIrritComponentHazards(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(skinCorr1A)) {
			
			if(list.contains(skinCorr1B)) {
				list.remove(skinCorr1B);
			}
			if(list.contains(skinCorr1C)) {
				list.remove(skinCorr1C);
			}
			if(list.contains(skinCorr1)) {
				list.remove(skinCorr1);
			}
			if(list.contains(skinIrrit)) {
				list.remove(skinIrrit);
			}
		}
		else if (list.contains(skinCorr1B)) {
			
			if(list.contains(skinCorr1C)) {
				list.remove(skinCorr1C);
			}
			if(list.contains(skinCorr1)) {
				list.remove(skinCorr1);
			}
			if(list.contains(skinIrrit)) {
				list.remove(skinIrrit);
			}
		}
		else if (list.contains(skinCorr1C)) {
			
			if(list.contains(skinCorr1)) {
				list.remove(skinCorr1);
			}
			if(list.contains(skinIrrit)) {
				list.remove(skinIrrit);
			}
		}
		else if (list.contains(skinCorr1)) {
			
			if(list.contains(skinIrrit)) {
				list.remove(skinIrrit);
			}
		}
		
	}
	
	
	
	
}
