package com.orcamento.controller;

import com.orcamento.model.Empresa;
import com.orcamento.model.Orcamento;
import com.orcamento.service.ConfigService;
import com.orcamento.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrcamentoController {

    @Autowired
    private PdfService pdfService;
    @Autowired
    private ConfigService configService;

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("empresa", configService.carregarConfig());
        model.addAttribute("orcamento", new Orcamento());
        return "form";
    }

    @PostMapping("/gerar")
    public String gerar(@ModelAttribute("orcamento") Orcamento orcamento, Model model) throws Exception {
        Empresa empresa = configService.carregarConfig();
        String caminhoPdf = pdfService.gerarPdf(orcamento, empresa);

        model.addAttribute("empresa", empresa);
        model.addAttribute("orcamento", orcamento);
        model.addAttribute("pdfPath", caminhoPdf.replace("\\", "/"));

        return "resultado"; // redireciona para baixar pdf
    }
}
