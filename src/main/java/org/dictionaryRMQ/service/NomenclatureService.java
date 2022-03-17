package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.Nomenclature;
import org.dictionaryRMQ.repository.NomenclatureRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NomenclatureService {
    private final NomenclatureRepository nomenclatureRepository;

    public void save(Nomenclature nomenclature) {
        nomenclatureRepository.save(nomenclature);
    }

    public void processing(Document document) {
        Nomenclature nomenclature = new Nomenclature();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclature.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        nomenclature.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        nomenclature.setDelete_mark(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Код
        nomenclature.setCode(document.getElementsByAttributeValue("name", "Код").text());
        //Наименование
        nomenclature.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //МА ИД
        nomenclature.setMa_id(PublicService.parseInt(document.getElementsByAttributeValue("name", "ИдМА").text()));
        //Полное наименование
        nomenclature.setFull_name(document.getElementsByAttributeValue("name", "ПолноеНаименование").text());
        //КодМА
        nomenclature.setMa_code(PublicService.parseInt(document.getElementsByAttributeValue("name", "КодМА").text()));
        //ЗаводПроизводитель
        Document producer = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "ЗаводПроизводитель").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclature.setProducer_ref(PublicService.uuidFromString(producer.getElementsByAttributeValue("name", "UID").text()));
        //НеИспользуемаяНоменклатура
        nomenclature.setNot_in_use(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "НеИспользуемаяНоменклатура").text()));
        //СпецГруппа
        Document specGroup = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "СпецГруппа").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclature.setSpec_group_ref(PublicService.uuidFromString(specGroup.getElementsByAttributeValue("name", "UID").text()));
        //МНН
        Document mnn = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "МНН").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclature.setMnn_ref(PublicService.uuidFromString(mnn.getElementsByAttributeValue("name", "UID").text()));
        //Лекарственная форма
        Document drugForm = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "ЛекарственнаяФорма").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclature.setDrug_form_ref(PublicService.uuidFromString(drugForm.getElementsByAttributeValue("name", "UID").text()));
        //СтавкаНДС
        nomenclature.setVat(document.getElementsByAttributeValue("name", "СтавкаНДС").text());
        //Новое наименование
        nomenclature.setNew_name(document.getElementsByAttributeValue("name", "НовоеНаименование").text());
        //ЖНВЛП
        nomenclature.setJnvlp(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ЖНВЛП").text()));
        //ПризнакПС
        nomenclature.set_ps(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПризнакПС").text()));
        //ВАрхиве
        nomenclature.set_archive(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ВАрхиве").text()));
        //НоваяНоменклатура
        nomenclature.set_new(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "НоваяНоменклатура").text()));
        //Кластер
        nomenclature.setCluster(document.getElementsByAttributeValue("name", "Кластер").text());
        save(nomenclature);

        Application.log.info("Сохранение номенклатуры: " + nomenclature.getId() + " "
                + nomenclature.getName());
    }
}
