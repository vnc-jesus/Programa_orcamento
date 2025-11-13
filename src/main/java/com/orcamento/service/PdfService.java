package com.orcamento.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.orcamento.model.Empresa;
import com.orcamento.model.Orcamento;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PdfService {

    public String gerarPdf(Orcamento orcamento, Empresa empresa) throws Exception {
        Path baseDir = Path.of("/var/data");
        Path saidaDir = baseDir.resolve("saida");

        Files.createDirectories(saidaDir);
        Files.createDirectories(baseDir.resolve("uploads"));


        String nomeArquivo = "orcamento_" + orcamento.getCliente().getNome().replaceAll("[^a-zA-Z0-9_-]", "_") + ".pdf";
        Path caminho = saidaDir.resolve(nomeArquivo);

        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        PdfWriter.getInstance(document, new FileOutputStream(caminho.toFile()));
        document.open();


        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
        Font texto = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Font negrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);


        if (empresa.getCaminhoLogo() != null) {
            try {
                Path logoPath = Path.of("/var/data/uploads/logo.png");
                if (Files.exists(logoPath)) {
                    Image logo = Image.getInstance(logoPath.toString());
                    logo.scaleToFit(100, 100);
                    logo.setAlignment(Element.ALIGN_LEFT);
                    document.add(logo);
                    addSpace(document, 10); // dá um respiro depois da logo
                }
            } catch (Exception e) {
                System.out.println("Logo não encontrada, seguindo sem imagem.");
            }
        }


        document.add(new Paragraph(empresa.getNome(), titulo));
        document.add(new Paragraph("CNPJ: " + safe(empresa.getCnpj()), texto));
        document.add(new Paragraph("Conta: " + safe(empresa.getTipoConta()) +
                " | Ag: " + safe(empresa.getAgencia()) +
                " | Nº: " + safe(empresa.getNumeroConta()), texto));
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        document.add(new Paragraph("Data: " + LocalDate.now().format(formato), texto));

        addSpace(document, 20);

        // --- LINHA DIVISÓRIA ---
        addDivider(document);


        document.add(new Paragraph("DADOS DO CLIENTE", subtitulo));
        addSpace(document, 10);
        document.add(new Paragraph("Nome: " + safe(orcamento.getCliente().getNome()), texto));
        document.add(new Paragraph("CPF/CNPJ: " + safe(orcamento.getCliente().getDocumento()), texto));
        document.add(new Paragraph("Endereço: " + safe(orcamento.getCliente().getEndereco()), texto));
        document.add(new Paragraph("Telefone: " + safe(orcamento.getCliente().getTelefone()), texto));
        addSpace(document, 25);


        addDivider(document);


        document.add(new Paragraph("DESCRIÇÃO DO SERVIÇO", subtitulo));
        addSpace(document, 10);
        document.add(new Paragraph(safe(orcamento.getDescricaoServico()), texto));
        addSpace(document, 25);


        addDivider(document);


        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        Paragraph valor = new Paragraph("VALOR DO SERVIÇO: " + nf.format(orcamento.getValor()), negrito);
        valor.setAlignment(Element.ALIGN_LEFT);
        document.add(valor);

        document.close();
        System.out.println("PDF gerado em: " + caminho.toAbsolutePath());
        return caminho.toString();
    }

    private String safe(String s) {
        return (s == null ? "" : s);
    }

    private void addSpace(Document document, float height) throws DocumentException {
        Paragraph space = new Paragraph(" ");
        space.setSpacingBefore(height);
        document.add(space);
    }

    private void addDivider(Document document) throws DocumentException {
        Paragraph line = new Paragraph(" ");
        line.setSpacingBefore(10);
        line.setSpacingAfter(10);
        line.add(new Chunk(new LineSeparator(0.5f, 100, Color.GRAY, Element.ALIGN_CENTER, -2)));
        document.add(line);
    }
}
