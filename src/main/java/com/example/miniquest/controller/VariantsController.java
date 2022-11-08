package com.example.miniquest.controller;

import com.example.miniquest.model.Question;
import com.example.miniquest.model.Variants;
import com.example.miniquest.service.VariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/variant")
@PreAuthorize("hasAuthority('ADMIN')")
public class VariantsController {
    @Autowired
    private VariantsService variantsService;

    @GetMapping("/{id}/create")
    public String createVariant(@PathVariable Long id, Model model) {
        model = variantsService.createVariant(id, model);
        return "create-variant";
    }

    @PostMapping("/create")
    public String addVariant(@ModelAttribute("variant") Variants variant,
                             @ModelAttribute("question") Question question,
                             Model model) {
        Optional<Variants> variantsFromBD = variantsService.findVariant(variant, question);
        if (variantsFromBD.isPresent()) {
            model.addAttribute("message", "Variant exists!");
            return "create-variant";
        }
        model = variantsService.addVariant(variant, question, model);
        return "variants";
    }

    @GetMapping("/{id}/edit")
    public String listVariantEdit(@PathVariable Long id, Model model) {
        model = variantsService.listVariantEdit(id, model);
        return "/edit-variant";
    }

    @RequestMapping("/{id}")
    public String patchVariant(@PathVariable Long id,
                               @ModelAttribute("variant") Variants variant,
                               Model model) {
        model = variantsService.patchVariant(id, variant, model);
        return "/variants";
    }

    @RequestMapping("/{id}/delete")
    public String deleteVariant(@PathVariable Long id, Model model) {
        model = variantsService.deleteVariant(id, model);
        return "variants";
    }
}
