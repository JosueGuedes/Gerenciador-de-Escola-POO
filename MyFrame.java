
import javax.swing.*;
import Person.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame implements ActionListener, Runnable{
    // Lista demoniacamente grande de botões que são atributos pois eles precisam ser visiveis para serem usados no método actionPerfomed
    JButton botao_prof, botao_aluno, botao_turma,
            botao_submit_prof, botao_submit_turma, botao_submit_aluno,
            botao_info_turmas, botao_info_professores, botao_info_alunos,
            botao_edit_turma, btn_excluir_aluno, btn_atribuir_notas,
            botao_check_idv, btn_exclude, btn_send_grades,
            retornar_button;

    Escola school;
    JComboBox<String> lista_profs;
    ArrayList<JRadioButton> opcoes;
    HashMap<String, JTextField> notas;
    JTextField input_nome, input_code, input_excd;
    JMenuBar menuBar; JMenu arquivo;
    JMenuItem salvar, carregar;

    public MyFrame(){
    }
    
    public void run(){
        this.school = new Escola(); // Cria um objeto Escola quando inicia o programa pela primeira vez
        tela_padrao_criacao(); 
    }

    public void tela_padrao_criacao(){ // Método principal do programa, é a tela inicial do programa
        this.setSize(1020, 600);
        this.setTitle("Sistema Integrado de Gerenciamento Orientado a Objeto");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        botao_prof = new JButton(); // Botão que redireciona para a tela de criação de professor
        botao_prof.setText("Cadastrar professor");
        botao_prof.setFocusable(false);
        botao_prof.addActionListener(this);
        botao_prof.setBounds(30, 400, 300, 30);

        menuBar = new JMenuBar();   // Cria a barra de menu
          arquivo = new JMenu("Save/Load"); // Cria um item nessa barra
            salvar = new JMenuItem("Salvar"); // Cria o sub-item de arquivo
            salvar.addActionListener(this);

            carregar = new JMenuItem("Carregar"); // Cria o sub-item carregar de arquivo
            carregar.addActionListener(this);

          arquivo.add(salvar);
          arquivo.add(carregar);
          menuBar.add(arquivo);

        this.setJMenuBar(menuBar);

        if (school.check_numProfessores() != 0){ // Checa o numero de professores, se for diferente de 0, podem começar a ser feitas turmas
            botao_turma = new JButton();
            botao_turma.setText("Cadastrar Turma");
            botao_turma.setFocusable(false);
            botao_turma.setBounds(30, 450, 300, 30);
            botao_turma.addActionListener(this);

            botao_info_professores = new JButton(); // E como há mais de um professor, é possível ver a tela com os professores e o botão para acessar o perfil
            botao_info_professores.setText("Ver Professores");  // individual de um professor
            botao_info_professores.setFocusable(false);
            botao_info_professores.addActionListener(this);
            botao_info_professores.setBounds(360, 400, 300, 30);

            this.add(botao_turma);
            this.add(botao_info_professores);
       }
        if (school.check_numTurmas() != 0){ // Checa o numero de turmas, se for diferente de 0, pode-se fazer alunos
            botao_aluno = new JButton();
            botao_aluno.setText("Cadastrar aluno");
            botao_aluno.setFocusable(false);
            botao_aluno.setBounds(30, 500, 300, 30);
            botao_aluno.addActionListener(this);

            botao_info_turmas = new JButton();
            botao_info_turmas.setText("Administrar turmas"); // Cria o botão que redireciona para a tela de administração de turmas
            botao_info_turmas.setFocusable(false);  // Afinal, não há como administrar uma turma sem alunos
            botao_info_turmas.setBounds(360, 450, 300, 30);
            botao_info_turmas.addActionListener(this);

            botao_info_alunos = new JButton(); // Botão que redireciona para a tela que mostra todos os alunos, a qual pode redirecionar para o perfil individual do aluno
            botao_info_alunos.setText("Ver alunos");
            botao_info_alunos.setBounds(360, 500, 300, 30);
            botao_info_alunos.addActionListener(this);

            this.add(botao_info_turmas);
            this.add(botao_info_alunos);
            this.add(botao_aluno);
        }

        this.add(botao_prof);
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clean();     // Limpa a tela
        System.out.println("Mukagen");                        
        if (e.getSource() == botao_prof)
            FrameCriacaoProfessor(); // Redireciona para a tela de criacao de professor
        
        else if (e.getSource() == botao_aluno)
            FrameCriacaoAluno(); // Redireciona para a tela de criação de aluno
        
        else if (e.getSource() == botao_turma)
            FrameCriacaoTurma();   // Redireciona para a tela de criação de turmas
        
        else if (e.getSource() == botao_submit_prof){ // Envia as informações de FrameCriacaoProfessor para o objeto Escola
            school.criar_professor(input_nome.getText()); // Cria um professor
            System.out.println("Professor criado!");
            tela_padrao_criacao(); // Retorno à tela principal
        }
        else if (e.getSource() == botao_submit_turma){ // Cria uma turma no obj Escola com as informações dadas em FrameCriacaoTurma
            school.criar_turma(input_nome.getText(), school.professores_registrados.get(lista_profs.getSelectedItem())); /* Passa os parametros nome da materia (informado em um JTextField)
            E passa um parametro Pessoa, que é um professor selecionado por código em um JComboBox */
            tela_padrao_criacao();
        }
        else if (e.getSource() == botao_submit_aluno){
            ArrayList<String> turmas_selec = new ArrayList<String>(); // Array que será preenchido com codigos
            for (JRadioButton botao : opcoes){ // Varre o array de JRadioButtons para ver quais estão selecionados ou não
                if (botao.isSelected()){
                    System.out.println("Botão " + botao.getText() + "selecionado");
                    turmas_selec.add(botao.getText()); // Adicionado ao array das turmas selecionados
                }
            }
            school.criar_aluno(input_nome.getText(), turmas_selec); //Passa os parametros de nome do aluno (String) e um array de codigos de turma
            tela_padrao_criacao();
        }

        else if (e.getSource() == botao_info_professores)
            FrameMostrarProfessores();  // Redireciona para 
        else if (e.getSource() == botao_info_alunos)
            FrameMostrarAlunos();   // Redireciona para a tela mostrando os alunos
        else if (e.getSource() == botao_info_turmas)
            AdministrarTurmas(); // Redireciona para a tela com as duas opções de adm de turma: Excluir Turma e Atribuir Notas
        else if(e.getSource() == retornar_button)
            tela_padrao_criacao(); // Retorna a tela principal
        else if (e.getSource() == botao_check_idv){
            // Procurando se é aluno, professor ou turma
            char[] frase = new char[input_code.getText().length()]; // Transforma a string em um array de caracteres
            input_code.getText().getChars(0, input_code.getText().length(), frase, 0);
            if (frase[0] == '2'){ // É um professor
                FramePerfilIdv(school.professores_registrados.get(input_code.getText())); // Usa a função de montar perfil para pessoas
            }
            else if (frase[0] == '3') // é um aluno
                FramePerfilIdv(school.alunos_registrados.get(input_code.getText()));    // Usa a função de montar perfil para pessoas
            else if (frase[0] == '1') // É uma turma
                FrameAdmTurma(school.turmas_registradas.get(input_code.getText())); // Redireciona para a tela de administração, passando a turma selecionada como parametro
        }
        else if (e.getSource() == btn_atribuir_notas)
            FrameAtribuirNotas(school.turmas_registradas.get(input_code.getText()));    // Redireciona para o frame de atribuir notas
        else if (e.getSource() == btn_excluir_aluno) // Entra pro frame pra seleção do aluno a ser excluido
            FrameExcluirAluno(school.turmas_registradas.get(input_code.getText())); // Redireciona para o frame para excluir aluno
        else if (e.getSource() == btn_exclude){ 
            System.out.println("I must say, what a brilliant speech you gave");
            school.alunos_registrados.get(input_excd.getText()).remover_turma(input_code.getText()); // Remove o codigo da classe Pessoa
            school.turmas_registradas.get(input_code.getText()).lista_de_alunos.remove(input_excd.getText()); // Remove o aluno da classe Turma
            tela_padrao_criacao();
        }
        else if (e.getSource() == btn_send_grades){
            for (JTextField termo : notas.values()){
                Pessoa aluno = new Pessoa("", "");
                for (String code_aluno : notas.keySet()){
                    aluno = school.alunos_registrados.get(code_aluno);}
                Float nota_convertida = Float.parseFloat(termo.getText());
                school.turmas_registradas.get(input_code.getText()).atribuir_nota(aluno, nota_convertida);
            }
            tela_padrao_criacao();
        }
        else if (e.getSource() == salvar){
            school.salvar(school);  // Usa o metodo de salvar da classe Escola
            System.out.println("Salvo!");
            tratar();
            tela_padrao_criacao();
        }
        else if (e.getSource() == carregar){ // Usa o método de carregar da classe Escola
            this.school = school.carregar();
            tratar();
            tela_padrao_criacao();
            System.out.println("Carregado!");
        }
    }

    private void FrameCriacaoProfessor(){

        botao_submit_prof = new JButton();
        botao_submit_prof.setText("Enviar");    // Botão para enviar as informações
        botao_submit_prof.setBounds(380,100, 70, 50);
        botao_submit_prof.addActionListener(this);

        JLabel texto_nome = new JLabel();
        texto_nome.setText("Nome: ");
        texto_nome.setBounds(0, 70, 70, 70);

        input_nome = new JTextField();  // JTextField para colocar o nome
        input_nome.setBounds(70, 70, 300, 25);

        this.add(input_nome);
        this.add(texto_nome);
        this.add(botao_submit_prof);
        tratar();
    }

    private void FrameCriacaoTurma(){
        botao_submit_turma = new JButton();
        botao_submit_turma.setText("Enviar"); // Botao de envio de informação
        botao_submit_turma.setBounds(380 ,100, 100, 50);
        botao_submit_turma.addActionListener(this);

        JLabel txt_materia = new JLabel();
        txt_materia.setText("Matéria: ");
        txt_materia.setBounds(0, 70, 100, 70);

        input_nome = new JTextField();  // Para inserir o nome da matéria
        input_nome.setBounds(80, 70, 140, 40);

        JLabel txt_discente = new JLabel();
        txt_discente.setText("Discente: ");
        txt_discente.setBounds(0, 100, 120, 70);

        lista_profs = new JComboBox<String>(); // Combobox para os codigos dos professores
        for (String code : school.professores_registrados.keySet())
            lista_profs.addItem(code); // Adiciona cada codigo do professor registrado na combobox
        lista_profs.setBounds(130, 100, 200, 70);

        this.add(txt_discente);
        this.add(txt_materia);
        this.add(input_nome);
        this.add(lista_profs);
        this.add(botao_submit_turma);

        tratar();
    }

    private void FrameCriacaoAluno(){
        botao_submit_aluno = new JButton();
        botao_submit_aluno.setText("Enviar");   // Botao de envio de info
        botao_submit_aluno.setBounds(380,100, 70, 30);
        botao_submit_aluno.addActionListener(this);

        // Para colocar o nome
        input_nome = new JTextField();
        input_nome.setBounds(70, 70, 300, 25);

        JLabel texto_nome = new JLabel();
        texto_nome.setText("Nome: ");
        texto_nome.setBounds(0, 70, 70, 0);

        // Para colocar as Turmas
        JLabel txt_turmas = new JLabel();
        txt_turmas.setText("Turmas: ");
        txt_turmas.setBounds(0, 140, 70, 0);

        opcoes = new ArrayList<JRadioButton>(); // ArrayList com os radiobuttons
        int indice = 0, pulo = 30;

        // Cria, adiciona à arraylist e coloca as dimensões de cada radiobutton
        for (String turma_idv : school.turmas_registradas.keySet()){
            System.out.println("Botao criado");
            opcoes.add(new JRadioButton(turma_idv));
            opcoes.get(indice).setBounds(70, 140+pulo, 100, 30);
            this.add(opcoes.get(indice));
            indice += 1;
            pulo += 30;
        }

        this.add(txt_turmas);
        this.add(input_nome);
        this.add(botao_submit_aluno);

        tratar();
    }

    private void FrameMostrarProfessores(){
        ArrayList<JLabel> nomes = new ArrayList<JLabel>();
        ArrayList<JLabel> codigos = new ArrayList<JLabel>();
        int index = 0, pulo_vert = 40;

        retornar_button = new JButton("X");
        retornar_button.setBounds(750, 50, 50, 50);
        retornar_button.addActionListener(this);
        this.add(retornar_button);

        // Cabeçalho
        JLabel cabecalho = new JLabel("NOME                     CODIGO");
        cabecalho.setBounds(30, 100, 300, 50);
        this.add(cabecalho);

        // Estruturação da alocação dos nomes e códigos dos professores
        for (Pessoa professor : school.professores_registrados.values()){
            nomes.add(new JLabel(professor.nome));
            nomes.get(index).setBounds(50, 120+pulo_vert, 150, 75);;
            codigos.add(new JLabel(professor.codigo));
            codigos.get(index).setBounds(200, 120+pulo_vert, 150, 75);
            pulo_vert += 40;
            this.add(nomes.get(index));
            this.add(codigos.get(index));
            index += 1;
        }

        setarIdv(pulo_vert); // Função que coloca o JTextField em que o código será inserido
        tratar();
    }

    private void AdministrarTurmas(){ // Método que mostra as turmas disponíveis para administração
        ArrayList<JLabel> nomes = new ArrayList<JLabel>();
        ArrayList<JLabel> codigos = new ArrayList<JLabel>();
        int index = 0, pulo_vert = 40;
        setarRetorno();

        // Cabeçalho
        JLabel cabecalho = new JLabel("MATERIA                     CODIGO");
        cabecalho.setBounds(30, 100, 300, 50);
        this.add(cabecalho);

        // Printando e organizando turmas na tabela
        for (Turma materia : school.turmas_registradas.values()){
            nomes.add(new JLabel(materia.materia));
            nomes.get(index).setBounds(50, 120+pulo_vert, 150, 75);;
            codigos.add(new JLabel(materia.codigo_turma));
            codigos.get(index).setBounds(200, 120+pulo_vert, 150, 75);
            pulo_vert += 40;
            this.add(nomes.get(index));
            this.add(codigos.get(index));
            index += 1;
        }

        setarIdv(pulo_vert);   //Coloca o JTextField em que será posto o código

        // Vai ter um botão ao lado de cada linha de "Atribuir notas"
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void FrameMostrarAlunos(){
        ArrayList<JLabel> nomes = new ArrayList<JLabel>();
        ArrayList<JLabel> codigos = new ArrayList<JLabel>();
        int index = 0, pulo_vert = 40;

        setarRetorno();

        // Cabeçalho
        JLabel cabecalho = new JLabel("NOME                     CODIGO");
        cabecalho.setBounds(30, 100, 300, 50);
        this.add(cabecalho);

        // Printando e organizando alunos na tabela
        for (Pessoa aluno : school.alunos_registrados.values()){
            nomes.add(new JLabel(aluno.nome));
            nomes.get(index).setBounds(50, 120+pulo_vert, 150, 75);;
            codigos.add(new JLabel(aluno.codigo));
            codigos.get(index).setBounds(200, 120+pulo_vert, 150, 75);
            pulo_vert += 40;
            this.add(nomes.get(index));
            this.add(codigos.get(index));
            index += 1;
        }

        setarIdv(pulo_vert); //Coloca o JTextField em que será posto o código

        // Vai ter um botão ao lado de cada linha de "Mostrar perfil individual"
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void FrameAdmTurma(Turma turma_analisada){
        btn_atribuir_notas = new JButton("Atribuir notas");     // Botão que redireciona para a tela de atribuir notas
        btn_atribuir_notas.addActionListener(this);
        btn_atribuir_notas.setFocusable(false);
        btn_atribuir_notas.setBounds(225, 225, 75, 50);

        btn_excluir_aluno = new JButton("Excluir aluno");       // Botão para a tela de deletamento de aluno
        btn_excluir_aluno.addActionListener(this);
        btn_excluir_aluno.setFocusable(false);
        btn_excluir_aluno.setBounds(400, 400, 75, 50);

        this.add(btn_atribuir_notas);
        this.add(btn_excluir_aluno);

        JLabel titulo = new JLabel("ADMINISTRAÇÃO DE TURMA");
        titulo.setBounds(200, 0, 400, 100);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.add(titulo);
        
        tratar();
    }

    private void FrameExcluirAluno(Turma turma_analisada){
        ArrayList<JLabel> nomes = new ArrayList<JLabel>();
        ArrayList<JLabel> codigos = new ArrayList<JLabel>();
        int index = 0, pulo_vert = 40;

        setarRetorno();

        // Cabeçalho
        JLabel cabecalho = new JLabel("NOME                     CODIGO");
        cabecalho.setBounds(30, 100, 300, 50);
        this.add(cabecalho);

        // Printando e organizando alunos na tabela
        for (Pessoa aluno : turma_analisada.lista_de_alunos.values()){ // Varre os alunos de uma certa turma, os organizando e os printando
            nomes.add(new JLabel(aluno.nome));
            nomes.get(index).setBounds(50, 120+pulo_vert, 150, 75);;
            codigos.add(new JLabel(aluno.codigo));
            codigos.get(index).setBounds(200, 120+pulo_vert, 150, 75);
            pulo_vert += 40;
            this.add(nomes.get(index));
            this.add(codigos.get(index));
            index += 1;
        }

        input_excd = new JTextField();  //Textfield em que será posto o código do aluno a ser deletado
        input_excd.setBounds(50, 120+pulo_vert, 150, 30);
        JLabel aux_code = new JLabel("Inserir código: ");
        aux_code.setBounds(0, 120+pulo_vert, 149, 50);
        this.add(aux_code);
        this.add(input_excd);

        btn_exclude = new JButton("Enviar");    // Botão para enviar a informação
        btn_exclude.setBounds(201, 120+pulo_vert, 100, 30);
        btn_exclude.addActionListener(this);
        this.add(btn_exclude);

        // Vai ter um botão ao lado de cada linha de "Mostrar perfil individual"
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    private void FrameAtribuirNotas(Turma turma_analisada){
        notas = new HashMap<>();    // Hashmap de chave String, um código de um aluno, que acessa um item Jtextfield associado
        // GERAÇÃO DOS NOMES
        ArrayList<JLabel> nomes = new ArrayList<JLabel>();
        ArrayList<JLabel> codigos = new ArrayList<JLabel>();
        int index = 0, pulo_vert = 40;

        setarRetorno();

             // Cabeçalho
        JLabel cabecalho = new JLabel("NOME                     CODIGO                     NOTA");
        cabecalho.setBounds(30, 100, 300, 50);
        this.add(cabecalho);

             // Printando e organizando alunos na tabela
        for (Pessoa aluno : turma_analisada.lista_de_alunos.values()){
            nomes.add(new JLabel(aluno.nome));
            nomes.get(index).setBounds(50, 120+pulo_vert, 150, 75);

            codigos.add(new JLabel(aluno.codigo));
            codigos.get(index).setBounds(200, 120+pulo_vert, 150, 75);

            notas.put(aluno.codigo, new JTextField());  // Cada JTextField terá um aluno associado via o codigo deste
            notas.get(aluno.codigo).setBounds(360, 120+pulo_vert, 150, 75);

            pulo_vert += 40;
            this.add(nomes.get(index));
            this.add(codigos.get(index));
            this.add(notas.get(aluno.codigo));
            index += 1;
        }

        btn_send_grades = new JButton("Mandar");    // Envia as informações nos textsfields
        btn_send_grades.addActionListener(this);
        btn_send_grades.setBounds(50, 150+pulo_vert, 100, 35);
        this.add(btn_send_grades);
        tratar();
    }

    private void FramePerfilIdv(Pessoa analisado){  // Cria o perfil individual de um professor ou aluno
        JLabel nome = new JLabel(analisado.nome);
        nome.setBounds(50, 50, 100, 50);
        JLabel codigo = new JLabel(analisado.codigo);
        codigo.setBounds(50, 100, 100, 50);
        JLabel emprego = new JLabel(analisado.ocupacao);
        emprego.setBounds(50, 150, 100, 50);

        JLabel cabecalho_materia = new JLabel("MATERIAS: ");
        cabecalho_materia.setBounds(75, 200, 150, 80);
        cabecalho_materia.setFont(new Font("Times New Roman", Font.BOLD, 16));

        ArrayList<JLabel> materias = new ArrayList<>();
        int index = 0, pulo_vert = 40;
        for (String code_class : analisado.codigos_turmas){ // Mostra as turmas que este está presente
            materias.add(new JLabel(school.turmas_registradas.get(code_class).materia)); // Adiciona o nome da materia as labels
            materias.get(index).setBounds(75, 200+pulo_vert, 100, 50);
            this.add(materias.get(index));
            pulo_vert += 40;
            index += 1;
        }

        if (analisado.ocupacao.equals("Aluno")){ // Se for um aluno, adiciona o setor das notas ao lado das turmas
            ArrayList<JLabel> notas = new ArrayList<>();
            index = 0;
            pulo_vert = 40;

            JLabel cabecalho_notas = new JLabel("NOTAS");
            cabecalho_notas.setBounds(230, 200, 150, 80);
            cabecalho_notas.setFont(new Font("Times New Roman", Font.ITALIC, 16));

            for (String code_class : analisado.codigos_turmas){
                String nota_convertida = Float.toString(school.turmas_registradas.get(code_class).notas.get(analisado.codigo));
                notas.add(new JLabel(nota_convertida));
                notas.get(index).setBounds(176, 200+pulo_vert, 150, 50);
                this.add(notas.get(index));
                index += 1;
                pulo_vert += 40;
            }
        }

        this.add(cabecalho_materia);
        this.add(nome);
        this.add(emprego);
        this.add(codigo);
        setarRetorno();
        tratar();
    }

    public void clean(){    // Limpa a tela
        this.getContentPane().removeAll();
        this.validate();
    }

    public void tratar(){   // Deve ser usada no fim de uma tela para tratar o clean()
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void setarIdv(int pulo_vert){    // Cria o JTextField para colocar o codigo de um aluno/professor/turma e o botão para enviar essa informação
        input_code = new JTextField();
        input_code.setBounds(50, 120+pulo_vert, 150, 50);
        JLabel aux_code = new JLabel("Inserir código: ");
        aux_code.setBounds(0, 120+pulo_vert, 149, 50);

        botao_check_idv = new JButton("Enviar");
        botao_check_idv.setBounds(201, 120+pulo_vert, 100, 50);
        botao_check_idv.addActionListener(this);

        this.add(botao_check_idv);
        this.add(aux_code);
        this.add(input_code);
    }

    public void setarRetorno(){ // Cria e posiciona o botão que retorna a tela principal
        retornar_button = new JButton("X");
        retornar_button.setBounds(750, 50, 50, 50);
        retornar_button.addActionListener(this);
        this.add(retornar_button);
    }
}
