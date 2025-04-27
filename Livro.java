public abstract class Livro {
    
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int numPaginas;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    public int getanoPublicacao() {
        return anoPublicacao;
    }
    public void setanoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getNumPaginas() {
        return numPaginas;
    }
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }
    public abstract String toString();
    public abstract String getFormato();
}
