class Main {
    private String nome;
    private String ra;
    private double[] notas;
    private double presenca;
    private boolean ead;

    // Construtor para disciplinas presenciais
    public Main(String nome, String ra, double[] notas, double presenca) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.presenca = presenca;
        this.ead = false;
    }

    // Construtor para disciplinas EAD (sem presença)
    public Main(String nome, String ra, double[] notas) {
        this.nome = nome;
        this.ra = ra;
        this.notas = notas;
        this.presenca = 100; // Presença sempre 100% para EAD
        this.ead = true;
    }

    // Método para calcular a nota final
    public double calcularNotaFinal() {
        double notaFinal = 0.0;

        if (notas.length == 2) {
            // Média aritmética
            notaFinal = (notas[0] + notas[1]) / 2;
        } else if (notas.length == 3) {
            // Média ponderada: 1º nota (peso 1), 2º (peso 2), 3º (peso 4)
            notaFinal = (notas[0] + notas[1] * 2 + notas[2] * 4) / 7;
        } else if (notas.length == 4) {
            // Média ponderada para 4 notas: ac1, ac2, ag, af
            notaFinal = (notas[0] * 0.15) + (notas[1] * 0.30) + (notas[2] * 0.10) + (notas[3] * 0.45);
        }

        return notaFinal;
    }

    // Método para verificar a aprovação
    public String verificarAprovacao() {
        double notaFinal = calcularNotaFinal();
        if (ead) {
            // Aprovação apenas pela nota no EAD
            return (notaFinal >= 5) ? "Aprovado" : "Reprovado";
        } else {
            // Aprovação pela nota e presença em disciplinas presenciais
            return (notaFinal >= 5 && presenca >= 75) ? "Aprovado" : "Reprovado";
        }
    }

    // Método para imprimir os dados do aluno
    public void imprimirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("RA: " + ra);
        System.out.println("Nota Final: " + calcularNotaFinal());
        System.out.println("Situação: " + verificarAprovacao());
    }

    // Main para testes
    public static void main(String[] args) {
        // Exemplo para aluno presencial com 3 notas
        double[] notas1 = {1.0, 10.0, 4.5};
        Main aluno1 = new Main("Gabriel", "9876", notas1, 10);
        aluno1.imprimirDados();

        // Exemplo para aluno EAD com 4 notas
        double[] notas2 = {6.0, 7.0, 8.0, 5.0};
        Main aluno2 = new Main("Jubileu", "652332", notas2);
        aluno2.imprimirDados();
    }
}
