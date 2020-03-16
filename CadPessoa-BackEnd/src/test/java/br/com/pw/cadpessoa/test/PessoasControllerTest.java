
package br.com.pw.cadpessoa.test;

import br.com.pw.cadpessoa.controller.api.PessoasController;
import br.com.pw.cadpessoa.entity.Pessoa;
import br.com.pw.cadpessoa.repository.Pessoas;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    PessoasController.class
})
public class PessoasControllerTest {
    
    //URL base
    private final String BASE_URL = "/pessoas";
    
    private ObjectMapper objectMapper;
    
    @Autowired
    private PessoasController restController;
    
    //Instância do MockMVC
    private MockMvc mockMvc;

    //Instância do mock repository
    @MockBean
    private Pessoas mockRepository;
    
    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }
    
    @Test
    public void adicionarTest() throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("08087035950");
        pessoa.setNome("Pedro Weber");
        pessoa.setDataNascimento(new Date());
        
        when(mockRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        
         mockMvc.perform(post(BASE_URL + "/v1")
                .content(objectMapper.writeValueAsString(pessoa))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("08087035950")))
                .andExpect(jsonPath("$.nome", is("Pedro Weber")));

        verify(mockRepository, times(1)).save(any(Pessoa.class));
    }
    
    @Test
    public void atualizarTest() throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("08087035950");
        pessoa.setNome("Pedro Weber");
        pessoa.setDataNascimento(new Date());
        pessoa.setEmail("pedro.weber@gmail.com");
        
        when(mockRepository.getOne("08087035950")).thenReturn(pessoa);
        when(mockRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        
         mockMvc.perform(put(BASE_URL + "/v1/08087035950")
                .content(objectMapper.writeValueAsString(pessoa))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("08087035950")))
                .andExpect(jsonPath("$.nome", is("Pedro Weber")));

    }
    
    @Test
    public void buscarTest() throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("08087035950");
        pessoa.setNome("Pedro Weber");
        pessoa.setDataNascimento(new Date());
        pessoa.setEmail("pedro.weber@gmail.com");
        
        when(mockRepository.getOne("08087035950")).thenReturn(pessoa);
        
         mockMvc.perform(get(BASE_URL + "/08087035950")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf", is("08087035950")))
                .andExpect(jsonPath("$.nome", is("Pedro Weber")));

    }
    
    @Test
    public void removerTest() throws Exception{
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("08087035950");
        pessoa.setNome("Pedro Weber");
        pessoa.setDataNascimento(new Date());
        pessoa.setEmail("pedro.weber@gmail.com");
        
        when(mockRepository.getOne("08087035950")).thenReturn(pessoa);
        
         mockMvc.perform(delete(BASE_URL + "/08087035950")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }
}
