package security;

import org.apache.tomcat.util.codec.binary.Base64;

public class Security {

	public String encode(String valor) {
		return Base64.encodeBase64String(valor.getBytes());
	}
	
	public String decode(String valor) {
		byte[] decode = Base64.decodeBase64(valor.getBytes());
		valor = new String(decode);
		return valor;
	}
}
