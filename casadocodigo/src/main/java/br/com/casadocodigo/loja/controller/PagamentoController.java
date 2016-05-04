package br.com.casadocodigo.loja.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
 
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender mailSender;
	
	private final String urlServiroPagamento = "http://book-payment.herokuapp.com/payment";
	
	// callable deixa a requisição assincrona por causa da chamado pro servidor de pagamento
	// @AuthenticationPrincipal manda o spring injetar o usuario logado na aplicação
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizarPagamento(@AuthenticationPrincipal Usuario usuario, RedirectAttributes redirectAttributes){ 
		return () -> {
			String response = "";
			
			try {
				response = restTemplate.postForObject(urlServiroPagamento, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
				enviaEmailFinalizacaoCompra(usuario);
			} catch (Exception e) {
				response = "Valor maior que o permitido";
				e.printStackTrace();
			}
			
			redirectAttributes.addFlashAttribute("mensagem", response);
			return new ModelAndView("redirect:/produtos");
		};
	}

	private void enviaEmailFinalizacaoCompra(Usuario usuario) {
		SimpleMailMessage emailMessage = new SimpleMailMessage();
		emailMessage.setSubject("Casa Do Codigo - Compra realizada com sucesso");
//		emailMessage.setTo(usuario.getEmail());
		emailMessage.setTo("lucas.andrade@gauge.com.br");
//		emailMessage.setText("Parabens, " + usuario.getNome() +  "! Sua compra no valor de " + carrinhoCompras.getTotal() + " foi efetuada com sucesso!");
		emailMessage.setText("Parabens, ! Sua compra no valor de foi efetuada com sucesso!");
		emailMessage.setFrom("lucas.andrade999@gmail.com");
		
		mailSender.send(emailMessage);
	}
}























