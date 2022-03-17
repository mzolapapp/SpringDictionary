package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.entity.PriceElasticityOfDemand;
import org.dictionaryRMQ.repository.PriceElasticityOfDemandRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceElasticityOfDemandService {
    private final PriceElasticityOfDemandRepository pedRepository;

    public void save(PriceElasticityOfDemand ped) {
        pedRepository.save(ped);
    }

    public void processing(Document document) {
        PriceElasticityOfDemand ped = new PriceElasticityOfDemand();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        ped.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //name
        ped.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //product
        Document productdoc = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Товары").first().toString()
                , ""
                , Parser.xmlParser());
        Elements products = productdoc.getElementsByAttributeValue("xsi:type", "UUID");
        for (Element product : products) {
            ped.setProduct(PublicService.uuidFromString(product.text()));
            save(ped);
        }
    }

}
