import java.util.Scanner;
import java.util.InputMismatchException;

public class Executor
{
	
	public static void main(String[] args)
	{
		int nFilosofos;
		Scanner aux = new Scanner(System.in); //Declara uma variável auxiliar da classe scanner para pegar o número de filósofos;
		
		do
		{
			try
			{
				nFilosofos = aux.nextInt(); //Recupera o valor digitado pelo usuário em formato int;
				break;
			}
			catch(InputMismatchException e) //Caso o valor digitado não seja inteiro gerará uma excessão
			{
				System.out.println("INSIRA UM NÚMERO INTEIRO!!!");
				aux.next();
			}

		}while(true);
		

		Jantar janta = new Jantar(nFilosofos); // Objeto de jantar para ser passado no construtor dos filósofos e todos executarem na mesma circunstância;
		Filosofos[] filosofos = new Filosofos[nFilosofos]; //Declara o vetor com o número de filósofos;

		for (int i = 0; i < nFilosofos; i++)
		{
			filosofos[i] = new Filosofos(i, janta, nFilosofos); //Inicializa todos os filósofos e passa o número total deles;
			if (i == 0) 
				filosofos[i].situacaoInicial(); //Caso seja o primeiro filósofo, atribui 0 a todas as posições do vetor de situação
			filosofos[i].start();
		}
	}
}