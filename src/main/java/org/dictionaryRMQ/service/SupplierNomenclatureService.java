package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.SupplierNomenclature;
import org.dictionaryRMQ.repository.SupplierNomenclatureRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierNomenclatureService {
    private final SupplierNomenclatureRepository supplierNomenclatureRepository;

    public void save(SupplierNomenclature sn) {
        supplierNomenclatureRepository.save(sn);
    }

    public void processing(Document document) {
        SupplierNomenclature supplierNomenclature = new SupplierNomenclature();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        supplierNomenclature.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Владелец
        Document owner = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Владелец").first().toString()
                , ""
                , Parser.xmlParser());
        supplierNomenclature.setOwner_ref(PublicService.uuidFromString(owner.getElementsByAttributeValue("name", "UID").text()));
        //Наименование
        supplierNomenclature.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //НаименованиеЗаводаПроизводителяПоставщика
        supplierNomenclature.setSupplies_name(document.getElementsByAttributeValue("name", "НаименованиеЗаводаПроизводителяПоставщика").text());
        //СсылкаНеИмеетТТ
        supplierNomenclature.setHas_no_tt(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "СсылкаНеИмеетТТ").text()));

        save(supplierNomenclature);

        Application.log.info("Сохранение номенклатуры поставщика: " + supplierNomenclature.getId() + " " + supplierNomenclature.getName());

    }
}
