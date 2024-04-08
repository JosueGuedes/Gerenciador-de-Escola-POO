package Person;
import java.util.HashMap;
import java.util.Scanner;

public class Turma {
    String codigo_turma, materia;
    static int base = 100;
    Pessoa discente;
    HashMap<String, Pessoa> lista_de_alunos = new HashMap<String, Pessoa>(); // A string em questão é codigo do aluno
    HashMap<Pessoa, Float> notas = new HashMap<Pessoa, Float>();

    public Turma(String materia){
        this.codigo_turma = "T" + Integer.toString(base);
        base += 10;
        this.materia = materia;
        Scanner scan = new Scanner(System.in);
        System.out.println("Nome do discente: ");
        // String nome = scan.nextLine();
        this.discente = new Pessoa(scan.nextLine(), "Professor");
        scan.close();
    }

    public void adicionar_aluno(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Nome do aluno: ");
        String nome = scan.nextLine();
        Pessoa aluno_adicionado = new Pessoa(nome, "Aluno"); // Um aluno so precisa ter seu nome informado, visto que seu codigo é feito automaticamente
        // E sua ocupação é implicita
        lista_de_alunos.put(aluno_adicionado.codigo, aluno_adicionado);

        scan.close();
    }

    public void excluir_aluno(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Codigo do aluno: ");
        String codigo_do_excluido = scan.nextLine();
        lista_de_alunos.remove(codigo_do_excluido);
        scan.close();
    }
}
