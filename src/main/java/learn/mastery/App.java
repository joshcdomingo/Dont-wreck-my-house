package learn.mastery;
import learn.mastery.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@PropertySource("classpath:data.properties")
public class App {

    public static void main(String[] args) {
        configureWithXMLandRun();
    }

    // with xml
    private static void configureWithXMLandRun(){
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-config.xml");
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

}
