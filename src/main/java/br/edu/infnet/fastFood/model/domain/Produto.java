package br.edu.infnet.fastFood.model.domain;

import br.edu.infnet.fastFood.exceptions.ComidaException;
import br.edu.infnet.fastFood.exceptions.PedidoException;
import br.edu.infnet.fastFood.exceptions.ProdutoException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        //include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sobremesa.class, name = "sobremesa"),
        @JsonSubTypes.Type(value = Bebida.class, name = "bebida"),
        @JsonSubTypes.Type(value = Comida.class, name = "comida")
})
public abstract class Produto {

    private String nome;

    private float valor;

    private int codigo;

    public Produto(String nome, float valor, int codigo) {
        this.nome = nome;
        this.codigo = codigo;

        try {
            setValor(valor);
        } catch (ProdutoException e) {
            System.out.println("Erro ao configurar o valor do produto: " + e.getMessage());
        }
    }

    public abstract void preparar();
    public abstract String toString();

    public void setValor(float valor) throws ProdutoException {
        if (valor <= 0) {
            throw new ProdutoException("O valor do Produto deve ser maior que zero.");
        }
        this.valor = valor;
    }

}
