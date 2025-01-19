package br.com.pegasus.lib.method.crypter;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class GenCripter {

	public static void main(String[] args) throws Exception {
		String chaveAcesso = "xxxxxxxxxx";
		CryptoService crypt = new CryptoService(chaveAcesso);

		String texto = "texto a ser criptografado";
		String sEncrypt = crypt.encrypt(texto);
		String sDecrypt = crypt.decript(sEncrypt);

		System.out.println("Dado encriptografado :: " + sEncrypt);
		System.out.println("Dado descriptografado :: " + sDecrypt);
	}

	interface Const {
		byte[] ACCEPTABLE_KEY_SIZE = new byte[] { 10, 11, 12, 16, 17, 18, 22, 23, 24 };
		String MSG_ERROR = "Tamanho incorreto da chave criptográfica. Tamanhos aceitáveis { 10, 11, 12, 16, 17, 18, 22, 23, 24 }";
		String ALGORITHM = "AES";
	}

	public static class CryptoService {
		private Cipher encrypt = null;
		private Cipher decrypt = null;

		public CryptoService(String chave) throws Exception {
			int length = chave.length();
			for (int i : Const.ACCEPTABLE_KEY_SIZE) {
				if (length == i) {
					// Ajuste na 'chave' para garantir um formato seguro e padronizado (Base64)
					chave = new String(Base64.encodeBase64(chave.getBytes()));
					Key key = new SecretKeySpec(chave.getBytes(), Const.ALGORITHM);
					decrypt = createCipher(key, Cipher.DECRYPT_MODE);
					encrypt = createCipher(key, Cipher.ENCRYPT_MODE);
					return;
				}
			}
			throw new RuntimeException(Const.MSG_ERROR);
		}

		public String encrypt(String text) throws Exception {
			// converte o texto criptografado para Base64 facilitando o transporte,
			// armazenamento e posterior decodificação.
			return new String(Base64.encodeBase64(encrypt.doFinal(text.getBytes())));
		}

		public String decript(String text) throws Exception {
			return new String(decrypt.doFinal(Base64.decodeBase64(text)));
		}

		// PRIVATE
		private static Cipher createCipher(Key key, int cryptMode) throws Exception {
			Cipher cipher = Cipher.getInstance(Const.ALGORITHM);
			cipher.init(cryptMode, key);
			return cipher;
		}
	}

}