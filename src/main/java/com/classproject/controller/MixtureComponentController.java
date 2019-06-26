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

import com.classproject.domain.Mixture;
import com.classproject.domain.MixtureComponent;
import com.classproject.liststoview.ListsToView;
import com.classproject.service.MixtureComponentService;
import com.classproject.service.MixtureService;

@Controller
public class MixtureComponentController {

	private MixtureComponentService compService;
	private MixtureService mixService;
	private ListsToView listsToView;
	
	
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
	
	
	
	
	@RequestMapping("/{mixtureId}/components/{componentId}/deletecomponent")
	public String deleteComp(@PathVariable(value="mixtureId") Long mixtureId, 
			@PathVariable(value = "componentId") Long componentId) {
		
		compService.deleteComponent(compService.getSpecificComponent(componentId));
		
		return "redirect:/" + mixtureId + "/components";
	}
	
	
	
	
	
	
	@RequestMapping("/{mixtureId}/components/{componentId}/updatecomp")
	public String updateComp(@PathVariable(value = "mixtureId") Long mixtureId, 
			@PathVariable(value = "componentId") Long componentId, Model model) {
		
		model.addAttribute("mixture", mixService.getSpecificMixture(mixtureId));
		model.addAttribute("mixtureComponent", compService.getSpecificComponent(componentId));
		model.addAttribute("selectableHazards", listsToView.addHazardNamesToView(new ArrayList<String>()));
		
		return "updatecomponents";
	}
				@RequestMapping(value="/updatecomponents", method=RequestMethod.POST, params="Add=update")
				public String updateComp(@Valid @ModelAttribute MixtureComponent comp, BindingResult result, @ModelAttribute Mixture mix, Model model) {
	
					model.addAttribute("selectableHazards", listsToView.addHazardNamesToView(new ArrayList<String>()));
					
					if (result.hasErrors()) {
						 return "updatecomponents";
					 }
					
					compService.componentSaveOrUpdate(comp);
					return "redirect:/" + mix.getMixtureId() + "/components";
				}
				
				@RequestMapping(value="/updatecomponents", method=RequestMethod.POST, params="Cancel=cancel")
				public String cancelUpdateComp(@ModelAttribute Mixture mix) {
					return "redirect:/" + mix.getMixtureId() + "/components";
				}
		
				
			
						
	
	@RequestMapping("/{mixtureId}/addcomponents")
	public String addComp(@PathVariable(value = "mixtureId") Long mixtureId, Model model) {
		
		Mixture specificMixture = mixService.getSpecificMixture(mixtureId);
		
		model.addAttribute("mixture", specificMixture);
		model.addAttribute("mixtureComponent", new MixtureComponent(specificMixture));
		model.addAttribute("selectableHazards", listsToView.addHazardNamesToView(new ArrayList<String>()));
		
		return "addcomponents";
	}
				@RequestMapping(value="/addcomponents", method=RequestMethod.POST, params="Add=add")
				public String addComp(@ModelAttribute Mixture mix, @Valid @ModelAttribute MixtureComponent comp, BindingResult result,  
						Model model) {
					
					model.addAttribute("selectableHazards", listsToView.addHazardNamesToView(new ArrayList<String>()));
					
					if (result.hasErrors()) {
						return "addcomponents";
					 }
					
					compService.componentSaveOrUpdate(comp);
					return "redirect:/" + mix.getMixtureId() + "/components";
				}
				
				@RequestMapping(value="/addcomponents", method=RequestMethod.POST, params="Cancel=cancel")
				public String cancelAddComp(@ModelAttribute Mixture mix) {
					return "redirect:/" + mix.getMixtureId() + "/components";
				}
				
				
					
	@RequestMapping("/{mixtureId}/components")
	public String findAllOfMixComponents(@PathVariable(value = "mixtureId") Long mixtureId, Model model) throws Exception {
		
		Mixture specificMixture = mixService.getSpecificMixture(mixtureId); 

		model.addAttribute("mixture", specificMixture);
		model.addAttribute("components", compService.getMixComponents(specificMixture));
		
		return "components";
	}
	
	
	
	
}
