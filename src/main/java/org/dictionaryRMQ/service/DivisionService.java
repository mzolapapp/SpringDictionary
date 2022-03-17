package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.Division;
import org.dictionaryRMQ.repository.DivisionRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DivisionService {

    private final DivisionRepository divisionRepository;

    public void save(Division division) {

        divisionRepository.save(division);
    }

    public void processing(Document document) {
        Division division = new Division();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        division.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //Версия данных
        division.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        division.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Код
        division.setCode(document.getElementsByAttributeValue("name", "Код").text());
        //Наименование
        division.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Адрес МА
        division.setMa_address(document.getElementsByAttributeValue("name", "АдресМА").text());
        //Код МА
        division.setMa_code(document.getElementsByAttributeValue("name", "КодМА").text());
        // Наименование МА
        division.setMa_name(document.getElementsByAttributeValue("name", "НаименованиеМА").text());
        //Краткое наименование МА
        division.setMa_short_name(document.getElementsByAttributeValue("name", "КраткоеНаименованиеМА").text());
        //Дата открытия
        division.setOpen_date(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "ДатаОткрытия")
                        .text()
                        .replace('T', ' ')));
        //Дата закрытия
        division.setClose_date(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "ДатаЗакрытия")
                        .text()
                        .replace('T', ' ')));
        //Код ПИУ
        division.setPiu_code(document.getElementsByAttributeValue("name", "КодПИУ").text());
        //Аптечная сеть
        Document pharmacy_net = Jsoup.parse(document.getElementsByAttributeValue("name", "АптечнаяСеть").first().toString()
                , ""
                , Parser.xmlParser());
        division.setPharmacy_net_ref(PublicService.uuidFromString(pharmacy_net.getElementsByAttributeValue("name", "UID").text()));
        //География прайсов
        Document price_geography = Jsoup.parse(document.getElementsByAttributeValue("name", "ГеографияПрайсов").first().toString()
                , ""
                , Parser.xmlParser());
        division.setPrice_geography_ref(PublicService.uuidFromString(price_geography.getElementsByAttributeValue("xsi:type", "UUID").text()));
        //Номер аптеки
        division.setDivision_number(PublicService.parseDouble(document.getElementsByAttributeValue("name", "НомерАптеки").text()));
        //Юридическое лицо
        division.setEntity(document.getElementsByAttributeValue("name", "ЮридическоеЛицо").text());
        //Подключена к Симфонии
        division.setSymphony_enabled(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПодключенаКСимфонии").text()));
        //Автозаказ
        division.setAuto_order(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "Автозаказ").text()));
        //СубъектРФ
        Document subjectRF = Jsoup.parse(document.getElementsByAttributeValue("name", "СубъектРФ").first().toString()
                , ""
                , Parser.xmlParser());
        division.setSubjectRF(PublicService.uuidFromString(subjectRF.getElementsByAttributeValue("xsi:type", "UUID").text()));
        save(division);

        Application.log.info("Сохранение аптеки: " + division.getId() + " "
                + division.getName());

    }
}
