/*
 * %W% %E% Pavel Shyrkavets
 *
 * Copyright (c) 2011-2024 Solvd, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Solvd,
 * Inc. ("Confidential Information.") You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Solvd.
 *
 * SOLVD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SOLVD SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.solvd.computer;

import com.solvd.computer.enums.*;
import com.solvd.computer.exceptions.*;
import com.solvd.computer.interfaces.IConsume;
import com.solvd.computer.interfaces.IFunction;
import com.solvd.computer.interfaces.IPredicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The Main class starts an application that creates 3 Laptop objects that are
 * descendants of the Computer class and compares them using the overridden
 * equals method as well as represents one of them by means of the overridden
 * toString method.
 *
 * @version    2.0 19 Dec 2024
 * @author     Pavel Shyrkavets
 */
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Path file = Path.of("logs/outputFile.txt");

        try (OutputStream out = Files.newOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            OperatingSystem operatingSystem = new OperatingSystem("Windows 10 Pro");
            Display screen = new Display(14.0, "1920x1080", "LCD touch screen");
            CPU processor = new CPU("Intel(R) Core(TM) i5-8365U CPU @ 1.60GHz 1.90 GHz",
                                    4, 1.90, true);
            CPU anotherProcessor =
                    new CPU("Intel(R) Core(TM) i5-8365X CPU @ 1.60GHz 1.90 GHz", 6, 1.90, true);
            GPU graphics = new GPU("Intel(R) UHD Graphics 620", 0, 1150, true);
            RAM memory = new RAM(16, 2133, "LPDDR3 RAM");
            Storage storage = new Storage(512, "SSD");
            Port ports = new Port(2, 1, 1, 1, 2);
            Laptop laptop = new Laptop("Dell", "Latitude 7400 2-in-1", "Laptop",
                                       operatingSystem, screen, processor, graphics, memory,
                                       storage, ports, new BigDecimal("300.00"), false, true,
                                       true, true, true, true, true, true, 1500, "gray");
            Laptop similarLaptop = new Laptop("Dell", "Latitude 7400 2-in-1", "Laptop",
                                              operatingSystem, screen, processor, graphics, memory,
                                              storage, ports, new BigDecimal("300.00"), false, true,
                                              true, true, true, true, true, true, 1500, "gray");
            Laptop anotherLaptop = new Laptop("Lenovo", "Latitude 7400 2-in-1", "Laptop",
                                              operatingSystem, screen, anotherProcessor, graphics, memory,
                                              storage, ports, new BigDecimal("300.00"), false, true,
                                              true, true, true, true, true, true, 1500, "gray");
            Laptop fourthLaptop = new Laptop("L", "La", "Lap",
                                             operatingSystem, screen, anotherProcessor, graphics, memory,
                                             storage, ports, new BigDecimal("1000.00"), false, true,
                                             true, true, true, true, true, true, 1500, "gray");
            List<Computer> laptops = new ArrayList<>();
            Set<Computer> uniqueLaptops;
            Deque<Computer> laptopsInDeque;
            Queue<Computer> queuedLaptops;
            CustomLinkedList<Computer> computers = new CustomLinkedList<>();

            LOGGER.info("laptop.equals(similarLaptop): {}", laptop.equals(similarLaptop));
            LOGGER.info("laptop.equals(anotherLaptop): {}", laptop.equals(anotherLaptop));
            writer.write("laptop.equals(similarLaptop): "
                         + laptop.equals(similarLaptop));
            writer.write("\nlaptop.equals(anotherLaptop): "
                         + laptop.equals(anotherLaptop));

            laptops.add(laptop);
            laptops.add(similarLaptop);
            laptops.add(anotherLaptop);
            //the uniqueLaptops elements are sorted by value in ascending order
            uniqueLaptops = new TreeSet<>(laptops);
            LOGGER.info("The number of the laptops: {}", laptops.size());
            LOGGER.info("The number of the unique laptops: {}", uniqueLaptops.size());
            writer.write("\nThe number of the laptops: " + laptops.size());
            writer.write("\nThe number of the unique laptops: " + uniqueLaptops.size());

            laptopsInDeque = new ArrayDeque<>(uniqueLaptops);
            LOGGER.info("The maximal number of cores in the given laptops: {}",
                        laptopsInDeque.getLast().getProcessor().getNumOfCores());
            writer.write("\nThe maximal number of cores in the given laptops: "
                         + laptopsInDeque.getLast().getProcessor().getNumOfCores());

            //the minimal element is in the beginning of queuedLaptops
            queuedLaptops = new ArrayDeque<>(laptopsInDeque);
            LOGGER.info("The minimal number of cores in the given laptops: {}",
                        queuedLaptops.element().getProcessor().getNumOfCores());
            writer.write("\nThe minimal number of cores in the given laptops: "
                         + queuedLaptops.element().getProcessor().getNumOfCores());

            laptops
                    .stream()
                    .collect(Collectors.groupingBy(Computer::getProducer))
                    .forEach((k, v) -> LOGGER.info(k + ": " + v));

            computers.addFirst(laptop);
            computers.addLast(similarLaptop);
            computers.addFirst(anotherLaptop);
            LOGGER.info("The first element in CustomLinkedList: {}",
                        computers.getFirst().getValue().toString());
            LOGGER.info("The last element in CustomLinkedList: {}",
                        computers.getLast().getValue().toString());
            writer.write("\nThe first element in CustomLinkedList: "
                         + computers.getFirst().getValue().toString());
            writer.write("\nThe last element in CustomLinkedList: "
                         + computers.getLast().getValue().toString());

            IFunction<String> producerName = (s) -> s;
            String greeting = producerName.run("Hi " + queuedLaptops.element().getProducer());
            LOGGER.info(greeting);
            writer.write("\n" + greeting);
            IConsume<String> modelName = LOGGER::info;
            modelName.consume(queuedLaptops.element().getModel());
            IPredicate<String> sameWord = (s) -> s.equals(queuedLaptops.element().getProducer());
            LOGGER.info(sameWord.predicate(queuedLaptops.element().getModel()));
            writer.write("\n" + sameWord.predicate(queuedLaptops.element().getModel()));

            fourthLaptop.setComputerProducer(Producer.SONY);
            fourthLaptop.setComputerModel(Model.VAIO);
            fourthLaptop.setComputerType(ComputerType.LAPTOP);
            fourthLaptop.setTarget(Target.BUSINESS);
            fourthLaptop.setFreshness(Freshness.NEW);
            LOGGER.info("The fourth laptop: {} {} {} {} {} {} USD",
                        fourthLaptop.getFreshness(), fourthLaptop.getTarget(),
                        fourthLaptop.getComputerProducer(), fourthLaptop.getComputerModel(),
                        fourthLaptop.getComputerType(), fourthLaptop.getPriceInUSD());
            writer.write("\nThe fourth laptop: " + fourthLaptop.getFreshness() + " "
                         + fourthLaptop.getTarget()+ " " + fourthLaptop.getComputerProducer() + " "
                         + fourthLaptop.getComputerModel() + " " + fourthLaptop.getComputerType() + " "
                         + fourthLaptop.getPriceInUSD() + " USD");

            Class<?> cpu = Class.forName("com.solvd.computer.CPU");
            Field[] cpuDeclaredFields = cpu.getDeclaredFields();
            Arrays
                    .stream(cpuDeclaredFields)
                    .forEach(field -> LOGGER.info("{} {} {}",
                                                        field.accessFlags(), field.getType(), field.getName()));
            Constructor<?>[] cpuDeclaredConstructors = cpu.getDeclaredConstructors();
            Arrays
                    .stream(cpuDeclaredConstructors)
                    .forEach(constructor ->
                            LOGGER.info("{} {} {} {}", constructor.accessFlags(), constructor.getName(),
                                        Arrays.toString(constructor.getParameterTypes()),
                                        Arrays.toString(constructor.getExceptionTypes())));
            Method[] cpuDeclaredMethods = cpu.getDeclaredMethods();
            Arrays
                    .stream(cpuDeclaredMethods)
                    .forEach(method -> LOGGER.info("{} {} {} {} {}", method.accessFlags(),
                                                           method.getReturnType(), method.getName(),
                                                           Arrays.toString(method.getParameterTypes()),
                                                           Arrays.toString(method.getExceptionTypes())));
            Constructor<?> constructor = Arrays
                    .stream(CPU.class.getDeclaredConstructors())
                    .filter(theConstructor -> theConstructor.getParameterTypes().length == 4)
                    .toList()
                    .getFirst();
            CPU aCPU = (CPU) constructor.newInstance("Super CPU", 16, 10.00, true);
            LOGGER.info("The CPU that is created using reflection has {} cores.",
                        aCPU.getNumOfCores());
            Method use = CPU.class.getDeclaredMethod("use");
            use.invoke(aCPU);
            Arrays
                    .stream(CPU.class.getInterfaces())
                    .forEach(theInterface -> LOGGER.info(theInterface.getName()));

            new Thread(new CustomRunnable()).start();
            new CustomThread().start();
        } catch (ZeroOrTooManyCharactersException ex1) {
            LOGGER.error("ZeroOrTooManyCharactersException is caught.");
        } catch (NoOneOrTooManyInchesException ex2) {
            LOGGER.error("NoOneOrTooManyInchesException is caught.");
        } catch (NoOneOrTooManyCoresException ex3) {
            LOGGER.error("NoOneOrTooManyCoresException is caught.");
        } catch (NoOneOrTooManyHzException ex4) {
            LOGGER.error("NoOneOrTooManyHzException is caught.");
        } catch (ZeroOrTooManyUSDException ex5) {
            LOGGER.error("ZeroOrTooManyUSDException is caught.");
        } catch (IOException ex6) {
            LOGGER.error("IOException is caught.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException is caught.");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException is caught.");
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException is caught.");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException is caught.");
        } catch (InstantiationException e) {
            LOGGER.error("InstantiationException is caught.");
        }
    }
}
