
package br.com.pw.cadpessoa.test;

import br.com.pw.cadpessoa.controller.PessoasValidation;
import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class PessoasValidationTest {
    
    @Test
    public void isEmailValidoTest(){
        //Cenário 1: Sem informar o e-mail
        assertTrue(PessoasValidation.isEmailValido(null));
        
        //Cenário 2: Com e-mail e sem final
        assertFalse(PessoasValidation.isEmailValido("pedro.weber.dev@"));
        
        //Cenário 3: Com e-mail e com o final
        assertTrue(PessoasValidation.isEmailValido("pedro.weber.dev@gmail.com"));
    }
    
    @Test
    public void isCPFValidoTest(){
        //Cenário 1: sem informar CPF
        assertFalse(PessoasValidation.isCPFValido(null));
        assertFalse(PessoasValidation.isCPFValido(""));
        
        //Cenário 2: com CPF correto
        assertTrue(PessoasValidation.isCPFValido("08087035950"));
        
        //Cenário 3: com CPF com numeros iguais
        assertFalse(PessoasValidation.isCPFValido("11111111111"));
        
        //Cenário 4: com CPF com numeros aleatórios
        assertFalse(PessoasValidation.isCPFValido("12345678922"));
        
    }
    
    @Test
    public void isDataNascimentoValidaTest(){
        //Cenário 1: Data de nascimento maior que hoje
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, +1);
        Date dataTest = c.getTime();

        assertFalse(PessoasValidation.isDataNascimentoValida(dataTest));
        
        //Cenário 2: Data de nascimento menor que hoje
        c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        dataTest = c.getTime();
        
        assertTrue(PessoasValidation.isDataNascimentoValida(dataTest));
        
        //Cenário 3: Data de nascimento igual a hoje
        c = Calendar.getInstance();
        dataTest = c.getTime();
        
        assertTrue(PessoasValidation.isDataNascimentoValida(dataTest));
        
        //Cenário 4: Data de nascimento não informada
        assertFalse(PessoasValidation.isDataNascimentoValida(null));
    }
    
}
