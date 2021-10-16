package learningjournal.guru.examples.avroposgencp;

import learningjournal.guru.examples.avroposgencp.service.KafkaProducerService;
import learningjournal.guru.examples.avroposgencp.service.datagenerator.InvoiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvroposgencpApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(AvroposgencpApplication.class, args);
  }

  @Autowired
  private KafkaProducerService producerService;

  @Autowired private InvoiceGenerator invoiceGenerator;

  @Value("${application.configs.invoice.count}")
  private int INVOICE_COUNT;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    for (int i = 0; i < INVOICE_COUNT; i++) {
      producerService.sendMessage(invoiceGenerator.getNextInvoice());
      Thread.sleep(1000);
    }
  }
}