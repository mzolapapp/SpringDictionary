package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.entity.ElasticityDictionaries;
import org.dictionaryRMQ.repository.ElasticityDictionariesRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElasticityDictionariesService {

    private final ElasticityDictionariesRepository elasticityDictionariesRepository;

    public void save(ElasticityDictionaries elasticityDictionaries) {
        elasticityDictionariesRepository.save(elasticityDictionaries);
    }

    public void processing(Document document) {
        ElasticityDictionaries elasticityDictionaries = new ElasticityDictionaries();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Аптека").first().toString()
                , ""
                , Parser.xmlParser());
        elasticityDictionaries.setDivision(PublicService.uuidFromString(id.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //productListElastic
        Document productListElastic = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "СписокТоваровЭластичный").first().toString()
                , ""
                , Parser.xmlParser());
        elasticityDictionaries.setProductListElastic(PublicService.uuidFromString(productListElastic.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //productListNonElastic
        Document productListNonElastic = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "СписокТоваровНеЭластичный").first().toString()
                , ""
                , Parser.xmlParser());
        elasticityDictionaries.setProductListNonElastic(PublicService
                .uuidFromString(productListNonElastic.getElementsByAttributeValue("xsi:type", "UUID").text()));

        save(elasticityDictionaries);
    }
}
