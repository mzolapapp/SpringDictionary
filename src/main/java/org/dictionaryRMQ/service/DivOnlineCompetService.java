package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.DivOnlineCompet;
import org.dictionaryRMQ.repository.DivOnlineCompetRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DivOnlineCompetService {

    private final DivOnlineCompetRepository divOnlineCompetRepository;

    public void save(DivOnlineCompet divOnlineCompet) {
        divOnlineCompetRepository.save(divOnlineCompet);
    }

    public void processing(Document document) {
        DivOnlineCompet divOnlineCompet = new DivOnlineCompet();
        //id
        divOnlineCompet.setId(PublicService.uuidFromString(document.getElementsByAttributeValue("name", "РегистраторИзменения").text()));
        //Аптека
        Document division = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Аптека").first().toString()
                , ""
                , Parser.xmlParser());
        divOnlineCompet.setMz_division_ref(PublicService.uuidFromString(division.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //Конкурент онлайн
        Document competitor = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "КонкурентОнлайн").first().toString()
                , ""
                , Parser.xmlParser());
        divOnlineCompet.setOnline_compet_ref(PublicService.uuidFromString(competitor.getElementsByAttributeValue("name", "UID").text()));
        //Субъект РФ
        divOnlineCompet.setSubject_rf(document.getElementsByAttributeValue("name", "СубъектРФ").text());
        //Населенный пункт для парсинга
        divOnlineCompet.setLocality_for_pars(document.getElementsByAttributeValue("name", "НаселенныйПунктДляПарсинга").text());
        //Населенный пункт
        divOnlineCompet.setLocality(document.getElementsByAttributeValue("name", "НаселенныйПункт").text());
        //Адрес аптеки конкурента
        divOnlineCompet.setAddr_div_compet(document.getElementsByAttributeValue("name", "АдресАптекиКонкурента").text());
        //Тип населенного пункта
        divOnlineCompet.setType_locality_full(document.getElementsByAttributeValue("name", "ТипНаселенногоПунктаНаименование").text());
        //Тип населенного пункта сокращение
        divOnlineCompet.setType_locality(document.getElementsByAttributeValue("name", "ТипНаселенногоПунктаСокращенноеНаименование").text());
        //Период
        divOnlineCompet.setPeriod(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "Период")
                        .text()
                        .replace('T', ' ')));
        save(divOnlineCompet);
        Application.log.info("Сохранение аптеки онлайн конкурента: " + divOnlineCompet.getId()
                + " " + divOnlineCompet.getAddr_div_compet());
    }
}
