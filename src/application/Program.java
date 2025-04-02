package application;

import java.util.Locale;
import java.util.Scanner;

import services.BrazilInterestService;
import services.InterestService;
import services.UsaInterestService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Amount: ");
		double amount = sc.nextDouble();
		System.out.print("Months: ");
		int months = sc.nextInt();

		System.out.println();
		double payment;
		InterestService is = new BrazilInterestService(2.0);
		payment = is.payment(amount, months);

		System.out.println("Brazil - Payment after " + months + " months: ");
		System.out.println(String.format("%.2f", payment));

		System.out.println();
		InterestService usa = new UsaInterestService(1.0);
		payment = usa.payment(amount, months);

		System.out.println("USA - Payment after " + months + " months: ");
		System.out.println(String.format("%.2f", payment));

		sc.close();
	}
}
/*

### **Resumo**

 - **O método `payment` é um default method na interface `InterestService`.**
 - **As classes `BrazilInterestService` e `UsaInterestService` herdam esse método automaticamente.**
 - **Isso evita repetição de código, pois a lógica do cálculo de juros está centralizada na interface.**

 Isso demonstra como **default methods** permitem adicionar novos métodos às interfaces sem quebrar compatibilidade com classes existentes,
  facilitando a manutenção e reaproveitamento do código. 🚀


### **Explicação didática sobre Default Methods nesse programa Java**

 Em Java, **default methods** são métodos concretos (com implementação) dentro de interfaces.
  Eles permitem que uma interface tenha métodos com comportamento padrão, sem obrigar as classes que a implementam a sobrescrevê-los.

 Neste programa, a interface `InterestService` define um **default method** chamado `payment`,
  que calcula o montante final de um investimento após um certo número de meses.


### **Analisando a interface `InterestService`**

public interface InterestService {

	double getInterestRate();  // Método abstrato que obriga as classes a fornecerem a taxa de juros

	default double payment(double amount, int months) {  // Método com implementação padrão
		if (months < 1) {
			throw new InvalidParameterException("Months must be greater than zero");
		}
		return amount * Math.pow(1.0 + getInterestRate() / 100.0, months);
	}
}

 - O método `getInterestRate()` é **abstrato** e precisa ser implementado pelas classes concretas.
 - O método `payment()` é **default**,
  o que significa que ele já tem um comportamento definido e pode ser **usado diretamente** pelas classes que implementam a interface `InterestService`, sem necessidade de reimplementá-lo.


### **Como o Default Method é usado?**

 As classes `BrazilInterestService` e `UsaInterestService` **implementam** a interface `InterestService`,
  mas **não precisam sobrescrever o método `payment`**, pois ele já tem uma implementação na interface.

Exemplo de uma dessas classes:

public class BrazilInterestService implements InterestService {

	public double interestRate;

	public BrazilInterestService(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public double getInterestRate() {
		return interestRate;
	}
}

Essa classe **só precisa implementar** o método `getInterestRate()`, pois a lógica do cálculo já está definida no default method `payment`.


### **Como o método default é chamado?**
 No `main`, criamos instâncias de `BrazilInterestService` e `UsaInterestService` e chamamos o método `payment()` diretamente,
  sem que essas classes tenham implementado ele explicitamente.

InterestService is = new BrazilInterestService(2.0);
double payment = is.payment(amount, months);

Como `BrazilInterestService` implementa `InterestService`, ele **herda** a implementação padrão do método `payment()` da interface.

O mesmo acontece para `UsaInterestService`:

InterestService usa = new UsaInterestService(1.0);
payment = usa.payment(amount, months);

 */