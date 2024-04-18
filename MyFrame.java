import javax.swing.*;
import Person.Escola;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    
    JButton botao_prof, botao_aluno, botao_turma, botao_submit_prof;
    Escola school;
    JTextField input_nome;

    public MyFrame(){
        tela_padrao();
    }

    public void tela_padrao(){
        this.school = new Escola();
        this.setSize(1020, 600);
        this.setTitle("Sistema Integrado de Gerenciamento Orientado a Objeto");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        botao_prof = new JButton();
        botao_prof.setText("Cadastrar professor");
        botao_prof.setFocusable(false);
        botao_prof.addActionListener(this);
        botao_prof.setBounds(30, 400, 300, 30);

        if (school.check_numProfessores() != 0){
            botao_turma = new JButton();
            botao_turma.setText("Cadastrar Turma");
            botao_turma.setFocusable(false);
            botao_turma.setBounds(30, 415, 50, 25);
            this.add(botao_turma);
        }
        if (school.check_numTurmas() != 0){
            botao_aluno = new JButton();
            botao_aluno.setText("Cadastrar aluno");
            botao_aluno.setFocusable(false);
            botao_aluno.setBounds(30, 430, 50, 25);
            this.add(botao_aluno);
        }

        this.add(botao_prof);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botao_prof){
            FrameCriacaoProfessor();
        }
        else if (e.getSource() == botao_aluno){

        }
        else if (e.getSource() == botao_submit_prof){
            school.criar_professor(input_nome.getText()); // Cria um professor
            System.out.println("Professor criado!");
            System.out.println(school.professores_registrados.get("2000"));
            tela_padrao();
        }
    }

    public void FrameCriacaoProfessor(){
        this.removeAll(); // Limpa tudo da tela

        JButton botao_submit = new JButton();
        botao_submit.setText("Enviar");
        botao_submit.setBounds(60, 50, 25, 30);
        botao_submit.addActionListener(this);

        JLabel texto_nome = new JLabel();
        texto_nome.setText("Nome: ");
        texto_nome.setBounds(35, 50, 15, 30);

        input_nome = new JTextField();
        input_nome.setPreferredSize(new Dimension(300, 30));
        input_nome.setBounds(50, 50, 300, 30);

        this.add(input_nome);
        this.add(texto_nome);
        this.setVisible(true);
    }
}
