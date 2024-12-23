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

import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.exceptions.ZeroOrTooManyUSDException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public final class Laptop extends Computer implements Comparable<Laptop> {
    private final static int LAPTOP_MASK;
    private final static double MAX_WEIGHT_IN_GRAMS = 10000.00;
    private final static int MAX_COLOR_NAME_LENGTH = 100;
    private final static String WEIGHT_MESSAGE =
            "The weight should not be less than or equal to 0.00 "
            + "or exceed " + MAX_WEIGHT_IN_GRAMS + " grams.";
    private final static String COLOR_NAME_MESSAGE =
            "The length of the color name should not be equal to 0 "
            + "or exceed " + MAX_COLOR_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(Laptop.class);

    private boolean hasWirelessNetworking = true;
    private boolean hasIntegratedCamera = true;
    private boolean hasIntegratedKeyboard = true;
    private boolean hasIntegratedMicrophone = true;
    private boolean hasIntegratedSpeakers = true;
    private boolean hasTouchpad = true;
    private boolean hasTouchableScreen = false;
    private double weightInGrams = 0.00;
    private String color = "";
    private CPU laptopProcessor = null;

    static {
        LAPTOP_MASK = 10;
    }

    public Laptop() {
        super();
    }

    public Laptop(String producer, String model, String type, OperatingSystem
                  operatingSystem, Display screen, CPU processor, GPU graphics,
                  RAM memory, Storage storage, Port ports, BigDecimal priceInUSD,
                  boolean isTurnedOn, boolean hasWirelessNetworking,
                  boolean hasIntegratedCamera, boolean hasIntegratedMicrophone,
                  boolean hasIntegratedSpeakers, boolean hasIntegratedKeyboard,
                  boolean hasTouchpad, boolean hasTouchableScreen,
                  double weightInGrams, String color) throws ZeroOrTooManyCharactersException,
                  ZeroOrTooManyUSDException {
        super(producer, model, type, operatingSystem, screen, processor, graphics,
                memory, storage, ports, priceInUSD, isTurnedOn);

        if (weightInGrams <= 0.00 || weightInGrams > MAX_WEIGHT_IN_GRAMS) {
            LOGGER.info(WEIGHT_MESSAGE);
            System.exit(1);
        }

        if (color.isEmpty() || color.length() > MAX_COLOR_NAME_LENGTH) {
            LOGGER.error(COLOR_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(COLOR_NAME_MESSAGE);
        }

        this.hasWirelessNetworking = hasWirelessNetworking;
        this.hasIntegratedMicrophone = hasIntegratedMicrophone;
        this.hasIntegratedSpeakers = hasIntegratedSpeakers;
        this.hasIntegratedCamera = hasIntegratedCamera;
        this.hasIntegratedKeyboard = hasIntegratedKeyboard;
        this.hasTouchpad = hasTouchpad;
        this.hasTouchableScreen = hasTouchableScreen;
        this.weightInGrams = weightInGrams;
        this.color = color;
        this.laptopProcessor = processor;
    }

    public boolean getHasWirelessNetworking() {
        return hasWirelessNetworking;
    }

    public void setHasWirelessNetworking(boolean hasWirelessNetworking) {
        this.hasWirelessNetworking = hasWirelessNetworking;
    }

    public boolean getHasIntegratedCamera() {
        return hasIntegratedCamera;
    }

    public void setHasIntegratedCamera(boolean hasIntegratedCamera) {
        this.hasIntegratedCamera = hasIntegratedCamera;
    }

    public boolean getHasIntegratedKeyboard() {
        return hasIntegratedKeyboard;
    }

    public void setHasIntegratedKeyboard(boolean hasIntegratedKeyboard) {
        this.hasIntegratedKeyboard = hasIntegratedKeyboard;
    }

    public boolean getHasIntegratedMicrophone() {
        return hasIntegratedMicrophone;
    }

    public void setHasIntegratedMicrophone(boolean hasIntegratedMicrophone) {
        this.hasIntegratedMicrophone = hasIntegratedMicrophone;
    }

    public boolean getHasIntegratedSpeakers() {
        return hasIntegratedSpeakers;
    }

    public void setHasIntegratedSpeakers(boolean hasIntegratedSpeakers) {
        this.hasIntegratedSpeakers = hasIntegratedSpeakers;
    }

    public boolean getHasTouchpad() {
        return hasTouchpad;
    }

    public void setHasTouchpad(boolean hasTouchpad) {
        this.hasTouchpad = hasTouchpad;
    }

    public boolean getHasTouchableScreen() {
        return hasTouchableScreen;
    }

    public void setHasTouchableScreen(boolean hasTouchableScreen) {
        this.hasTouchableScreen = hasTouchableScreen;
    }

    public double getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(double weightInGrams) {
        if (weightInGrams <= 0.00 || weightInGrams > MAX_WEIGHT_IN_GRAMS) {
            LOGGER.info(WEIGHT_MESSAGE);
            System.exit(1);
        }

        this.weightInGrams = weightInGrams;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws ZeroOrTooManyCharactersException {
        if (color.isEmpty() || color.length() > MAX_COLOR_NAME_LENGTH) {
            LOGGER.error(COLOR_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(COLOR_NAME_MESSAGE);
        }

        this.color = color;
    }

    public void touchTouchpad() {
        if (this.hasTouchpad) {
            getOperatingSystem().useTouchpad();
        }
    }

    @Override
    public void turnOn() {
        if (!this.isTurnedOn) {
            LOGGER.info("The laptop is started.");
            getProcessor().use();
            getMemory().use();
            getPorts().use();
            getStorage().use();
            getGraphics().use();
            getScreen().use();
            getOperatingSystem().use();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        Laptop laptop = (Laptop) object;
        return this.getProcessor().equals(laptop.getProcessor())
                && this.getGraphics().equals(laptop.getGraphics())
                && this.getMemory().equals(laptop.getMemory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProcessor(), getGraphics(), getMemory(), LAPTOP_MASK);
    }

    @Override
    public int compareTo(Laptop o) {
        return this.laptopProcessor.getNumOfCores() - o.laptopProcessor.getNumOfCores();
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", " + getOperatingSystem() +
                ", " + getScreen() +
                ", " + getProcessor() +
                ", " + getGraphics() +
                ", " + getMemory() +
                ", " + getStorage() +
                ", " + getPorts() +
                ", priceInUSD=" + priceInUSD +
                ", isTurnedOn=" + isTurnedOn +
                ", hasWirelessNetworking=" + hasWirelessNetworking +
                ", hasIntegratedCamera=" + hasIntegratedCamera +
                ", hasIntegratedKeyboard=" + hasIntegratedKeyboard +
                ", hasIntegratedMicrophone=" + hasIntegratedMicrophone +
                ", hasIntegratedSpeakers=" + hasIntegratedSpeakers +
                ", hasTouchpad=" + hasTouchpad +
                ", hasTouchableScreen=" + hasTouchableScreen +
                ", weightInGrams=" + weightInGrams +
                ", color='" + color + '\'' +
                '}';
    }
}
