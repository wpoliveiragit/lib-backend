package br.com.pegasus.lib.method;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	/**
	 * Retorna todo o conteudo de um arquivo em formato de lista das linhas.
	 * 
	 * @param path Caminho/com/o/nome/do/arquivo.
	 * 
	 * @return Uma lista contendo as linhas do arquivo.
	 * 
	 * @create 23/04/2018.
	 * @modification 20/07/2024.
	 */
	public static final List<String> dataFileReader(String path) throws Exception {
		List<String> lines = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
			reader.close();
			return lines;
		} catch (Exception ex) {
			if (reader != null) {
				reader.close();
			}
			throw ex;
		}
	}

	/**
	 * Escreve na última linha de um arquivo. Caso o arquivo não exista, ele será
	 * criado.
	 * 
	 * @param append   recria o arquivo, se e somente se for false.
	 * @param fileName caminho/nomeDoArquivo.
	 * @param lines    a lista de linhas a serem escritas.
	 * 
	 * @create 23/04/2018.
	 * @modification 24/04/2024.
	 */
	public static final void writeLines(boolean append, String fileName, List<String> lines) throws Exception {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(fileName, append));
			for (String line : lines) {
				writer.println(line);
			}
			writer.close();
		} catch (Exception e) {
			if (writer != null) {
				writer.close();
			}
			throw e;
		}
	}

	/**
	 * Escreve na última linha de um arquivo. Caso o arquivo não exista, ele será
	 * criado.
	 * 
	 * @param append   recria o arquivo, se e somente se for false.
	 * @param fileName caminho/nomeDoArquivo.
	 * @param line     a linha a ser escrita.
	 * 
	 * @create 23/04/2018.
	 * @modification 24/04/2024.
	 */
	public static final void writeLine(boolean append, String fileName, String line) throws Exception {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileWriter(fileName, append));
			writer.println(line);
			writer.close();
		} catch (Exception e) {
			if (writer != null) {
				writer.close();
			}
			throw e;
		}
	}

}
