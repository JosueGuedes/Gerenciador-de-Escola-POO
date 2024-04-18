package Person;

import java.util.ArrayList;

public class Pessoa {
    String nome, codigo, ocupacao;
    ArrayList<String> codigos_turmas = new ArrayList<String>(); // Arraylist com os códigos das turmas
    // codigos_turmas.put(chave, entrada).notas.put(codigo)

    static int base_professor = 2000, base_codigo_aluno = 3000;
    /**
     Faixa dos 1000: Turma
     Faixa dos 2000: Professor
     Faixa dos 3000: Aluno
     */

    public Pessoa(String nome, String ocupacao){
        this.nome = nome;
        this.ocupacao = ocupacao; // Deve ser sempre professor ou aluno, sempre.
        if (ocupacao.equals("Aluno")){
            this.codigo = Integer.toString(base_codigo_aluno);
            base_codigo_aluno += 1;
        }
        else if (ocupacao.equals("Professor")){
            this.codigo = Integer.toString(base_professor);
            base_professor += 1;
        }
    }

    public String getName(){
        return nome;
    }

    public void setName(String novo_nome){
        this.nome = novo_nome;
    }

    public String getCode(){
        return codigo;
    }

    public ArrayList<String> get_materias(){
        return this.codigos_turmas;
    }

    public void mostrar_notas_por_materia(Escola school){
        System.out.println("MATERIA\tCÓDIGO\tNOTA:");

        for (String item : codigos_turmas){
            System.out.println(school.turmas_registradas.get(item).notas.get(codigo)); // Acessa as turmas da escola com o código da turma
            // Daí acessa uma nota usando o código daquele aluno
        }
    }

    public void adicionar_turma(String codigo_turma){ // Método que vai ser invocado na criação de uma turma
        codigos_turmas.add(codigo_turma); 
    }
}
