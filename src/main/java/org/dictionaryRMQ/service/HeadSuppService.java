package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.HeadSupp;
import org.dictionaryRMQ.repository.HeadSuppRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HeadSuppService {
    private final HeadSuppRepository head_suppRepository;

    public void save(HeadSupp head_supp) {
        head_suppRepository.save(head_supp);
    }

    public void processing(Document document) {
        HeadSupp headSupp = new HeadSupp();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        headSupp.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Код
        headSupp.setCode(document.getElementsByAttributeValue("name", "Код").text());
        //Наименование
        headSupp.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Особый код поставщика
        headSupp.setSpecial_code(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ОсобыйКодПоставщика").text()));
        //закуп более 2-х процентов
        headSupp.setPurch_2_percent(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ЗакупБолее2Процентов").text()));
        save(headSupp);

        Application.log.info("Сохранение головного поставщика: " + headSupp.getId() + " "
                + headSupp.getName());
    }
}
