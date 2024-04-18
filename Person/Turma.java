package Person;
import java.util.HashMap;
import java.util.Scanner;

public class Turma {
    String codigo_turma, materia;
    static int base = 1000;
    Pessoa discente;
    public HashMap<String, Pessoa> lista_de_alunos = new HashMap<String, Pessoa>(); 
    public HashMap<String, Float> notas = new HashMap<String, Float>(); // A string em questão é codigo do aluno em ambos os casos

    public Turma(String materia){
        this.codigo_turma = Integer.toString(base);
        base += 1;
        this.materia = materia;
        Scanner scan = new Scanner(System.in);
        System.out.println("Nome do discente: ");
        String nome = scan.nextLine();
        this.discente = new Pessoa(nome, "Professor");
        //scan.close();
    }

    public void adicionar_aluno(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Nome do aluno: ");
        String nome = scan.nextLine();
        Pessoa aluno_adicionado = new Pessoa(nome, "Aluno"); // Um aluno so precisa ter seu nome informado, visto que seu codigo é feito automaticamente
        // E sua ocupação é implicita
        lista_de_alunos.put(aluno_adicionado.codigo, aluno_adicionado);
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
