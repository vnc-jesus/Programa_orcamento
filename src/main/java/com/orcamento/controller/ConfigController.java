
package com.orcamento.controller;

import com.orcamento.model.Empresa;
import com.orcamento.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("empresa", configService.carregarConfig());
        return "config";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Empresa empresa,
                         @RequestParam(value = "logoFile", required = false) MultipartFile logo) throws Exception {
        configService.salvarConfig(empresa, logo);
        return "redirect:/?configSalva";
    }
}
