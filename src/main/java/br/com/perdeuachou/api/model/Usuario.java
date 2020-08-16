package br.com.perdeuachou.api.model;


import br.com.perdeuachou.api.model.pertence.Pertence;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    private String telefone;
    @NotNull
    private String cpfCpnj;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pertence> pertences;


    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String telefone, String cpfCpnj, List<Pertence> pertences) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpfCpnj='" + cpfCpnj + '\'' +
                ", pertences=" + pertences +
                '}';
    }
}
