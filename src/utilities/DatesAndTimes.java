package utilities;

import static utilities.Constantes.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    
    // Converte uma data no formato "dd/mm/aaaa" em um objeto LocalDate
    public static LocalDate converteData(String data) {
        if (data.length() == 10)
            return LocalDate.parse(data, DateTimeFormatter.ofPattern(FORMATA_DATA));
        return null;
    }

    // Converte um objeto LocalDate em uma String no formato "dd/mm/aaaa"
    public static String toStringData(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern(FORMATA_DATA));
    }

    // Converte um objeto LocalTime em uma String no formato "HH:MM:SS"
    public static String toStringHora(LocalTime hora) {
        return hora.format(DateTimeFormatter.ofPattern(FORMATA_HORA));
    }
}
