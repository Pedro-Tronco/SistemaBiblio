public final class LivroDigital extends Livro {
    
    private String tamanhoArquivo;
    private String formatoArquivo;

    public String getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    public void setTamanhoArquivo(String tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }
    public String getFormatoArquivo() {
        return formatoArquivo;
    }
    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }
    public String toString() {
        return this.getTitulo() + "(Livro " + this.getFormato() + ") - Formato: " + this.getFormatoArquivo() + " - Tamanho: " + this.getTamanhoArquivo();
    }
    public String getFormato(){
        return "Digital";
    }

}
