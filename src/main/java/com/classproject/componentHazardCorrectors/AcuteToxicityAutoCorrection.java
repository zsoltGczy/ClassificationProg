package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class AcuteToxicityAutoCorrection {
	
	private final String catOral1 = "Acute Tox. 1 (H300)";
	private final String catOral2 = "Acute Tox. 2 (H300)";
	private final String catOral3 = "Acute Tox. 3 (H301)";
	private final String catOral4 = "Acute Tox. 4 (H302)";
	
	private final String catDermal1 = "Acute Tox. 1 (H310)";
	private final String catDermal2 = "Acute Tox. 2 (H310)";
	private final String catDermal3 = "Acute Tox. 3 (H311)";
	private final String catDermal4 = "Acute Tox. 4 (H312)";
	
	private final String catInhalationGases1 = "Acute Tox. 1 (H330) - Gases";
	private final String catInhalationGases2 = "Acute Tox. 2 (H330) - Gases";
	private final String catInhalationGases3 = "Acute Tox. 3 (H331) - Gases";
	private final String catInhalationGases4 = "Acute Tox. 4 (H332) - Gases";
	
	private final String catInhalationVapours1 = "Acute Tox. 1 (H330) - Vapours";
	private final String catInhalationVapours2 = "Acute Tox. 2 (H330) - Vapours";
	private final String catInhalationVapours3 = "Acute Tox. 3 (H331) - Vapours";
	private final String catInhalationVapours4 = "Acute Tox. 4 (H332) - Vapours";

	private final String catInhalationDustMist1 = "Acute Tox. 1 (H330) - Dust/mist";
	private final String catInhalationDustMist2 = "Acute Tox. 2 (H330) - Dust/mist";
	private final String catInhalationDustMist3 = "Acute Tox. 3 (H331) - Dust/mist";
	private final String catInhalationDustMist4 = "Acute Tox. 4 (H332) - Dust/mist";

	protected void correctAcuteToxComponentHazards (MixtureComponent component) {
		acuteToxCorrector(component, catOral1, catOral2, catOral3, catOral4);
		acuteToxCorrector(component, catDermal1, catDermal2, catDermal3, catDermal4);
		acuteToxCorrector(component, catInhalationGases1, catInhalationGases2, catInhalationGases3, catInhalationGases4);
		acuteToxCorrector(component, catInhalationVapours1, catInhalationVapours2, catInhalationVapours3, catInhalationVapours4);
		acuteToxCorrector(component, catInhalationDustMist1, catInhalationDustMist2, catInhalationDustMist3, catInhalationDustMist4);
	}
	
	private void acuteToxCorrector(MixtureComponent component, String cat1, String cat2, String cat3, String cat4) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(cat1)) {
			if(list.contains(cat2)) {
				list.remove(cat2);
			}
			if(list.contains(cat3)) {
				list.remove(cat3);
			}
			if(list.contains(cat4)) {
				list.remove(cat4);
			}
		}
		else if (list.contains(cat2)) {
			if(list.contains(cat3)) {
				list.remove(cat3);
			}
			if(list.contains(cat4)) {
				list.remove(cat4);
			}
		}
		else if (list.contains(cat3)) {
			if(list.contains(cat4)) {
				list.remove(cat4);
			}
		}
		
	}
	
	
	
	
}
