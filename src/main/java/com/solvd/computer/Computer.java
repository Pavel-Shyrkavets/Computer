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
import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.exceptions.ZeroOrTooManyUSDException;
import com.solvd.computer.interfaces.ITurn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public abstract class Computer implements ITurn {
    private final static int MAX_PRODUCER_NAME_LENGTH = 100;
    private final static int MAX_MODEL_NAME_LENGTH = 100;
    private final static int MAX_TYPE_NAME_LENGTH = 100;
    private final static BigDecimal MAX_PRICE_IN_USD = new BigDecimal("3500000.00");
    private final static String PRODUCER_NAME_MESSAGE =
            "The producer name length should not be equal to 0 "
            + "or exceed " + MAX_PRODUCER_NAME_LENGTH + " characters.";
    private final static String MODEL_NAME_MESSAGE =
            "The length of the model name should not be equal to 0 "
            + "or exceed " + MAX_MODEL_NAME_LENGTH + " characters.";
    private final static String TYPE_NAME_MESSAGE =
            "The length of the type name should not be equal to 0 "
            + "or exceed " + MAX_TYPE_NAME_LENGTH + " characters.";
    private final static String PRICE_MESSAGE =
            "The price should not be less than or equal to 0.00 "
            + "or exceed " + MAX_PRICE_IN_USD + " USD.";
    private final static Logger LOGGER = LogManager.getLogger(Computer.class);

    protected String producer = "";
    protected String model = "";
    protected String type = "";
    private OperatingSystem operatingSystem;
    private Display screen;
    private CPU processor;
    private GPU graphics;
    private RAM memory;
    private Storage storage;
    private Port ports;
    protected BigDecimal priceInUSD;
    protected boolean isTurnedOn = false;
    private ComputerType computerType;
    private Freshness freshness;
    private Model computerModel;
    private Producer computerProducer;
    private Target target;

    public Computer() {
        this.operatingSystem = new OperatingSystem();
        this.screen = new Display();
        this.processor = new CPU();
        this.graphics = new GPU();
        this.memory = new RAM();
        this.storage = new Storage();
        this.ports = new Port();
        this.priceInUSD = new BigDecimal("0.01");
    }

    public Computer(String producer, String model, String type, OperatingSystem
                    operatingSystem, Display screen, CPU processor, GPU graphics,
                    RAM memory, Storage storage, Port ports, BigDecimal priceInUSD,
                    boolean isTurnedOn) throws ZeroOrTooManyCharactersException,
                    ZeroOrTooManyUSDException {
        if (producer.isEmpty() || producer.length() > MAX_PRODUCER_NAME_LENGTH) {
            LOGGER.error(PRODUCER_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(PRODUCER_NAME_MESSAGE);
        }

        if (model.isEmpty() || model.length() > MAX_MODEL_NAME_LENGTH) {
            LOGGER.error(MODEL_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(MODEL_NAME_MESSAGE);
        }

        if (type.isEmpty() || type.length() > MAX_TYPE_NAME_LENGTH) {
            LOGGER.error(TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TYPE_NAME_MESSAGE);
        }

        if (priceInUSD.compareTo(new BigDecimal("0.00")) == 0
                || priceInUSD.compareTo(MAX_PRICE_IN_USD) > 0) {
            LOGGER.error(PRICE_MESSAGE);
            throw new ZeroOrTooManyUSDException(PRICE_MESSAGE);
        }

        this.producer = producer;
        this.model = model;
        this.type = type;
        this.operatingSystem = operatingSystem;
        this.screen = screen;
        this.processor = processor;
        this.graphics = graphics;
        this.memory = memory;
        this.storage = storage;
        this.ports = ports;
        this.priceInUSD = priceInUSD;
        this.isTurnedOn = isTurnedOn;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer)
            throws ZeroOrTooManyCharactersException {
        if (producer.isEmpty() || producer.length() > MAX_PRODUCER_NAME_LENGTH) {
            LOGGER.error(PRODUCER_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(PRODUCER_NAME_MESSAGE);
        }

        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws ZeroOrTooManyCharactersException {
        if (model.isEmpty() || model.length() > MAX_MODEL_NAME_LENGTH) {
            LOGGER.error(MODEL_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(MODEL_NAME_MESSAGE);
        }

        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ZeroOrTooManyCharactersException {
        if (type.isEmpty() || type.length() > MAX_TYPE_NAME_LENGTH) {
            LOGGER.error(TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(TYPE_NAME_MESSAGE);
        }

        this.type = type;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Display getScreen() {
        return screen;
    }

    public void setScreen(Display screen) {
        this.screen = screen;
    }

    public CPU getProcessor() {
        return processor;
    }

    public void setProcessor(CPU processor) {
        this.processor = processor;
    }

    public GPU getGraphics() {
        return graphics;
    }

    public void setGraphics(GPU graphics) {
        this.graphics = graphics;
    }

    public RAM getMemory() {
        return memory;
    }

    public void setMemory(RAM memory) {
        this.memory = memory;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Port getPorts() {
        return ports;
    }

    public void setPorts(Port ports) {
        this.ports = ports;
    }

    public BigDecimal getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(BigDecimal priceInUSD)
            throws ZeroOrTooManyUSDException {
        if (priceInUSD.compareTo(new BigDecimal("0.00")) == 0
                || priceInUSD.compareTo(MAX_PRICE_IN_USD) > 0) {
            LOGGER.error(PRICE_MESSAGE);
            throw new ZeroOrTooManyUSDException(PRICE_MESSAGE);
        }

        this.priceInUSD = priceInUSD;
    }

    public boolean getIsTurnedOn() {
        return isTurnedOn;
    }

    public void setIsTurnedOn(boolean turnedOn) {
        isTurnedOn = turnedOn;
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public Freshness getFreshness() {
        return freshness;
    }

    public void setFreshness(Freshness freshness) {
        this.freshness = freshness;
    }

    public Model getComputerModel() {
        return computerModel;
    }

    public void setComputerModel(Model computerModel) {
        this.computerModel = computerModel;
    }

    public Producer getComputerProducer() {
        return computerProducer;
    }

    public void setComputerProducer(Producer computerProducer) {
        this.computerProducer = computerProducer;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
