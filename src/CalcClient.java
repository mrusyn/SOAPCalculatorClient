

import com.webService.ICalculator;
import com.webService.jaxws.Add;
import com.webService.jaxws.AddResponse;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


public class CalcClient {

    public static void main(String[] args) {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(ICalculator.class);
        factory.setAddress("http://localhost:8080/SOAPCalculator/soap?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        ICalculator client = (ICalculator) factory.create();
        Add request = new Add();
        
        request.setArg0(1);
        request.setArg1(10);
     
        int reply = client.add(request.getArg0(), request.getArg1());            
       
        AddResponse response = new AddResponse();
      
        response.setReturn(reply);
        System.out.println("Response: "+ response.getReturn());
    }

}
