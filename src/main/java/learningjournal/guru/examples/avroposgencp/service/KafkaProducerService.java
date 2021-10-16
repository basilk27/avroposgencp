package learningjournal.guru.examples.avroposgencp.service;

import learningjournal.guru.examples.avroposgencp.model.PosInvoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {
    @Value("${application.configs.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    private KafkaTemplate<String, PosInvoice> kafkaTemplate;

    public void sendMessage(PosInvoice invoice) {
        log.info(String.format("Producing Invoice No: %s", invoice.getInvoiceNumber()));
        kafkaTemplate.send(TOPIC_NAME, invoice.getStoreID(), invoice);
    }
}
