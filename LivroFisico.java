public final class LivroFisico extends Livro {

    private String capa;
    private int condicao;
    private int numExemplares;

    public int getNumExemplares(){
        return numExemplares;
    }
    public void setNumExemplares(int numExemplares){
        this.numExemplares = numExemplares;
    }
    public String getCapa() {
        return capa;
    }
    public void setCapa(String capa) {
        this.capa = capa;
    }
    public int getCondicao() {
        return condicao;
    }
    public void setCondicao(int condicao) {
        this.condicao = condicao;
    }
    public String toString() {
        return this.getTitulo() + " - Condição: " + this.getCondicao() + "/10 - Nº de Exemplares: " + this.getNumExemplares();
    }
}