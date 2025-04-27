import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca lib = new Biblioteca(); 
    
    public static void main(String[] args) {
        
        boolean running = true;
        Scanner getInput = new Scanner(System.in);

        do{
            clearScreen();
               
            //Menu principal
            System.out.println("""
            #===================================#
            |          Biblioteca 3000          |
            #===================================#
            - Comandos:

            1: Cadastrar novo livro
            2: Exibir todos os livros
            3: Buscar livros
            4: Remover livros
            5: Dados da Biblioteca
            0: Sair
            """);
            int option = inputNum("", getInput);

            switch (option) {
                case 1:
                    selecionarTipoLivro(getInput);
                    break;
                case 2:
                    exibirLivros();
                    askConfirmation(getInput);
                    break;
                case 3:
                    buscarLivros(getInput);
                    askConfirmation(getInput);
                    break;
                case 4:
                    excluirLivros(getInput);
                    askConfirmation(getInput);
                    break;
                case 5:
                    dadosAcervo();
                    askConfirmation(getInput);
                    break;
                case 0:
                    running = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println(option + " não é um comando válido.");
                    askConfirmation(getInput);
                    break;
            }

        } while (running);
        getInput.close();
    }

    private static void selecionarTipoLivro(Scanner scanner){
        clearScreen();
        System.out.println("--- Cadastro de livros ---\n");
        System.out.println("""
                Selecione o tipo de Livro:
                1: Fisico
                2: Digital
                0: Cancelar""");
        int option = inputNum("", scanner);
        clearScreen();
        switch (option) {
            // Fisico
            case 1:
                LivroFisico novoLivroFisico = new LivroFisico();
                System.out.println("-- Novo Livro Físico --");
                novoLivroFisico = cadastrarDadosLivro(novoLivroFisico, scanner);
                verificaLivro(novoLivroFisico, scanner); 
                break;

            // Digital
            case 2:
                LivroDigital novoLivroDigital = new LivroDigital();
                System.out.println("-- Novo Livro Digital --");
                novoLivroDigital = cadastrarDadosLivro(novoLivroDigital, scanner);
                verificaLivro(novoLivroDigital, scanner); 
                break;

            // Retornar
            case 0:
                break;

            // Input invalido
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                selecionarTipoLivro(scanner);
        }
        
    }

    private static LivroFisico cadastrarDadosLivro(LivroFisico novoLivro, Scanner scanner){
        
        System.out.print("Insira o Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Insira o Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Insira a Editora: ");
        String editora = scanner.nextLine();
        int anoPublicacao = inputNum("Insira o ano de publicação: ", scanner);
        int numPaginas = inputNum("Insira o número de páginas: ", scanner);
        int numExemplares = inputNota("Insira o número de exemplares: ", scanner);
        System.out.print("Insira o tipo da capa: ");
        String capa = scanner.nextLine();
        int condicao = inputNota("Insira a condição do livro (nota de 1-10): ", scanner);

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setEditora(editora);
        novoLivro.setanoPublicacao(anoPublicacao);
        novoLivro.setNumPaginas(numPaginas);
        novoLivro.setNumExemplares(numExemplares);
        novoLivro.setCapa(capa);
        novoLivro.setCondicao(condicao);

        return novoLivro;
    }

    private static LivroDigital cadastrarDadosLivro(LivroDigital novoLivro, Scanner scanner){
        
        System.out.print("Insira o Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Insira o Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Insira a Editora: ");
        String editora = scanner.nextLine();
        int anoPublicacao = inputNum("Insira o ano de publicação: ", scanner);
        int numPaginas = inputNum("Insira o número de páginas: ", scanner);
        System.out.print("Insira o tipo do arquivo: ");
        String formatoArquivo = scanner.nextLine();
        System.out.print("Insira o peso do arquivo: ");
        String tamanhoArquivo = scanner.nextLine();  

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setEditora(editora);
        novoLivro.setanoPublicacao(anoPublicacao);
        novoLivro.setNumPaginas(numPaginas);
        novoLivro.setFormatoArquivo(formatoArquivo);
        novoLivro.setTamanhoArquivo(tamanhoArquivo);

        return novoLivro;
    }

    private static void verificaLivro(Livro novoLivro, Scanner scanner) {
        try {
            String titulo = lib.verificaLivro(novoLivro);
            System.out.println("'" + titulo + "' cadastrado com sucesso.");
            askConfirmation(scanner);
        } catch(Exception e){
            System.out.println("Livro não cadastrado: " + e.getMessage());
            askConfirmation(scanner);
            selecionarTipoLivro(scanner);
        }
    }

    private static void exibirLivros(){
        List<Livro> livros = lib.getAcervo();
        boolean encontrouLivro = false;
        for(Livro livro : livros){
            encontrouLivro = true;
            System.out.println(livro.toString() + " - Anos desde publicação : " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
        }
        if(!encontrouLivro){
            System.out.println("Nenhum livro cadastrado!");
        }
    }

    private static void buscarLivros(Scanner scanner) {
        clearScreen();
        System.out.println("--- Consulta de Livros ---");
        System.out.println("""
                Selecione o tipo de busca:
                1: Título
                2: Autor
                3: Editora
                4: Ano de publicação
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                buscarPorTitulo(scanner);
                break;
            case 2:
                buscarPorAutor(scanner);
                break;
            case 3:
                buscarPorEditora(scanner);
                break;
            case 4:
                buscarPorAno(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                buscarLivros(scanner);     
        }
    }

    private static void buscarPorTitulo(Scanner scanner){
        String titulo;
        List<Livro> livros;
        clearScreen();
        System.out.println("--- Consulta por título ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Título igual a ''
                2: Título contém ''
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o título: ");
                titulo = scanner.nextLine();
                livros = lib.buscarPorTitulo(titulo, true);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("'" + titulo + "' não foi encontrado.");}
                break;

            case 2:
                System.out.print("Digite a palavra de busca: ");
                titulo = scanner.nextLine();
                livros = lib.buscarPorTitulo(titulo, false);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhum título contendo '" + titulo + "' foi encontrado.");}
                break;

            case 0:
                break;

            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                buscarPorTitulo(scanner);     
        }
    }

    private static void buscarPorAutor(Scanner scanner){
        String autor;
        List<Livro> livros;
        clearScreen();
        System.out.println("--- Consulta por autor ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Nome do Autor igual a ''
                2: Nome do Autor contém ''
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o nome do autor: ");
                autor = scanner.nextLine();
                livros = lib.buscarPorAutor(autor, true);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhum livro escrito por '" + autor + "' foi encontrado.");}
                break;

            case 2:
                System.out.print("Digite a palavra de busca: ");
                autor = scanner.nextLine();
                livros = lib.buscarPorAutor(autor, false);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhum autor contendo '" + autor + "' no nome foi encontrado.");}
                break;

            case 0:
                break;

            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                buscarPorAutor(scanner);     
        }
    }

    private static void buscarPorEditora(Scanner scanner){
        String editora;
        List<Livro> livros;
        clearScreen();
        System.out.println("--- Consulta por editora ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Nome da Editora igual a ''
                2: Nome da Editora contém ''
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o nome da editora: ");
                editora = scanner.nextLine();
                livros = lib.buscarPorEditora(editora, true);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhum livro publicado por '" + editora + "' foi encontrado.");}
                break;

            case 2:
                System.out.print("Digite a palavra de busca: ");
                editora = scanner.nextLine();
                livros = lib.buscarPorEditora(editora, false);
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhuma editora contendo '" + editora + "' no nome foi encontrado.");}
                break;

            case 0:
                break;

            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                buscarPorEditora(scanner);     
        }
    }

    private static void buscarPorAno(Scanner scanner){
        int ano;
        List<Livro> livros;
        clearScreen();
        System.out.println("--- Consulta por ano de publicação ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Ano de publicação = ano de busca
                2: Ano de publicação < ano de busca
                3: Ano de publicação > ano de busca
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                ano = inputNum("Digite o número de busca: ", scanner);
                livros = lib.buscarPorAno(ano, 1); // ano pub = ano
                System.out.println("Livros encontrados:");
                for(Livro livro : livros){
                    System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
                }
                if(livros.isEmpty()){System.out.println("Nenhum livro publicado em " + ano + " foi encontrado.");}
                break;

            case 2:
            ano = inputNum("Digite o número de busca: ", scanner);
            livros = lib.buscarPorAno(ano, 2); // ano pub < ano
            System.out.println("Livros encontrados:");
            for(Livro livro : livros){
                System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
            }
            if(livros.isEmpty()){System.out.println("Nenhum livro publicado antes de " + ano + " foi encontrado.");}
            break;

            case 3:
            ano = inputNum("Digite o número de busca: ", scanner);
            livros = lib.buscarPorAno(ano, 3); // ano pub > ano
            System.out.println("Livros encontrados:");
            for(Livro livro : livros){
                System.out.println(livro.toString() + " - Anos desde publicação: " + lib.calcularTempoPublicacao(livro.getanoPublicacao()));
            }
            if(livros.isEmpty()){System.out.println("Nenhum livro publicado a partir de " + ano + " foi encontrado.");}
            break;

            case 0:
                break;

            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                buscarPorAno(scanner);     
        }
    }

    private static void excluirLivros(Scanner scanner) {
        clearScreen();
        System.out.println("--- Apagar Livros ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Título
                2: Autor
                3: Editora
                4: Ano de publicação
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                excluirPorTitulo(scanner);
                break;
            case 2:
                excluirPorAutor(scanner);
                break;
            case 3:
                excluirPorEditora(scanner);
                break;
            case 4:
                excluirPorAno(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                excluirLivros(scanner);     
        }
    }

    private static void excluirPorTitulo(Scanner scanner){
        String titulo;
        clearScreen();
        System.out.println("--- Excluir livros por título ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Título igual a ''
                2: Título contém ''
                0: Cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o título do livro: ");
                titulo = scanner.nextLine();
                System.out.println(lib.excluirPorTitulo(titulo,true));
                break;
            case 2:
                System.out.print("Digite a palavra de busca: ");
                titulo = scanner.nextLine();
                System.out.println(lib.excluirPorTitulo(titulo,false));
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                excluirPorTitulo(scanner);
        }
    }

    private static void excluirPorAutor(Scanner scanner){
        String autor;
        clearScreen();
        System.out.println("--- Excluir livros por autor ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Nome do autor igual a ''
                2: Nome do autor contém ''
                0: Cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o nome do autor: ");
                autor = scanner.nextLine();
                System.out.println(lib.excluirPorAutor(autor,true));
                break;
            case 2:
                System.out.print("Digite a palavra de busca: ");
                autor = scanner.nextLine();
                System.out.println(lib.excluirPorAutor(autor,false));
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                excluirPorAutor(scanner);
        }
    }

    private static void excluirPorEditora(Scanner scanner){
        String editora;
        clearScreen();
        System.out.println("--- Excluir livros por editora ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Nome da editora igual a ''
                2: Nome da editora contém ''
                0: Cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1:
                System.out.print("Digite o nome da editora: ");
                editora = scanner.nextLine();
                System.out.println(lib.excluirPorEditora(editora,true));
                break;
            case 2:
                System.out.print("Digite a palavra de busca: ");
                editora = scanner.nextLine();
                System.out.println(lib.excluirPorEditora(editora,false));
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                excluirPorEditora(scanner);
        }
    }

    private static void excluirPorAno(Scanner scanner){
        int ano;
        clearScreen();
        System.out.println("--- Excluir livros ano de publicação ---");
        System.out.println("""
                Selecione o tipo de filtro:
                1: Ano de publicação = ano de busca
                2: Ano de publicação < ano de busca
                3: Ano de publicação > ano de busca
                0: cancelar
                """);
        int option = inputNum("", scanner);
        switch (option) {
            case 1: // ano pub = ano
                ano = inputNum("Digite o ano de busca: ", scanner);
                System.out.println(lib.excluirPorAno(ano,1));
                break;
            case 2: // ano pub < ano
                ano = inputNum("Digite o ano de busca: ", scanner);
                System.out.println(lib.excluirPorAno(ano,2));
                break;
            case 3: // ano pub > ano
                ano = inputNum("Digite o ano de busca: ", scanner);
                System.out.println(lib.excluirPorAno(ano,3));
                break;
            case 0:
                break;
            default:
                System.out.println(option + " não é um comando válido.");
                askConfirmation(scanner);
                excluirPorAno(scanner);
        }
    }

    private static void dadosAcervo(){
        List<Livro> livros = lib.getAcervo();
        int livrosCadastrados = livros.size();
        int totalPaginas = 0;
        String livroMaisRecente = "";
        int anoMaisRecente = 99999999;
        String livroMaisAntigo = "";
        int anoMaisAntigo = 0;
        if(livrosCadastrados > 0){
            for(Livro livro : livros) {
                totalPaginas = totalPaginas + livro.getNumPaginas();
                if(lib.calcularTempoPublicacao(livro.getanoPublicacao()) > anoMaisAntigo){
                    livroMaisAntigo = livro.getTitulo();
                    anoMaisAntigo = lib.calcularTempoPublicacao(livro.getanoPublicacao());
                }
                if(lib.calcularTempoPublicacao(livro.getanoPublicacao()) < anoMaisRecente){
                    livroMaisRecente = livro.getTitulo();
                    anoMaisRecente = lib.calcularTempoPublicacao(livro.getanoPublicacao());
                }
            }
            double mediaPaginas = totalPaginas / livrosCadastrados;
            System.out.println("Livros cadastrados: " + livrosCadastrados);
            System.out.printf("Média de páginas por livro: %.2f \n", mediaPaginas);
            System.out.println("Livro mais antigo: " + livroMaisAntigo + " (" + anoMaisAntigo + " ano(s) desde publicação)");
            System.out.println("Livro mais recente: " + livroMaisRecente + " (" + anoMaisRecente + " ano(s) desde publicação)");
        }
        else {
            System.out.println("Nenhum livro cadastrado!");
        }
    }

    public static void askConfirmation(Scanner scanner){
        System.out.println("Aperte 'enter' para continuar.");
        scanner.nextLine();
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();    
    }

    public static int inputNum(String mensagem, Scanner scanner) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = scanner.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro.");
            }
        } while (!entradaValida);
        return valor;
    }

    public static int inputNota(String mensagem, Scanner scanner) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = scanner.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                if(!(valor >= 1 && valor <= 10)) {
                    valor = 2/0;
                }
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro de 1 à 10.");
            }
        } while (!entradaValida);
        return valor;
    }

}