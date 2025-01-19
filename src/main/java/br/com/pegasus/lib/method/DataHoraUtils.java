package br.com.pegasus.lib.method;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataHoraUtils {

	/**
	 * Retorna o horário atual do sistema no formato HH:mm:ss, onde a hora é de 0 a
	 * 23h
	 *
	 * @return O horário atual do sistema no formato HH:mm:ss, onde a hora é de 0 a
	 *         23h
	 */
	public static final String getHoraAtual() {
		return (new SimpleDateFormat("HH:mm:ss")).format(new Date());
	}

	/**
	 * retorna a data atual do sistema no formato YYYY/MM/dd.
	 *
	 * @return A data atual do sistema no formato YYYY/MM/dd.
	 */
	public final static String getDataAtual() {
		return (new SimpleDateFormat("YYYY/MM/dd")).format(new Date());
	}

	/**
	 * Gera uma data futura baseada na data atual do sistema no formato YYYY/MM/dd.
	 *
	 * @param dias Os dia que serão adicionados na data atual.
	 * @return (Adata atual + dias) do futura YYYY/MM/dd.
	 */
	public static final String geraDataFutura(int dias) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_WEEK, dias);
		return (new SimpleDateFormat("YYYY/MM/dd")).format(c.getTime());
	}

}
