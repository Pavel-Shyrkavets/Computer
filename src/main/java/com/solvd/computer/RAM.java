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

import com.solvd.computer.exceptions.NoOneOrTooManyHzException;
import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class RAM implements IUse {
    private final static int RAM_MASK = 60;
    private final static int MAX_MEMORY_SIZE_IN_GIGABYTES = 1024;
    private final static int MAX_CLOCK_RATE_IN_MHZ = 20000;
    private final static int MAX_RAM_TYPE_NAME_LENGTH = 100;
    private final static String MEMORY_SIZE_MESSAGE =
            "The size of the RAM should not be less than or equal to 0 "
            + "or exceed " + MAX_MEMORY_SIZE_IN_GIGABYTES + " GBs.";
    private final static String CLOCK_RATE_MESSAGE =
            "The RAM clock rate should not be less than or equal to 0 "
            + "or exceed " + MAX_CLOCK_RATE_IN_MHZ + " MHz.";
    private final static String RAM_TYPE_NAME_MESSAGE =
            "The length of the RAM type name should not be equal to 0 "
            + "or exceed " + MAX_RAM_TYPE_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(RAM.class);

    private int memorySizeInGBs = 0;
    private int clockRateInMHz = 0;
    private String type = "";

    public RAM() {}

    public RAM(int memorySizeInGBs, int clockRateInMHz, String type)
            throws NoOneOrTooManyHzException, ZeroOrTooManyCharactersException {
        if (memorySizeInGBs <= 0 || memorySizeInGBs > MAX_MEMORY_SIZE_IN_GIGABYTES) {
            LOGGER.info(MEMORY_SIZE_MESSAGE);
            System.exit(1);
        }

        if (clockRateInMHz <= 0 || clockRateInMHz > MAX_CLOCK_RATE_IN_MHZ) {
            LOGGER.error(CLOCK_RATE_MESSAGE);
            throw new NoOneOrTooManyHzException(CLOCK_RATE_MESSAGE);
        }

        if (type.isEmpty() || type.length() > MAX_RAM_TYPE_NAME_LENGTH) {
            LOGGER.error(RAM_TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(RAM_TYPE_NAME_MESSAGE);
        }

        this.memorySizeInGBs = memorySizeInGBs;
        this.clockRateInMHz = clockRateInMHz;
        this.type = type;
    }

    public int getMemorySizeInGBs() {
        return memorySizeInGBs;
    }

    public void setMemorySizeInGBs(int memorySizeInGBs) {
        if (memorySizeInGBs <= 0 || memorySizeInGBs > MAX_MEMORY_SIZE_IN_GIGABYTES) {
            LOGGER.info(MEMORY_SIZE_MESSAGE);
            System.exit(1);
        }

        this.memorySizeInGBs = memorySizeInGBs;
    }

    public int getClockRateInMHz() {
        return clockRateInMHz;
    }

    public void setClockRateInMHz(int clockRateInMHz)
            throws NoOneOrTooManyHzException {
        if (clockRateInMHz <= 0 || clockRateInMHz > MAX_CLOCK_RATE_IN_MHZ) {
            LOGGER.error(CLOCK_RATE_MESSAGE);
            throw new NoOneOrTooManyHzException(CLOCK_RATE_MESSAGE);
        }

        this.clockRateInMHz = clockRateInMHz;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ZeroOrTooManyCharactersException {
        if (type.isEmpty() || type.length() > MAX_RAM_TYPE_NAME_LENGTH) {
            LOGGER.error(RAM_TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(RAM_TYPE_NAME_MESSAGE);
        }

        this.type = type;
    }

    @Override
    public void use() {
        LOGGER.info("The RAM is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        RAM ram = (RAM) object;
        return getMemorySizeInGBs() == ram.getMemorySizeInGBs()
                && getClockRateInMHz() == ram.getClockRateInMHz();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemorySizeInGBs(), getClockRateInMHz(), RAM_MASK);
    }

    @Override
    public String toString() {
        return "RAM{" +
                "memorySizeInGBs=" + memorySizeInGBs +
                ", clockRateInMHz=" + clockRateInMHz +
                ", type='" + type + '\'' +
                '}';
    }
}
