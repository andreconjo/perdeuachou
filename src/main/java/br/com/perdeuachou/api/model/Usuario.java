package br.com.perdeuachou.api.model;


import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Usuario.class)
    private Long id;
    @NotNull
    @JsonView(Views.Usuario.class)
    private String nome;
    @Column(unique = true, nullable = false)
    @JsonView(Views.Usuario.class)
    private String email;
    @NotNull
    private String senha;
    @NotNull
    @Column(unique = true)
    @JsonView(Views.Usuario.class)
    private String telefone;
    @NotNull
    @Column(unique = true)
    @JsonView(Views.Usuario.class)
    private String cpfCpnj;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonView(Views.Usuario.class)
    private List<Pertence> pertences;
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private LocalDate dataCadastro;


    public Usuario() {
        this.dataCadastro = LocalDate.now();
    }

    public Usuario(Long id, @NotNull String nome, String email, @NotNull String senha, @NotNull String telefone, @NotNull String cpfCpnj, List<Pertence> pertences) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.cpfCpnj = cpfCpnj;
        this.pertences = pertences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpfCpnj() {
        return cpfCpnj;
    }

    public void setCpfCpnj(String cpfCpnj) {
        this.cpfCpnj = cpfCpnj;
    }

    public List<Pertence> getPertences() {
        return pertences;
    }

    public void setPertences(List<Pertence> pertences) {
        this.pertences = pertences;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
