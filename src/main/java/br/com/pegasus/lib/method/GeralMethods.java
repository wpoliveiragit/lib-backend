package br.com.pegasus.lib.method;

import java.io.File;
import java.io.IOException;

public class GeralMethods {

	/**
	 * Abre uma janela ou um programa.
	 *
	 * @param path Uma instancia de onde fica o arquivo.
	 */
	public static final void abrePastaPrograma(File path) {
		try {
			Runtime.getRuntime().exec("explorer " + path.getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("[ERRO] problemas em abrir o caminho \"" + path.getAbsolutePath() + "\"");
		}
	}

	/**
	 * Retorna a url do arquivo.
	 * 
	 * @param nomeArquivo o nome do arquivo.
	 * @return A url do arquivo ou null se e somente se o arquivo nao existir dentro
	 *         do projeto.
	 */
	public static final String getURL(String nomeArquivo) {
		return procuraArq(new File(new File("").getAbsolutePath()), nomeArquivo);

	}

	/**
	 * Busca o arquivo dentro do caminho do parametro, caso encontre, salvara a url
	 * na variavel estatica url
	 * 
	 * @param path        o caminho a ser verificado.
	 * @param nomeArquivo o nome do arquivo.
	 * @return true se e somente se encontrar o arquivo.
	 */
	public static final String procuraArq(File path, String nomeArquivo) {
		if (path.isDirectory()) {
			String[] listaItens = path.list();
			String url;
			for (String item : listaItens) {
				url = procuraArq(new File(path, item), nomeArquivo);
				if (url != null) {
					return url;
				}
			}
		}
		return path.getName().equalsIgnoreCase(nomeArquivo) ? path.getAbsolutePath() : null;
	}

}
