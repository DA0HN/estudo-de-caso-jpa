package org.gabriel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daohn on 30/07/2020
 * @project ExercicioMapeamentoJPA
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Venda implements ValueObject {
    @Id @Getter @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codigo;
    @NonNull @Getter @Setter
    private LocalDate dataVenda;

    @Getter @Setter @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @Getter @Setter @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Getter @Setter @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itensVenda = new ArrayList<>();

    public double getValorTotal() {
        return getItensVenda().stream().mapToDouble(ItemVenda::getTotalVenda).sum();
    }

    public double getValorComissao() {
        return getValorTotal() * vendedor.getPerComissao() / 100;
    }
}
