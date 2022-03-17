package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.OnlineCompet;
import org.dictionaryRMQ.repository.OnlineCompetRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OnlineCompetService {
    private final OnlineCompetRepository onlineCompetRepository;

    public void save(OnlineCompet oc) {
        onlineCompetRepository.save(oc);
    }

    public void processing(Document document) {
        OnlineCompet onlineCompet = new OnlineCompet();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        onlineCompet.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Наименование
        onlineCompet.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //АСКонкурента
        Document compet = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "АСКонкурента").first().toString()
                , ""
                , Parser.xmlParser());
        onlineCompet.setAs_compet(PublicService.uuidFromString(compet.getElementsByAttributeValue("name", "UID").text()));
        //Сайт
        onlineCompet.setSite(document.getElementsByAttributeValue("name", "Сайт").text());
        //ТипКонкурента
        onlineCompet.setType_compet(document.getElementsByAttributeValue("name", "Сайт").text());
        //ТипПродаж
        onlineCompet.setType_sale(document.getElementsByAttributeValue("name", "ТипПродаж").text());
        //ТипЦен
        onlineCompet.setType_price(document.getElementsByAttributeValue("name", "ТипЦен").text());
        //УмеемПарситьСайт
        onlineCompet.setCan_parse(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "УмеемПарситьСайт").text()));
        save(onlineCompet);

        Application.log.info("Сохранение онлайн конкурента: " + onlineCompet.getId() + " "
                + onlineCompet.getName());
    }
}
