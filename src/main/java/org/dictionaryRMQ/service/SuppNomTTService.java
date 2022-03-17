package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.SuppNomTT;
import org.dictionaryRMQ.repository.SuppNomTTRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SuppNomTTService {
    private final SuppNomTTRepository suppNomTTRepository;

    public void save(SuppNomTT suppNomTT) {
        suppNomTTRepository.save(suppNomTT);
    }

    public void processing(Document document) {
        SuppNomTT suppNomTT = new SuppNomTT();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "НоменклатураПоставщика").first().toString()
                , ""
                , Parser.xmlParser());
        suppNomTT.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Номенклатура
        Document nomenclature = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Номенклатура").first().toString()
                , ""
                , Parser.xmlParser());
        suppNomTT.setNomenclature_ref(PublicService.uuidFromString(nomenclature.getElementsByAttributeValue("name", "UID").text()));

        save(suppNomTT);

        Application.log.info("Сохранение ТТ Номенклатуры Поставщиков " + suppNomTT.getId());
    }
}
