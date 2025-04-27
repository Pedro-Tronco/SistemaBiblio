import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Biblioteca {
    private List<Livro> acervo = new ArrayList<>();

    public String verificaLivro(Livro novoLivro) throws Exception {
        final int ANO_MINIMO = 1400;
        final int ANO_MAXIMO = Year.now().getValue();
        if(novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty()) {
            throw new Exception("Título inválido.");
        }
        if(novoLivro.getanoPublicacao() < ANO_MINIMO){
            throw new Exception("Esse livro já virou pó!");
        }
        if(novoLivro.getanoPublicacao() > ANO_MAXIMO){
            throw new Exception("Não aceitamos livros de viajantes no tempo.");
        }
        if(novoLivro.getNumPaginas() <= 0){
            throw new Exception("Número de páginas não pode ser 0 ou negativo.");
        }
        for(Livro livro : acervo){
            if(livro.getTitulo().toUpperCase().contentEquals(novoLivro.getTitulo().toUpperCase())){
                throw new Exception("Livro com mesmo nome já cadastrado.");
            }
            
        }
        acervo.add(novoLivro);
        return novoLivro.getTitulo();     
    }

    public List<Livro> getAcervo(){
        return acervo;
    }

    public List<Livro> buscarPorTitulo(String busca, boolean exactMatch){
        List<Livro> livrosEncontrados = new ArrayList<Livro>();
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getTitulo().toUpperCase().contentEquals(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getTitulo().toUpperCase().contains(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> buscarPorAutor(String busca, boolean exactMatch){
        List<Livro> livrosEncontrados = new ArrayList<Livro>();
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getAutor().toUpperCase().contentEquals(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getAutor().toUpperCase().contains(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> buscarPorEditora(String busca, boolean exactMatch){
        List<Livro> livrosEncontrados = new ArrayList<Livro>();
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getEditora().toUpperCase().contentEquals(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getEditora().toUpperCase().contains(busca.toUpperCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> buscarPorAno(int busca, int searchType){
        List<Livro> livrosEncontrados = new ArrayList<Livro>();
        if (searchType == 1) {
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() == busca) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        else if (searchType == 2) {
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() < busca) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        else if (searchType == 3) {
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() > busca) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    public String excluirPorTitulo(String busca, boolean exactMatch){
        List<Integer> indexLivros = new ArrayList<>();
        int livrosRemovidos = 0;
        String mensagem = "";
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getTitulo().toUpperCase().contentEquals(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro chamado '" + busca + "' foi encontrado.";
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getTitulo().toUpperCase().contains(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro com título contendo '" + busca + "' foi encontrado.";
            }
        }
        for(Integer integer : indexLivros){
            int index = integer.intValue() - livrosRemovidos;
            acervo.remove(index);
            livrosRemovidos ++;
        }
        if(indexLivros.size() > 0){
            mensagem = indexLivros.size() + " livro(s) excluido(s).";
        }
        return mensagem;
    }

    public String excluirPorAutor(String busca, boolean exactMatch){
        List<Integer> indexLivros = new ArrayList<>();
        int livrosRemovidos = 0;
        String mensagem = "";
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getAutor().toUpperCase().contentEquals(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro escrito por '" + busca + "' foi encontrado.";
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getAutor().toUpperCase().contains(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro com nome do autor contendo '" + busca + "' foi encontrado.";
            }
        }
        for(Integer integer : indexLivros){
            int index = integer.intValue() - livrosRemovidos;
            acervo.remove(index);
            livrosRemovidos ++;
        }
        if(indexLivros.size() > 0){
            mensagem = indexLivros.size() + " livro(s) excluido(s).";
        }
        return mensagem;
    }

    public String excluirPorEditora(String busca, boolean exactMatch){
        List<Integer> indexLivros = new ArrayList<>();
        int livrosRemovidos = 0;
        String mensagem = "";
        if(exactMatch){
            for(Livro livro : acervo){
                if(livro.getEditora().toUpperCase().contentEquals(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro publicado por '" + busca + "' foi encontrado.";
            }
        }
        else {
            for(Livro livro : acervo){
                if(livro.getEditora().toUpperCase().contains(busca.toUpperCase())) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro com nome da editora contendo '" + busca + "' foi encontrado.";
            }
        }
        for(Integer integer : indexLivros){
            int index = integer.intValue() - livrosRemovidos;
            acervo.remove(index);
            livrosRemovidos ++;
        }
        if(indexLivros.size() > 0){
            mensagem = indexLivros.size() + " livro(s) excluido(s).";
        }
        return mensagem;
    }

    public String excluirPorAno(int busca, int searchType){
        List<Integer> indexLivros = new ArrayList<>();
        int livrosRemovidos = 0;
        String mensagem = "";
        if(searchType == 1){
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() == busca) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro publicado em " + busca + " foi encontrado.";
            }
        }
        else if(searchType == 2){
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() < busca) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro publicado antes de " + busca + " foi encontrado.";
            }
        }
        else if(searchType == 3){
            for(Livro livro : acervo){
                if(livro.getanoPublicacao() > busca) {
                    indexLivros.add(acervo.indexOf(livro));
                }
            }
            if(indexLivros.size() == 0){
                mensagem = "Nenhum livro publicado depois de " + busca + " foi encontrado.";
            }
        }
        for(Integer integer : indexLivros){
            int index = integer.intValue() - livrosRemovidos;
            acervo.remove(index);
            livrosRemovidos ++;
        }
        if(indexLivros.size() > 0){
            mensagem = indexLivros.size() + " livro(s) excluido(s).";
        }
        return mensagem;
    }

    public int calcularTempoPublicacao(int anoPublicacao){
        final int ANO_ATUAL = Year.now().getValue();
        anoPublicacao = ANO_ATUAL - anoPublicacao;
        return anoPublicacao;
    }

}