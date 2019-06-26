package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class StotREAutoCorrection {
	
	private final String stotRE1 = "STOT RE 1 (H372)";
	private final String stotRE2 = "STOT RE 2 (H373)";
	

	protected void correctStotRE(MixtureComponent component) {

		List<String> list = component.getHazards();

		if (list.contains(stotRE1)) {

			if (list.contains(stotRE2)) {
				list.remove(stotRE2);
			}
		}
	}
	
}
