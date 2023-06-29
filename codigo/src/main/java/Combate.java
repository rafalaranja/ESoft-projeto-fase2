public class Combate {
    private Atleta atleta1;
    private Atleta atleta2;
    private Prova prova;
    private String resultado1;
    private String resultado2;
    private String vencedor;

    public Combate(Atleta atleta1, Atleta atleta2, Prova prova, String resultado1, String resultado2, String vencedor) {
        this.atleta1 = atleta1;
        this.atleta2 = atleta2;
        this.prova = prova;
        this.resultado1 = resultado1;
        this.resultado2 = resultado2;
        this.vencedor = vencedor;
    }

}
