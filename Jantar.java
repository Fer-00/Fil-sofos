public class Jantar
{
	private boolean[] garfos;
	private int nFilosofos;

	public Jantar(int nFilosofos)
	{
		this.nFilosofos = nFilosofos;
		garfos = new boolean[nFilosofos];
		for (int i = 0; i < nFilosofos; i++) //Todos os garfos são inicializados disponíveis;
			garfos[i] = true; 
	}

	private synchronized void mudaEstadoIndisponivel(int garfo)
	{
		garfos[garfo] = false;
	}

	private synchronized void mudaEstadoDisponivel(int garfo)
	{
		garfos[garfo] = true;
	}

	protected synchronized void pegaGarfos(int filosofo)
	{
		while(garfos[filosofo] == false && (garfos[filosofo_ant(filosofo)] == false && garfos[filosofo_prox(filosofo)] == false))
		{ //Verifica se o filósofo tem disponível seu garfo, depois verifica se ele tem disponível o garfo à esquerda ou à direita;
			try
			{
				wait(); //Caso ele não tenha dois garfos disponíveis, ele espera;
				System.out.println("O Filósofo " + filosofo + " Está Esperando para pegar Garfos");
			}
			catch(InterruptedException e)
			{
				System.out.println("Erro pegaGarfos - jantar");
			}
		}
		System.out.println("O Filósofo " + filosofo + " Está Pegando Garfos");
		mudaEstadoIndisponivel(filosofo); //Altera o estado dos garfos usados pelo filósofo para indisponível, verificando se ele pegou o garfo do seu antecessor ou sucessor;
		if (garfos[filosofo_ant(filosofo)] == true)
			mudaEstadoIndisponivel(filosofo_ant(filosofo));
		else
			mudaEstadoIndisponivel(filosofo_prox(filosofo));
	}

	protected synchronized void largaGarfos(int filosofo) //Devolve os garfos à mesa;
	{
		System.out.println("O Filósofo " + filosofo + " Está Largando Garfos");
		mudaEstadoDisponivel(filosofo);
		if (garfos[filosofo_ant(filosofo)] == false)
			mudaEstadoDisponivel(filosofo_ant(filosofo));
		else
			mudaEstadoDisponivel(filosofo_prox(filosofo));
		
		notifyAll();
	}

	private int filosofo_ant(int filosofo) //Retorna o filósofo anterior ao que está executando, lembrando que o 0 e último estão lado a lado;
	{
		if (filosofo == 0)
			return nFilosofos - 1;
		else
			return filosofo - 1;		
	}

	private int filosofo_prox(int filosofo) //Retorna o filósofo sucessor ao que está executando, lembrando que o 0 e último estão lado a lado;
	{
		if (filosofo == nFilosofos - 1)
			return 0;
		else
			return filosofo + 1;		
	}
}