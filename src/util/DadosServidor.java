package util;

import javax.faces.context.FacesContext;

public class DadosServidor {
	
	public static String getUrlBase() {
		String url = FacesContext.getCurrentInstance().getExternalContext().getRequestScheme(); 
		url+= "://"+FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
		if(!FacesContext.getCurrentInstance().getExternalContext().getRequestServerName().equals("80")) {
			url+= ":"+FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
		}
		url+="/"+FacesContext.getCurrentInstance().getExternalContext().getContextName();
		return url;
	}
}
