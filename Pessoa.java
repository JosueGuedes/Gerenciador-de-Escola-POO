package Person;

import java.util.ArrayList;

public class Pessoa {
    String nome, codigo, ocupacao;
    ArrayList<String> materias = new ArrayList<String>();
    static int base_codigo = 100;
    // O codigo é sempre uma string de tamanho 5, com 1 digito + 4 numeros.
    // P pra professor, A pra aluno e T pra turma

    public Pessoa(String nome, String ocupacao){
        this.nome = nome;
        this.ocupacao = ocupacao; // Deve ser sempre professor ou aluno, sempre.
        if (ocupacao.equals("Aluno")){
            this.codigo = "A" + Integer.toString(base_codigo);
        }
        else if (ocupacao.equals("Professor")){
            this.codigo = "P" + Integer.toString(base_codigo);
        }
        base_codigo += 1;
    }

    public String getName(){
        return nome;
    }

    public String getCode(){
        return codigo;
    }

    public ArrayList<String> get_materias(){
        return this.materias;
    }
}