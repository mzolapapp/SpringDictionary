package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.MNN;
import org.dictionaryRMQ.repository.MNNRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MNNService {
    private final MNNRepository mnnRepository;

    public void save(MNN mnn) {
        mnnRepository.save(mnn);
    }

    public void processing(Document document) {
        MNN mnn = new MNN();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        mnn.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        mnn.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        mnn.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Наименование
        mnn.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //полное наименование
        mnn.setFull_name(document.getElementsByAttributeValue("name", "ПолноеНаименование").text());
        //Краткое наименование
        mnn.setShort_name(document.getElementsByAttributeValue("name", "СокращенноеНаименование").text());
        save(mnn);
        Application.log.info("Сохранение МНН: " + mnn.getId() + " "
                + mnn.getName());
    }
}
