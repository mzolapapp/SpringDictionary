package org.dictionaryRMQ.service;

import lombok.RequiredArgsConstructor;
import org.dictionaryRMQ.Application;
import org.dictionaryRMQ.entity.Employee;
import org.dictionaryRMQ.repository.EmployeeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void processing(Document document) {
        Employee employee = new Employee();
        //id
        Document id = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Ссылка").first().toString()
                , ""
                , Parser.xmlParser());
        employee.setId(PublicService.uuidFromString(id.getElementsByAttributeValue("name", "UID").text()));
        //Версия данных
        employee.setData_version(document.getElementsByAttributeValue("name", "ВерсияДанных").text());
        //Пометка удаления
        employee.setDelete_mark(Boolean.parseBoolean(document.getElementsByAttributeValue("name", "ПометкаУдаления").text()));
        //Код
        employee.setCode(PublicService.parseInt(document.getElementsByAttributeValue("name", "Код").text()));
        //Наименование
        employee.setName(document.getElementsByAttributeValue("name", "Наименование").text());
        //Дата приема
        employee.setHire_date(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "ДатаПриема")
                        .text()
                        .replace('T', ' ')));
        //Дата увольнения
        employee.setFree_date(PublicService
                .parseDatetime(document
                        .getElementsByAttributeValue("name", "ДатаУвольнения")
                        .text()
                        .replace('T', ' ')));
        //Должность
        employee.setPosition(document.getElementsByAttributeValue("name", "Должность").text());
        //Аптека
        Document division = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "Аптека").first().toString()
                , ""
                , Parser.xmlParser());
        employee.setDivision_ref(PublicService.uuidFromString(division.getElementsByAttributeValue("name", "UID").text()));
        //Аптечная сеть
        Document pharmacyNet = Jsoup.parse(document
                        .getElementsByAttributeValue("name", "АптечнаяСеть").first().toString()
                , ""
                , Parser.xmlParser());
        employee.setPharmacy_net_ref(PublicService.uuidFromString(pharmacyNet.getElementsByAttributeValue("name", "UID").text()));
        save(employee);

        Application.log.info("Сохранение сотрудника: " + employee.getId() + " "
                + employee.getName());
    }
}
