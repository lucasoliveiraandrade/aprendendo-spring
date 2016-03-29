package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	public String salvarArquivo(String pastaBase, MultipartFile arquivo){
		try {
			String realPath = httpServletRequest.getServletContext().getRealPath("/" + pastaBase);
			
			File file = new File(realPath + "/" + arquivo.getOriginalFilename());			
			arquivo.transferTo(file);
			return pastaBase + "/" + arquivo.getOriginalFilename();
		} 
		catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}		
	}	
}