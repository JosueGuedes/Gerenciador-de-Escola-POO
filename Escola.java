package Person;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Escola {
    String nome_escola;
    HashMap<String, Turma> turmas_registradas = new HashMap<String, Turma>();
    HashMap<String, Pessoa> alunos_registrados = new HashMap<String, Pessoa>();
    public HashMap<String, Pessoa> professores_registrados = new HashMap<String, Pessoa>();
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

    public void criar_aluno(String nome, String materia){
        Pessoa aluno_add = new Pessoa(nome, "Aluno");
        alunos_registrados.put(aluno_add.codigo, aluno_add);
    }

    public void criar_professor(String nome){
        Pessoa discente_registrado = new Pessoa(nome, "Professor");
        professores_registrados.put(discente_registrado.codigo, discente_registrado);
    }

    public void salvar(Escola school){
        try {
            FileOutputStream fos = new FileOutputStream("src/Person/dados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(school); oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException r){
            System.out.println("Erro encontrado: " + r);
        }
    }

    public Escola carregar(){
        Escola escola_carregada = new Escola();
        try {
            FileInputStream fos = new FileInputStream("src/Person/dados.txt");
            ObjectInputStream oos = new ObjectInputStream(fos);
            escola_carregada = (Escola) oos.readObject();
            oos.close();
            return escola_carregada;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException r){
            System.out.println("Erro encontrado: " + r);
        } catch (ClassNotFoundException s){
            System.out.println("to bluelockado");
        }
        return escola_carregada;
    }

    public int check_numProfessores(){
        return this.professores_registrados.size();
    }

    public int check_numTurmas(){
        return this.turmas_registradas.size();
    }

}
