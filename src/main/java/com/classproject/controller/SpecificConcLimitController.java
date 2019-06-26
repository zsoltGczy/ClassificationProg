package com.classproject.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.classproject.domain.MixtureComponent;
import com.classproject.domain.SpecificConcLimit;
import com.classproject.liststoview.ListsToView;
import com.classproject.service.MixtureComponentService;
import com.classproject.service.MixtureService;
import com.classproject.service.SpecificConcLimitsService;

@Controller
public class SpecificConcLimitController {

	private MixtureComponentService compService;
	private MixtureService mixService;
	private ListsToView listsToView;
	private SpecificConcLimitsService specService;
	
	
	@Autowired
	public void setCompService(MixtureComponentService compService) {
		this.compService = compService;
	}
	@Autowired
	public void setMixService(MixtureService mixService) {
		this.mixService = mixService;
	}
	@Autowired
	public void setListsToView(ListsToView listsToView) {
		this.listsToView = listsToView;
	}
	@Autowired
	public void setSpecService(SpecificConcLimitsService specService) {
		this.specService = specService;
	}
	
	
	
	
	@RequestMapping("/{mixtureId}/components/{componentId}/spec/{specId}/deletespec")
	public String deleteSpec(@PathVariable(value="mixtureId") Long mixtureId, 
			@PathVariable(value = "componentId") Long componentId, @PathVariable(value = "specId") Long specId) {
		
		specService.deleteSpecificConcLimit(specService.getActualSpecificConcLimit(specId));
		return "redirect:/" + mixtureId + "/components/" + componentId + "/specs";
		
	}
	
	
	
	
	
			
	@RequestMapping("/{mixtureId}/components/{componentId}/spec/{specId}/updatespec")
	public String updateSpecificConclimits(@PathVariable(value = "specId") Long specId, Model model) {

		model.addAttribute("selectableSpec", listsToView.addSpecificConcLimitsToView(new ArrayList<String>()));
		model.addAttribute("specificConcLimit", specService.getActualSpecificConcLimit(specId));

		return "updatespecs";
	}
				@RequestMapping(value="/updatespecs", method=RequestMethod.POST, params="Add=update")
				public String updateSpecificConclimits(@ModelAttribute MixtureComponent comp, 
						@Valid @ModelAttribute SpecificConcLimit spec, BindingResult result, Model model) {

					model.addAttribute("selectableSpec", listsToView.addSpecificConcLimitsToView(new ArrayList<String>()));
					
					if (result.hasErrors()) {
						 return "updatespecs";
					}
					
					specService.specificConcLimitSaveOrUpdate(spec);
					return "redirect:/" + comp.getMixture().getMixtureId() + "/components/" + comp.getComponentId() + "/specs";	
				}

				@RequestMapping(value="/updatespecs", method=RequestMethod.POST, params="Cancel=cancel")
				public String cancelUpdateSpecificConclimits(@ModelAttribute MixtureComponent comp) {
					return "redirect:/" + comp.getMixture().getMixtureId() + "/components/" + comp.getComponentId() + "/specs";
				}		
				
				
				
				
				
				
	
		@RequestMapping("/{mixtureId}/components/{componentId}/spec")
		public String addSpecificConclimits(@PathVariable(value = "componentId") Long componentId, Model model) {
							
			model.addAttribute("selectableSpec", listsToView.addSpecificConcLimitsToView(new ArrayList<String>()));
			model.addAttribute("specificConcLimit", new SpecificConcLimit(compService.getSpecificComponent(componentId)));
					
			return "addspecs";
		}
					@RequestMapping(value="/addspecs", method=RequestMethod.POST, params="Add=add")
					public String addSpecificConclimits(@ModelAttribute MixtureComponent comp, 
							@Valid @ModelAttribute SpecificConcLimit spec, BindingResult result, Model model) {
						
						model.addAttribute("selectableSpec", listsToView.addSpecificConcLimitsToView(new ArrayList<String>()));
						
						if (result.hasErrors()) {
							 return "addspecs";
						}
						
						spec.setMixtureComponent(comp);
						specService.specificConcLimitSaveOrUpdate(spec);
						
						return "redirect:/" + comp.getMixture().getMixtureId() + "/components";	
					}
				
					@RequestMapping(value="/addspecs", method=RequestMethod.POST, params="Cancel=cancel")
					public String cancelAddSpecificConclimits(@ModelAttribute MixtureComponent comp) {
						return "redirect:/" + comp.getMixture().getMixtureId() + "/components";
					}	
				
	
					
					
	
	@RequestMapping("/{mixtureId}/components/{componentId}/specs")
	public String findAllOfComponentSpecs(@PathVariable(value = "mixtureId") Long mixtureId, 
			@PathVariable(value = "componentId") Long componentId, Model model) throws Exception {
						
		model.addAttribute("mixture", mixService.getSpecificMixture(mixtureId));
		model.addAttribute("mixtureComponent", compService.getSpecificComponent(componentId));
		model.addAttribute("specificConcLimits", specService.getComponentSpecificConcLimits(compService.getSpecificComponent(componentId)));
						
		return "specconclimits";
	}
					
	
}
