package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.NomenclatureInfo;
import org.dictionaryRMQ.repository.NomenclatureInfoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NomenclatureInfoService {
    private final NomenclatureInfoRepository nomenclatureInfoRepository;

    public void save(NomenclatureInfo ni) {
        nomenclatureInfoRepository.save(ni);
    }

    public void processing(Document document) {
        NomenclatureInfo nomenclatureInfo = new NomenclatureInfo();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclatureInfo.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Номенклатура
        Document nomenclature = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Владелец").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclatureInfo.setNomenclature_ref(PublicService.uuidFromString(nomenclature.getElementsByAttributeValue("name", "UID").text()));
        //Аптека
        Document division = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "мзАптека").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclatureInfo.setDivision_ref(PublicService.uuidFromString(division.getElementsByAttributeValue("name", "UID").text()));
        //Поставщик
        Document supplier = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "мзКонтрагент").first().toString()
                , ""
                , Parser.xmlParser());
        nomenclatureInfo.setSupplier_ref(PublicService.uuidFromString(supplier.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        nomenclatureInfo.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Наименование
        nomenclatureInfo.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //серия производителя
        nomenclatureInfo.setMz_producer_series(document.getElementsByAttributeValue("name", "мзСерияПроизводителя").text());
        //страна производителя
        nomenclatureInfo.setMz_producer_country(document.getElementsByAttributeValue("name", "мзСтранаПроизводителя").text());
        //производитель партии
        nomenclatureInfo.setMz_producer(document.getElementsByAttributeValue("name", "мзПроизводительПартии").text());
        //описание
        nomenclatureInfo.setMz_description(document.getElementsByAttributeValue("name", "мзОписание").text());
        //ставка НДС
        nomenclatureInfo.setMz_vat_percent(document.getElementsByAttributeValue("name", "мзСтавкаНДС").text());
        //штрих код
        nomenclatureInfo.setMz_scan_code(document.getElementsByAttributeValue("name", "мзШтрихкод").text());
        //штрихкод производителя
        nomenclatureInfo.setMz_producer_scan_code(document.getElementsByAttributeValue("name", "мзШтрихкодЗШК").text());
        //Пометка удаления
        nomenclatureInfo.setDelete_mark(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        // Это комиссия
        nomenclatureInfo.setMz_is_commission(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзЭтоКомиссия").text()));
        //это интернет магазин
        nomenclatureInfo.setMz_is_internet_product(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзТоварИМ").text()));
        //в списке ЦП
        nomenclatureInfo.setMz_is_product_cp(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзВСпискеЦП").text()));
        //копия
        nomenclatureInfo.setMz_is_copy(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзКопия").text()));
        //маркировка
        nomenclatureInfo.setMarked_drug(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "МаркированныйЛП").text()));
        //бывший товар интернет магазина
        nomenclatureInfo.setMz_was_internet_product(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзБывшийТоварИМ").text()));
        //изменен срок годности
        nomenclatureInfo.setMz_use_before_changed(
                Boolean.parseBoolean(document.getElementsByAttributeValue("name", "мзИзмененСрокГодности").text()));
        //цена закупа
        nomenclatureInfo.setMz_income_price(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаЗакупа").text()));
        //цена производителя без НДС
        nomenclatureInfo.setMz_producer_price_no_vat(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаПроизводителяБезНДС").text()));
        //Цена производителя с ндс
        nomenclatureInfo.setMz_producer_price_vat(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаПроизводителяСНДС").text()));
        //цена поставщика без НДС
        nomenclatureInfo.setMz_supplier_price_no_vat(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаПоставщикаБезНДС").text()));
        //цена ЖНВЛП
        nomenclatureInfo.setMz_jnvlp_price(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаРеестраЖНВЛП").text()));
        //цена интернет магазина
        nomenclatureInfo.setMz_internet_sale_price(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаПродажиИМ").text()));
        //цена по тендеру
        nomenclatureInfo.setMz_tender_price(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзЦенаПоТендеру").text()));
        //делитель фасовки
        nomenclatureInfo.setMz_packing_divider(
                PublicService.parseDouble(document.getElementsByAttributeValue("name", "мзДелительФасовки").text()));
        //метка
        nomenclatureInfo.setMz_mark(
                PublicService.parseInt(document.getElementsByAttributeValue("name", "мзМетка").text()));
        //Годен до
        nomenclatureInfo.setMz_use_before(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "мзГоденДо")
                        .text()
                        .replace('T', ' ')));
        save(nomenclatureInfo);

        Application.log.info("Сохранение характеристики номенклатуры " + nomenclatureInfo.getId() + " "
                + nomenclatureInfo.getName());

    }
}
