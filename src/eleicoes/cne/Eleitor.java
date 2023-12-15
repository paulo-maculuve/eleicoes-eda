package eleicoes.cne;

public class Eleitor {
    String numeroCartao;
    String nome;
    String provincia;
    String candidato;

    public Eleitor(String numeroCartao, String nome, String provincia) {
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.provincia = provincia;
        this.candidato = null; // Inicialmente nenhum candidato escolhido
    }

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCandidato() {
		return candidato;
	}

	public void setCandidato(String candidato) {
		this.candidato = candidato;
	}

   
}