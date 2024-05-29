package utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DatesAndTimes {

	// Calcula os meses entre duas datas.
    public static int mesesEntre(LocalDate inicio, LocalDate fim) {
        int meses = 0;
        LocalDate proximo = inicio.plusMonths(1);

        while (!proximo.isAfter(fim)) {
            meses++;
            proximo = proximo.plusMonths(1);
        }
        return meses;
    }
    
    // Calcula os dias úteis entre duas datas. Desconsidera feriados.
    public static int diasUteis(LocalDate inicio, LocalDate fim) {
        int diasUteis = 0;
        LocalDate atual = inicio;

        while (!atual.isAfter(fim)) {
            if (atual.getDayOfWeek() != DayOfWeek.SATURDAY && atual.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasUteis++;
            }
            atual = atual.plusDays(1);
        }
        return diasUteis;
    }

}
