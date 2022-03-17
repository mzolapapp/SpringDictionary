package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.PharmacyNet;
import org.dictionaryRMQ.repository.PharmacyNetRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PharmacyNetService {
    private final PharmacyNetRepository pharmacyNetRepository;

    public void save(PharmacyNet pn) {
        pharmacyNetRepository.save(pn);
    }

    public void processing(Document document) {
        PharmacyNet pharmacyNet = new PharmacyNet();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        pharmacyNet.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        pharmacyNet.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        pharmacyNet.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Наименование
        pharmacyNet.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Регион
        Document region = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Владелец").first().toString()
                , ""
                , Parser.xmlParser());
        pharmacyNet.setRegion(PublicService.uuidFromString(region.getElementsByAttributeValue("name", "UID").text()));

        save(pharmacyNet);

        Application.log.info("Сохранение аптечной сети: " + pharmacyNet.getId() + " " + pharmacyNet.getName());
    }
}
