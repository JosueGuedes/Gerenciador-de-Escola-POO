package Person;
import java.util.Scanner;
import java.util.HashMap;

public class Escola {
    String nome_escola;
    HashMap<String, Turma> turmas_registradas = new HashMap<String, Turma>();
    HashMap<String, Pessoa> alunos_registrados = new HashMap<String, Pessoa>(); 
    // A string nos casos acima são o código da turma e do aluno, respectivamente
    
    public void criar_turma(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual a matéria lecionadada? ");
        Turma turma_criada = new Turma(scan.nextLine());
        turmas_registradas.put(turma_criada.codigo_turma, turma_criada);
        scan.close();
        System.out.println("Cod. Turma: " + turma_criada.codigo_turma);
        System.out.println("Professor: " + turma_criada.discente);
        System.out.println("Matéria: " + turma_criada.materia);
    }

    public void criar_aluno(){

    }
}
