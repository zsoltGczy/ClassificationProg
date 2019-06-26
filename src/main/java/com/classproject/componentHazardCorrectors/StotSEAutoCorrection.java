package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class StotSEAutoCorrection {
	
	private final String stotSE1 = "STOT SE 1 (H370)";
	private final String stotSE2 = "STOT SE 2 (H371)";
	private final String stotSE335 = "STOT SE 3 (H335)";
	private final String stotSE336 = "STOT SE 3 (H336)";
	

	protected void correctStotSE(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(stotSE1)) {
			
			if(list.contains(stotSE2)) {
				list.remove(stotSE2);
			}
			if(list.contains(stotSE335)) {
				list.remove(stotSE335);
			}
			if(list.contains(stotSE336)) {
				list.remove(stotSE336);
			}
		}
		else if (list.contains(stotSE2)) {
			
			if(list.contains(stotSE335)) {
				list.remove(stotSE335);
			}
			if(list.contains(stotSE336)) {
				list.remove(stotSE336);
			}
		}
	}
	
	
	
	
}
