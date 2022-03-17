package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.SpecGroup;
import org.dictionaryRMQ.repository.SpecGroupRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SpecGroupService {
    private final SpecGroupRepository specGroupRepository;

    public void save(SpecGroup specGroup) {
        specGroupRepository.save(specGroup);
    }

    public void processing(Document document) {
        SpecGroup specGroup = new SpecGroup();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        specGroup.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        specGroup.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        specGroup.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Наименование
        specGroup.setName(document.getElementsByAttributeValue("name", "Наименование").text());

        save(specGroup);

        Application.log.info("Сохранение спецгруппы: " + specGroup.getId() + " " + specGroup.getName());

    }
}
