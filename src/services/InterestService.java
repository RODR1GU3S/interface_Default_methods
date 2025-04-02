package services;

import java.security.InvalidParameterException;

public interface InterestService {

//  Método abstrato que obriga as classes concretas a fornecerem a taxa de juros
    double getInterestRate();

//  Método default: fornece uma implementação padrão para o cálculo do pagamento
//  As classes concretas não precisam reimplementá-lo, garantindo reutilização e consistência
    default double payment(double amount, int months) {
        if (months < 1) {
            throw new InvalidParameterException("Months must be greater than zero");
        }
        return amount * Math.pow(1.0 + getInterestRate() / 100.0, months);
    }
}
/*
   O método getInterestRate() é abstrato e precisa ser implementado pelas classes concretas.

   O método payment() é default, o que significa que ele já tem um comportamento definido
    e pode ser usado diretamente pelas classes que implementam a interface InterestService,
     sem necessidade de reimplementá-lo.
     
*/





