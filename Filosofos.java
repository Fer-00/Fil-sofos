//Código usado como consulta e referência: https://www.revista-programar.info/artigos/threads-semaforos-e-deadlocks-o-jantar-dos-filosofos/

public class Filosofos extends Thread
{
	Jantar janta; //Objeto pertencente a classe jantar para que todos os filósofos estejam participando da mesma circunstância;
	private int[] situacao = new int[10]; //Vetor com a quantidade de filósofos armazenando ints que indicam se o filósofo ainda não comeu (0) ou se já o fez (1);
	private final static int tempo = 5000; //variável fixa de 5 segundos para utilizar nos sleep;
	private static int i; //int i dos comandos for, declarado aqui pois foi usado mais de uma vez;
	int id; //identificador do filósofo;

	public Filosofos(int id, Jantar janta)
	{
		this.id = id;
		this.janta = janta;
	}

	public void run()
	{
		while(true)
		{
			pensar(id);
			tentaGarfos(id);
			comer(id);
		}
	}

	private void pensar(int filosofo)
	{
		try
		{
			sleep(tempo);
			System.out.println("O Filósofo " + filosofo + " Está Pensando");
		}
		catch(InterruptedException e)
		{
			System.out.println("Erro método pensar");
		}
	}

	private void tentaGarfos(int filosofo)
	{
		if (returnSituacao(filosofo) == 0) //Verifica se o filósofo não comeu ainda;
			janta.pegaGarfos(filosofo); //Com o objeto janta, que controla todos os garfos, chama o método pegaGarfos, que verifica se os garfos estão disponíveis;
		else //Caso o filósofo já tenha comido, ele cede o recurso;
			yield();
	}

	private void comer(int filosofo)
	{
		try
		{
			sleep(tempo);
			System.out.println("O Filósofo " + filosofo + " Está Comendo");
			mudaSituacao(id); //Altera o vetor de situação informando que o filósofo comeu;
			janta.largaGarfos(id); //Objeto janta chamado para devolver os garfos a mesa;
		}
		catch(InterruptedException e)
		{
			System.out.println("Erro método comer");
		}	
	}

	protected void situacaoInicial() // Nenhum filósofo comeu, 0 atribuído a todas as posições do vetor de situação;
	{
		for (i = 0; i < 10; i++)
			situacao[i] = 0;
	}

	private void mudaSituacao(int filosofo)
	{
		situacao[filosofo] = 1;
		if (verificaSituacao())
			situacaoInicial();
	}

	private int returnSituacao(int filosofo)
	{
		return situacao[filosofo];
	}

	private boolean verificaSituacao() //Se todos os filósofos comeram, chama o método situaçãoInicial para zerar todas as posições;
	{
		for (i = 0; i < 10; i++) {
			if (situacao[i] == 1)
				continue;
			else
				return false;
		}
		return true;
	}
}