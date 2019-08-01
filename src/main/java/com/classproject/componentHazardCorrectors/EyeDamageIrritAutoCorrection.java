package com.classproject.componentHazardCorrectors;

import java.util.List;

import org.springframework.stereotype.Component;
import com.classproject.domain.MixtureComponent;

@Component
public class EyeDamageIrritAutoCorrection {
	
	private static final String eyeDamage1 = "Eye Dam. 1 (H318)";
	private static final String eyeIrrit2 = "Eye Irrit. 2 (H319)";
	
	protected static void correctEyeDamageIrritComponentHazards(MixtureComponent component) {

		List<String> list = component.getHazards();

		if (list.contains(eyeDamage1)) {

			if (list.contains(eyeIrrit2)) {
				list.remove(eyeIrrit2);
			}
		}
	}

}
