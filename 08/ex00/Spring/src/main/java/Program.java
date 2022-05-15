import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preproc.PreProcessor;
import preproc.PreProcessorToLowerImpl;
import printer.PrinterWithPrefixImpl;
import renderer.Renderer;
import renderer.RendererErrImpl;

public class Program {
    public static void main(String[] args) {
//
//        System.out.println("Hello world!") ;
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        PrinterWithPrefixImpl printer = applicationContext.getBean("printer", PrinterWithPrefixImpl.class);

        printer.setPrefix("Prefix");
        printer.print("Hello!");

    }
}