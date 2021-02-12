package com.webimport.main.model.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="tb_campanha")
public class Campanha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Integer qtdTelefone;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "tb_campanha_telefone",
            joinColumns = {@JoinColumn(name = "telefone_id")},
            inverseJoinColumns = {@JoinColumn(name = "campanha_id")}
    )
//    @JoinTable(
//            name = "TB_CAMPANHA_ID",
//            joinColumns = {@JoinColumn(name = "campanha_id")},
//            inverseJoinColumns = {@JoinColumn(name = "telefone_id")}
//    )
    private List<Telefone> telefone = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "tb_campanha_campos",
            joinColumns = {@JoinColumn(name = "campo_id")},
            inverseJoinColumns = {@JoinColumn(name = "campanha_id")}
    )
    private List<Campo> campos = new ArrayList<>();


    public Campanha(){

    }

    public Campanha(Long id, String nome, Integer qtdTelefone, List<Telefone> telefone, List<Campo> campos) {
        this.id = id;
        this.nome = nome;
        this.qtdTelefone = qtdTelefone;
        this.telefone = telefone;
        this.campos = campos;
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

    public Integer getQtdTelefone() {
        return qtdTelefone;
    }

    public void setQtdTelefone(Integer qtdTelefone) {
        this.qtdTelefone = qtdTelefone;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return Objects.equals(id, campanha.id) && Objects.equals(nome, campanha.nome) && Objects.equals(qtdTelefone, campanha.qtdTelefone) && Objects.equals(telefone, campanha.telefone) && Objects.equals(campos, campanha.campos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, qtdTelefone, telefone, campos);
    }
}
