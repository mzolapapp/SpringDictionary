package org.dictionaryRMQ;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.service.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Application implements CommandLineRunner {
    public static Logger log = LoggerFactory.getLogger(Application.class);

    private final DivisionService divisionService;
    private final DivOnlineCompetService divOnlineCompetService;
    private final EmployeeService employeeService;
    private final HeadSuppService headSuppService;
    private final MNNService mnnService;
    private final NomenclatureService nomenclatureService;
    private final NomenclatureInfoService nomenclatureInfoService;
    private final OnlineCompetService onlineCompetService;
    private final PharmacyNetService pharmacyNetService;
    private final SpecGroupService specGroupService;
    private final SupplierService supplierService;
    private final SupplierNomenclatureService supplierNomenclatureService;
    private final SuppNomTTService suppNomTTService;
    private final PriceElasticityOfDemandService pedService;
    private final ElasticityDictionariesService elasticityDictionariesService;
    private final PredefinedValuesService predefinedValuesService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        File logs = new File(
                File.separator + "home" +
                        File.separator + "rmqDictionary" +
                        File.separator + "logs" +
                        File.separator + "logfile.log");
        logs.renameTo(new File(
                File.separator + "home" +
                        File.separator + "rmqDictionary" +
                        File.separator + "logs" +
                        File.separator + "logfile" + '.' + format.format(d) + ".log"));
    }

    @Override
    public void run(String... args) {

        File folder = new File(File.separator + "home" + File.separator + "rmqDictionary" +
                File.separator + "messages");

        ArrayList<String> filenames = new ArrayList<>();
        for (String filename : folder.list()) {
            filenames.add(folder + File.separator + filename);
        }
        for (String filename : filenames) {
            Application.log.info("Обработка файла: " + filename);
            try {
                Document document = Jsoup.parse(PublicService.readBufferedReader(filename), "", Parser.xmlParser());
                Element link = Jsoup.parse(document.getElementsByAttributeValue("name", "ПолноеИмяМД").text(), "", Parser.xmlParser());
                String documentType = link.text();
                switch (documentType) {
                    case "Справочник.Аптеки" -> divisionService.processing(document);
                    case "АптекиКонкурентыОнлайн" -> divOnlineCompetService.processing(document);
                    case "Справочник.ФизическиеЛица" -> employeeService.processing(document);
                    case "Справочник.ГоловныеПоставщики" -> headSuppService.processing(document);
                    case "Справочник.МНН" -> mnnService.processing(document);
                    case "Справочник.Номенклатура" -> nomenclatureService.processing(document);
                    case "Справочник.ХарактеристикиНоменклатуры" -> nomenclatureInfoService.processing(document);
                    case "Справочник.ОнлайнКонкуренты" -> onlineCompetService.processing(document);
                    case "Справочник.АптечныеСети" -> pharmacyNetService.processing(document);
                    case "Справочник.СпецГруппыНоменклатуры" -> specGroupService.processing(document);
                    case "Справочник.Поставщики" -> supplierService.processing(document);
                    case "Справочник.НоменклатураПоставщиков" -> supplierNomenclatureService.processing(document);
                    case "ТТНоменклатурыПоставщиков" -> suppNomTTService.processing(document);
                    case "Справочник.ЦеноваяЭластичностьТоваров" -> pedService.processing(document);
                    case "ЦеноваяЭластичностьТоваровПоАптекам" -> elasticityDictionariesService.processing(document);
                    case "Справочник.ПредопределенныеЗначения" -> predefinedValuesService.processing(document);
                    default -> {
                        File file = new File(filename);
                        log.info("Файл " + filename + " переносится в папку: substandardMessages " +
                                file.renameTo(new File(File.separator + "home" +
                                        File.separator + "rmqDictionary" +
                                        File.separator + "substandardMessages" +
                                        File.separator + file.getName())));
                    }
                }
            } catch (FileNotFoundException e) {
                Application.log.error("Файл " + filename + " не найден", e.fillInStackTrace());
            } catch (IOException | IllegalStateException | ArrayIndexOutOfBoundsException e) {
                File file = new File(filename);
                Application.log.error("Ошибка чтения файла: " +
                        file.renameTo(new File(File.separator + "home" +
                        File.separator + "rmqDictionary" +
                        File.separator + "substandardMessages" +
                        File.separator + file.getName())), e.fillInStackTrace());


            }

            File file = new File(filename);
            Application.log.info("Файл " + filename + "удаляется: " + file.delete());
        }
    }
}
