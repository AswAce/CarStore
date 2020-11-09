package Shop.Cars.ApplicaitonRunner;


import Shop.Cars.Services.SeedDto.Cars.CarsRootDto;
import Shop.Cars.Services.SeedDto.Customer.CustomerRootDto;
import Shop.Cars.Services.SeedDto.Parts.PartsRootDto;
import Shop.Cars.Services.SeedDto.Suppliers.SupplierRootDto;
import Shop.Cars.Services.Service.*;
import Shop.Cars.Services.ViewDTO.CarRootDtoView;
import Shop.Cars.Services.ViewDTO.CustomerDtoView;
import Shop.Cars.Services.ViewDTO.CustomerRootDtoView;
import Shop.Cars.Utils.XMLParserImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.*;

import static Shop.Cars.FilesPath.ExportPath.CARS_TOYOTA;
import static Shop.Cars.FilesPath.ExportPath.ORDERED_CUSTOMERS;
import static Shop.Cars.FilesPath.FilesPath.*;


@Component

public class AppStarter implements CommandLineRunner {


    private final SupplierServiceImpl supplierService;
    private final XMLParserImpl xmlParser;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public AppStarter(SupplierServiceImpl supplierService, XMLParserImpl xmlParser, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
//Here the app is running and the methods for new tasks are written.
        this.supplierService = supplierService;
        this.xmlParser = xmlParser;
        this.partService = partService;
        this.carService = carService;

        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedSuppliers();
//        seedParts();
//        seedCars();
//        seedCustomers();
//        this.saleService.createSale();
        exportCustomers();
        this.getCarsForBrandToyota();
    }

    //Add suppliers from xml file
    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierRootDto supplierRootDto =
                this.xmlParser.importFromXML(SupplierRootDto.class, SUPPLIERS_PATH);

        this.supplierService.insertSuppliers(supplierRootDto);

    }

    //Add part from xml file
    private void seedParts() throws JAXBException, FileNotFoundException {
        PartsRootDto partsRootDto =
                this.xmlParser.importFromXML(PartsRootDto.class, PARTS_PATH);
        this.partService.importParts(partsRootDto);
    }

    //Add cars from xml file
    private void seedCars() throws JAXBException, FileNotFoundException {
        CarsRootDto cars = this.xmlParser.importFromXML(CarsRootDto.class, CARS_PATH);
        this.carService.insertCars(cars);
    }
    //Add customers from xml file
    private void seedCustomers() throws JAXBException, FileNotFoundException {

        CustomerRootDto customers = this.xmlParser.importFromXML(CustomerRootDto.class, CUSTOMERS_PATH);

        this.customerService.insertAllCustomers(customers);
    }

    //Get customers from DB to hml files
    private void exportCustomers() throws JAXBException, IOException {
        CustomerRootDtoView allOrderedByDate = this.customerService.getAllOrderedByDate();


        JAXBContext context = JAXBContext.newInstance(CustomerRootDtoView.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        OutputStream outputStream = new FileOutputStream(ORDERED_CUSTOMERS);
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(outputStream));
        jaxbMarshaller.marshal(allOrderedByDate, bfw);
    }

    //Get cars from DB to hml files
    private void getCarsForBrandToyota() throws JAXBException, IOException {

        CarRootDtoView toyotaCars = this.carService.getCarsFromBrandOrderByModel("Toyota");

        JAXBContext context = JAXBContext.newInstance(CarRootDtoView.class);
        Marshaller jaxbMarshaller = context.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        OutputStream outputStream = new FileOutputStream(CARS_TOYOTA);
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(outputStream));
        jaxbMarshaller.marshal(toyotaCars, bfw);


    }
}
