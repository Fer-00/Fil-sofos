public class Executor
{
	
	public static void main(String[] args)
	{
		Jantar janta = new Jantar(); // Objeto de jantar para ser passado no construtor dos filósofos e todos executarem na mesma circunstância;
		Filosofos[] filosofos = new Filosofos[10]; //Declara o vetor com o número de filósofos;

		for (int i = 0; i < 10; i++)
		{
			filosofos[i] = new Filosofos(i, janta); //Inicializa todos os filósofos;
			if (i == 0) 
				filosofos[i].situacaoInicial(); //Caso seja o primeiro filósofo, atribui 0 a todas as posições do vetor de situação
			filosofos[i].start();
		}
	}
}