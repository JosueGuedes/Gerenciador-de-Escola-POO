package Person;
import java.util.HashMap;

public class Turma {
    public String codigo_turma, materia;
    static int base = 1000;
    Pessoa discente;
    public HashMap<String, Pessoa> lista_de_alunos = new HashMap<String, Pessoa>(); 
    public HashMap<String, Float> notas = new HashMap<String, Float>(); // A string em questão é codigo do aluno em ambos os casos

    public Turma(String materia, Pessoa professor){
        this.codigo_turma = Integer.toString(base);
        base += 1;
        this.materia = materia;
        this.discente = professor;
    }

    public void adicionar_aluno(String codigo_aluno, Pessoa registrado){
        lista_de_alunos.put(codigo_aluno, registrado);
         // Um aluno so precisa ter seu nome informado, visto que seu codigo é feito automaticamente
        // E sua ocupação é implicita
    }

    public void excluir_aluno(String codigo_do_excluid){
        lista_de_alunos.remove(codigo_do_excluid);
    }

    public void get_data(){
        System.out.println("Cod. turma: " + codigo_turma + "\nProfessor: " + discente.nome + "\nMatéria: " + materia);
        System.out.println("Lista de alunos: ");

        for (Pessoa estudantes : lista_de_alunos.values()){ // Vai buscar na lista de alunos (que é da classe Pessoa) 
            System.out.println("\t"+estudantes.nome+"\tCod: "+estudantes.codigo);
        }
        System.out.println();
    }

    public void atribuir_nota(Pessoa aluno, Float nota){
        notas.put(aluno.getCode(), nota);
    }

    public void mostrar_notas(){
        for (Pessoa estudantes : lista_de_alunos.values()){
            System.out.println("Aluno: "+ estudantes.nome + "\tNota: " + notas.get(estudantes.codigo));
        }
    }
}
