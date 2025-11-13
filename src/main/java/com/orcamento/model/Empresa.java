
package com.orcamento.model;

public class Empresa {
    private String nome;
    private String cnpj;
    private String tipoConta;
    private String agencia;
    private String numeroConta;
    private String caminhoLogo;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumeroConta() { return numeroConta; }
    public void setNumeroConta(String numeroConta) { this.numeroConta = numeroConta; }

    public String getCaminhoLogo() { return caminhoLogo; }
    public void setCaminhoLogo(String caminhoLogo) { this.caminhoLogo = caminhoLogo; }
}
