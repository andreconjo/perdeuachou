package br.com.perdeuachou.api.model.pertence;

import br.com.perdeuachou.api.model.Usuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Entity
public class Pertence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Image> images;
    private String descricao;
    private String categoria;
    private String perdidoEm;
    private Status status;
    private Locale data;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "entregue_usuario_id")
    private Usuario entregue;

    private LocalDate dataEntrega;
    private LocalDate dataCadastro;

    @Transient
    private int score;

    public Pertence() {
        this.dataCadastro = LocalDate.now();
    }

    public Pertence(Long id, List<Image> images, String descricao, String categoria,
                    String perdidoEm, Status status, Locale data, Tipo tipo, Usuario usuario,
                    Usuario entregue, LocalDate dataEntrega, int score) {
        this.id = id;
        this.images = images;
        this.descricao = descricao;
        this.categoria = categoria;
        this.perdidoEm = perdidoEm;
        this.status = status;
        this.data = data;
        this.tipo = tipo;
        this.usuario = usuario;
        this.entregue = entregue;
        this.dataEntrega = dataEntrega;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPerdidoEm() {
        return perdidoEm;
    }

    public void setPerdidoEm(String encontradoEm) {
        this.perdidoEm = encontradoEm;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Locale getData() {
        return data;
    }

    public void setData(Locale data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Usuario getEntregue() {
        return entregue;
    }

    public void setEntregue(Usuario entregue) {
        this.entregue = entregue;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Pertence{" +
                "id=" + id +
                ", images=" + images +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", perdidoEm='" + perdidoEm + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", tipo=" + tipo +
                ", usuario=" + usuario +
                ", entregue=" + entregue +
                ", dataEntrega=" + dataEntrega +
                ", dataCadastro=" + dataCadastro +
                ", score=" + score +
                '}';
    }
}
