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

import com.solvd.computer.exceptions.NoOneOrTooManyCoresException;
import com.solvd.computer.exceptions.NoOneOrTooManyHzException;
import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.interfaces.IBoost;
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class CPU implements IBoost, IUse {
    private final static int CPU_MASK = 20;
    private final static int MAX_CPU_NAME_LENGTH = 100;
    private final static int MAX_NUMBER_OF_CORES = 50;
    private final static double MAX_CLOCK_RATE_IN_GHZ = 20.00;
    private final static String CPU_NAME_MESSAGE =
            "The CPU name length should not be equal to 0 "
            + "or exceed " + MAX_CPU_NAME_LENGTH + " characters.";
    private final static String CORES_MESSAGE =
            "The number of the cores should not be less than "
            + "or equal to 0 or exceed " + MAX_NUMBER_OF_CORES + " chores.";
    private final static String CLOCK_RATE_MESSAGE =
            "The CPU clock rate should not be less than or equal to 0 "
            + "or exceed " + MAX_CLOCK_RATE_IN_GHZ + " GHz.";
    private final static Logger LOGGER = LogManager.getLogger(CPU.class);

    private String name = "";
    private int numOfCores = 1;
    private double clockRateInGHz = 0.00;
    private boolean hasTurboBoost = false;

    public CPU() {}

    public CPU(String name, int numOfCores, double clockRateInGHz,
               boolean hasTurboBoost) throws ZeroOrTooManyCharactersException,
               NoOneOrTooManyCoresException, NoOneOrTooManyHzException {
        if (name.isEmpty() || name.length() > MAX_CPU_NAME_LENGTH) {
            LOGGER.error(CPU_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(CPU_NAME_MESSAGE);
        }

        if (numOfCores <= 0 || numOfCores > MAX_NUMBER_OF_CORES) {
            LOGGER.error(CORES_MESSAGE);
            throw new NoOneOrTooManyCoresException(CORES_MESSAGE);
        }

        if (clockRateInGHz <= 0.00 || clockRateInGHz > MAX_CLOCK_RATE_IN_GHZ) {
            LOGGER.error(CLOCK_RATE_MESSAGE);
            throw new NoOneOrTooManyHzException(CLOCK_RATE_MESSAGE);
        }

        this.name = name;
        this.numOfCores = numOfCores;
        this.clockRateInGHz = clockRateInGHz;
        this.hasTurboBoost = hasTurboBoost;
    }

    public static double getMaxClockRateInGhz() {
        return MAX_CLOCK_RATE_IN_GHZ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_CPU_NAME_LENGTH) {
            LOGGER.error(CPU_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(CPU_NAME_MESSAGE);
        }

        this.name = name;
    }

    public int getNumOfCores() {
        return numOfCores;
    }

    public void setNumOfCores(int numOfCores) throws NoOneOrTooManyCoresException {
        if (numOfCores <= 0 || numOfCores > MAX_NUMBER_OF_CORES) {
            LOGGER.error(CORES_MESSAGE);
            throw new NoOneOrTooManyCoresException(CORES_MESSAGE);
        }

        this.numOfCores = numOfCores;
    }

    public double getClockRateInGHz() {
        return clockRateInGHz;
    }

    public void setClockRateInGHz(double clockRateInGHz)
            throws NoOneOrTooManyHzException {
        if (clockRateInGHz <= 0.00 || clockRateInGHz > MAX_CLOCK_RATE_IN_GHZ) {
            LOGGER.error(CLOCK_RATE_MESSAGE);
            throw new NoOneOrTooManyHzException(CLOCK_RATE_MESSAGE);
        }

        this.clockRateInGHz = clockRateInGHz;
    }

    public boolean getHasTurboBoost() {
        return hasTurboBoost;
    }

    public void setHasTurboBoost(boolean hasTurboBoost) {
        this.hasTurboBoost = hasTurboBoost;
    }

    public CPU getCPU() {
        return this;
    }

    @Override
    public void use() {
        LOGGER.info("The CPU is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        CPU cpu = (CPU) object;
        return getNumOfCores() == cpu.getNumOfCores()
                && Double.compare(getClockRateInGHz(), cpu.getClockRateInGHz()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumOfCores(), getClockRateInGHz(), CPU_MASK);
    }

    @Override
    public String toString() {
        return "CPU{" +
                "name='" + name + '\'' +
                ", numOfCores=" + numOfCores +
                ", clockRateInGHz=" + clockRateInGHz +
                ", hasTurboBoost=" + hasTurboBoost +
                '}';
    }
}
