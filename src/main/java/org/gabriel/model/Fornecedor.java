package org.gabriel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Fornecedor implements ValueObject {

    @Id @Getter @GeneratedValue(strategy = GenerationType.SEQUENCE) private Integer codigo;
    @Getter @Setter @NonNull private String razaoSocial;
    @Getter @Setter @ManyToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<Produto> produtos;
}
