package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.entity.PredefinedValues;
import org.dictionaryRMQ.repository.PredefinedValuesRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PredefinedValuesService {

    private final PredefinedValuesRepository predefinedValuesRepository;

    public void save(PredefinedValues predefinedValues){
        predefinedValuesRepository.save(predefinedValues);
    }
    public void processing(Document document){
        PredefinedValues predefinedValues = new PredefinedValues();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        predefinedValues.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //Код
        predefinedValues.setCode(document.getElementsByAttributeValue("name", "Код").text());
        //Наименование
        predefinedValues.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Значение
        predefinedValues.setValue(document.getElementsByAttributeValue("name", "Значение").text());

        save(predefinedValues);
    }
}
