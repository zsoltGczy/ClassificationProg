package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class RespSensAutoCorrection {
	
	private final String respSens1 = "Resp. Sens. 1 (H334)";
	private final String respSens1A = "Resp. Sens. 1A (H334)";
	private final String respSens1B = "Resp. Sens. 1B (H334)";
	

	protected void correctRespSens(MixtureComponent component) {
		
		List <String> list = component.getHazards();
		
		if(list.contains(respSens1A)) {
			
			if(list.contains(respSens1B)) {
				list.remove(respSens1B);
			}
			if(list.contains(respSens1)) {
				list.remove(respSens1);
			}
		}
		else if (list.contains(respSens1B)) {
			
			if(list.contains(respSens1)) {
				list.remove(respSens1);
			}
		}
	}
	
	
	
	
}
