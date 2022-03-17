package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.Supplier;
import org.dictionaryRMQ.repository.SupplierRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void processing(Document document) {
        Supplier supplier = new Supplier();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        supplier.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        supplier.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        supplier.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Наименование
        supplier.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Код МА
        supplier.setMa_code(PublicService.parseInt(document.getElementsByAttributeValue("name", "КодМА").text()));
        //Код
        supplier.setCode(document.getElementsByAttributeValue("name", "Код").text());
        //Краткое наименование МА
        supplier.setShort_name(document.getElementsByAttributeValue("name", "КраткоеНаименование").text());

        save(supplier);

        Application.log.info("Сохранение поставщика: " + supplier.getId() + " " + supplier.getName());
    }
}
