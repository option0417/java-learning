package op.sample.spring;

import java.io.IOException;

import op.sample.spring.bean_aware.Cashier;
import op.sample.spring.event.EventPublish;
import op.sample.spring.ext_resources.BannerLoader;
import op.sample.spring.factory_method.ProductCreator;
import op.sample.spring.product.AProductBean;
import op.sample.spring.product.ProductBooks;
import op.sample.spring.refbean.DataPrefixGenerator;
import op.sample.spring.refbean.SequenceGenerator;
import op.sample.spring.seqgenerator.SequenceService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpring { 
    public static void main(String[] args) throws IOException {
    	//Get bean definition from bean-config
    	ApplicationContext helloBeanContext = new FileSystemXmlApplicationContext("config/xml/hellobeans.xml");
    	ApplicationContext strBeanContext = new FileSystemXmlApplicationContext("config/xml/samplestringbean.xml");
    	ApplicationContext productBeanContext = new FileSystemXmlApplicationContext("config/xml/productbeans.xml");
    	ApplicationContext refBeanContext = new FileSystemXmlApplicationContext("config/xml/refBeans.xml");
    	ApplicationContext sequenceContext = new FileSystemXmlApplicationContext("config/xml/SeqGenerator.xml");
    	ApplicationContext factoryContext = new FileSystemXmlApplicationContext("config/xml/FactoryMethod.xml");
    	ApplicationContext cashierContext = new FileSystemXmlApplicationContext("config/xml/BeanAware.xml");
    	ApplicationContext bannerContext = new FileSystemXmlApplicationContext("config/xml/BannerLoader.xml");
    	ApplicationContext eventContext = new FileSystemXmlApplicationContext("config/xml/Event.xml");
    	
    	//Get bean from ApplicationContext and casting to HelloBean
        HelloBean hello = (HelloBean) helloBeanContext.getBean("helloBean");
        SampleStringBean _SampleStringBean = (SampleStringBean) strBeanContext.getBean("samplestringbean");
        AProductBean _ProductBooks = (ProductBooks)productBeanContext.getBean("ProductBean");
        DataPrefixGenerator _DataPrefixGenerator = (DataPrefixGenerator)refBeanContext.getBean("RefBean");
        SequenceGenerator _SequenceGenerator = (SequenceGenerator)refBeanContext.getBean("SequenceGenerator");
        SequenceService _sequenceSrevice = (SequenceService)sequenceContext.getBean("sequenceService");
        //ProductCreator _ProductCreator = (ProductCreator)factoryContext.getBean("aaa");
        Cashier _Cashier = (Cashier)cashierContext.getBean("cashier");
        BannerLoader _BannerLoader = (BannerLoader)bannerContext.getBean("banner");
        
        EventPublish _EventPublish = (EventPublish)eventContext.getBean("EventPublish");
        
        //Get String from bean's method and print it out
        System.out.println(hello.getHelloWord());
        System.out.println("------------------------------");
        
        System.out.println(_SampleStringBean.getStrng());
        System.out.println("------------------------------");
        
        _ProductBooks.showData();
        System.out.println("------------------------------");
        
        System.out.println( _DataPrefixGenerator.getPrefix() );
        System.out.println( _SequenceGenerator.getSequence() );
        System.out.println("------------------------------");
        
        System.out.println( _sequenceSrevice.generate("IT") );
        System.out.println( _sequenceSrevice.generate("IT") );
        System.out.println("------------------------------");
        AProductBean _ProductBooks2 = ProductCreator.createProduct("aaa");
        _ProductBooks2.showData();
        System.out.println("------------------------------");
        _Cashier.show();
        System.out.println("------------------------------");
        //the banner was show on the top
        System.out.println( _BannerLoader.getNum() );
        System.out.println("------------------------------");
        _EventPublish.checkout();
    } 
}