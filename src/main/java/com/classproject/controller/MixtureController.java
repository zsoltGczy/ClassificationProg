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
import com.classproject.liststoview.ListsToView;
import com.classproject.service.MixtureService;

@Controller
public class MixtureController {

	private MixtureService mixService;
	private ListsToView listsToView;
	
	@Autowired
	public void setMixService(MixtureService mixService) {
		this.mixService = mixService;
	}
	@Autowired
	public void setListsToView(ListsToView listsToView) {
		this.listsToView = listsToView;
	}
	
	
	
	
	@RequestMapping("/{mixtureId}/deletemixture")
	public String deleteMix(@PathVariable(value="mixtureId") Long mixtureId) {
		mixService.deleteMixture(mixService.getSpecificMixture(mixtureId));
		return "redirect:/allmix";
	}
		
	
	
	
	@RequestMapping("/{mixtureId}/updatemixture")
	public String updateMix(@PathVariable(value="mixtureId") Long mixtureId, Model model) {
		
		model.addAttribute("mixture", mixService.getSpecificMixture(mixtureId));
		model.addAttribute("selectablePhysicalSt", listsToView.addPhysicalStatesToView(new ArrayList<>()));
		return "updatemix";
	}
			@RequestMapping(value="/updatem", method=RequestMethod.POST, params="Add=update")
			public String updateMix(@Valid @ModelAttribute Mixture mix, BindingResult result, Model model) {
				
				model.addAttribute("selectablePhysicalSt", listsToView.addPhysicalStatesToView(new ArrayList<>()));
				
				if (result.hasErrors()) {
					 return "updatemix";
				}
				
				mixService.mixtureSaveOrUpdate(mix);
				return "redirect:/" + mix.getMixtureId();
			}
			@RequestMapping(value="/updatem", method=RequestMethod.POST, params="Cancel=cancel")
			public String cancelUpdateMix(@ModelAttribute Mixture mix) {
				
				return "redirect:/" + mix.getMixtureId();
			}
			
			
	
	
	@RequestMapping("/addmix")
	public String addMix(Model model) {
		
		model.addAttribute("mixture", new Mixture());
		model.addAttribute("selectablePhysicalSt", listsToView.addPhysicalStatesToView(new ArrayList<>()));
		return "addmix";
	}
			@RequestMapping(value="/addmix", method=RequestMethod.POST, params="Add=add")
			public String addMix(@Valid @ModelAttribute Mixture mix, BindingResult result, Model model) {
				
				model.addAttribute("selectablePhysicalSt", listsToView.addPhysicalStatesToView(new ArrayList<>()));
				
				 if (result.hasErrors()) {
					 return "addmix";
				 }
				 
				mixService.mixtureSaveOrUpdate(mix);
				return "redirect:/allmix";
			}
			@RequestMapping(value="/addmix", method=RequestMethod.POST, params="Cancel=cancel")
			public String cancelAddMix(@ModelAttribute Mixture mix) {
				
				return "redirect:/allmix";
			}
		
			
			
			
	@RequestMapping("/{mixtureId}")
	public String findOneMix(@PathVariable(value="mixtureId") Long mixtureId, Model model) throws Exception {
		
		model.addAttribute("mixture", mixService.getSpecificMixture(mixtureId));
		return "specificmix";
	}

	
	@RequestMapping("/allmix")
	public String allMix(Model model){
		model.addAttribute("allmixtures", mixService.getMixtures());
		return "allmix";
	}
	
	
	@RequestMapping("/")
	public String index(Model model){
		return "index";
	}
	
}
