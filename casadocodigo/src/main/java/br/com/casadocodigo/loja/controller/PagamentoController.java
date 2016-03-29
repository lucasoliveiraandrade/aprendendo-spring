package br.com.casadocodigo.loja.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
 
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String urlServiroPagamento = "http://book-payment.herokuapp.com/payment";
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizarPagamento(RedirectAttributes redirectAttributes){ // callable deixa a requisição assincrona por causa da chamado pro servidor de pagamento
		return () -> {
			String response = "";
			
			try {
				response = restTemplate.postForObject(urlServiroPagamento, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
			} catch (Exception e) {
				response = "Valor maior que o permitido";
				e.printStackTrace();
			}
			
			redirectAttributes.addFlashAttribute("mensagem", response);
			return new ModelAndView("redirect:/produtos");
		};
	}
}