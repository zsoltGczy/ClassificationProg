package com.classproject.liststoview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ListsToView {
	
	private List<String> acuteTox = new ArrayList<>();
	private List<String> aquaticHazards = new ArrayList<>();
	
	
	
	public List<String> addHazardNamesToView (List<String> list) {
		
		list.clear();
		acuteTox.clear();
		aquaticHazards.clear();


		// Acute toxicity
		acuteTox.add("Acute Tox. 1 (H300)");
		acuteTox.add("Acute Tox. 2 (H300)");
		acuteTox.add("Acute Tox. 3 (H301)");
		acuteTox.add("Acute Tox. 4 (H302)");	
		
		acuteTox.add("Acute Tox. 1 (H310)");
		acuteTox.add("Acute Tox. 2 (H310)");
		acuteTox.add("Acute Tox. 3 (H311)");
		acuteTox.add("Acute Tox. 4 (H312)");
		
		acuteTox.add("Acute Tox. 1 (H330) - Gases");
		acuteTox.add("Acute Tox. 2 (H330) - Gases");
		acuteTox.add("Acute Tox. 3 (H331) - Gases");
		acuteTox.add("Acute Tox. 4 (H332) - Gases");
		
		acuteTox.add("Acute Tox. 1 (H330) - Vapours");
		acuteTox.add("Acute Tox. 2 (H330) - Vapours");
		acuteTox.add("Acute Tox. 3 (H331) - Vapours");
		acuteTox.add("Acute Tox. 4 (H332) - Vapours");
		
		acuteTox.add("Acute Tox. 1 (H330) - Dust/mist");
		acuteTox.add("Acute Tox. 2 (H330) - Dust/mist");
		acuteTox.add("Acute Tox. 3 (H331) - Dust/mist");
		acuteTox.add("Acute Tox. 4 (H332) - Dust/mist");
		
		list.addAll(acuteTox);
		
		// Skin corrosion/irritation
		list.add("Skin Corr. 1 (H314)");
		list.add("Skin Corr. 1A (H314)");
		list.add("Skin Corr. 1B (H314)");
		list.add("Skin Corr. 1C (H314)");
		
		list.add("Skin Irrit. 2 (H315)");
		
		// Serious eye damage/eye irritation
		list.add("Eye Dam. 1 (H318)");
		
		list.add("Eye Irrit. 2 (H319)");
		
		// Respiratory/skin sensitization
		list.add("Resp. Sens. 1 (H334)");
		list.add("Resp. Sens. 1A (H334)");
		list.add("Resp. Sens. 1B (H334)");
		
		list.add("Skin. Sens. 1 (H317)");
		list.add("Skin. Sens. 1A (H317)");	
		list.add("Skin. Sens. 1B (H317)");
		
		//Germ cell mutagenicity
		list.add("Muta. 1A (H340)");
		list.add("Muta. 1B (H340)");
		
		list.add("Muta. 2 (H341)");
		
		//Carcinogenicity
		list.add("Carc. 1A (H350)");
		list.add("Carc. 1B (H350)");
			
		list.add("Carc. 2 (H351)");
		
		//Reproductive toxicity
		list.add("Repr. 1A (H360)");
		list.add("Repr. 1B (H360)");
					
		list.add("Repr. 2 (H361)");
		
		list.add("Lact. (H362)");
		
		//Specific target organ toxicity — single exposure
		list.add("STOT SE 1 (H370)");
		list.add("STOT SE 2 (H371)");
		list.add("STOT SE 3 (H335)");
		list.add("STOT SE 3 (H336)");
		
		//Specific target organ toxicity — repeated exposure
		list.add("STOT RE 1 (H372)");
		list.add("STOT RE 2 (H373)");
		
		//Aspiration hazard
		list.add("Asp. Tox. 1 (H304)");
		
		//Hazardous to the aquatic environment
		aquaticHazards.add("Aquatic Acute 1 (H400)");
		
		aquaticHazards.add("Aquatic Chronic 1 (H410)");
		aquaticHazards.add("Aquatic Chronic 2 (H411)");
		aquaticHazards.add("Aquatic Chronic 3 (H412)");
		aquaticHazards.add("Aquatic Chronic 4 (H413)");
		
		list.addAll(aquaticHazards);
		
		//Hazardous to the ozone layer
		list.add("Ozone 1 (H420)");
		
		list.add("Not classified");
		
		
		return list;
	}
	
	public List<String> addSpecificConcLimitsToView (List<String> list){
		
		List<String> spec = addHazardNamesToView (list);
		
		
		spec.removeAll(acuteTox);
		spec.removeAll(aquaticHazards);
		spec.remove("Asp. Tox. 1 (H304)");
		spec.remove("Not classified");
		
		spec.add("oral: ATE");
		spec.add("dermal: ATE");
		spec.add("inhalation: ATE");
		spec.add("M-factor Aquatic Acute");
		spec.add("M-factor Aquatic Chronic");
		
		return spec;
	}


	
	public List<String> addPhysicalStatesToView (List<String> list) {
		
		list.add("gas");
		list.add("liquid, solid");
		
		return list;
	}

	
}
