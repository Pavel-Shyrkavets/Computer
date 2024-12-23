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
import com.solvd.computer.interfaces.ISubstitute;
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class GPU implements ISubstitute, IUse {
    private final static int GPU_MASK = 30;
    private final static int MAX_GPU_NAME_LENGTH = 100;
    private final static int MAX_VIDEO_MEMORY_SIZE_IN_GIGABYTES = 64;
    private final static int MAX_CLOCK_RATE_IN_MHZ = 20000;
    private final static String GPU_NAME_MESSAGE =
            "The GPU name length should not be equal to 0 "
            + "or exceed " + MAX_GPU_NAME_LENGTH + " characters.";
    private final static String VIDEO_MEMORY_SIZE =
            "The size of the video memory integrated into the GPU "
            + "should not be less 0 or exceed " + MAX_VIDEO_MEMORY_SIZE_IN_GIGABYTES
            + " GBs.";
    private final static String CLOCK_RATE_MESSAGE =
            "The GPU clock rate should not be less than or equal to 0 "
            + "or exceed " + MAX_CLOCK_RATE_IN_MHZ + " MHz.";
    private final static Logger LOGGER = LogManager.getLogger(GPU.class);

    private String name = "";
    private int videoMemorySizeInGBs = 0;
    private int clockRateInMHz = 0;
    private boolean isIntegrated = false;

    public GPU() {}

    public GPU(String name, int videoMemorySizeInGBs, int clockRateInMHz,
               boolean isIntegrated) throws ZeroOrTooManyCharactersException,
               NoOneOrTooManyHzException {
        if (name.isEmpty() || name.length() > MAX_GPU_NAME_LENGTH) {
            LOGGER.error(GPU_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(GPU_NAME_MESSAGE);
        }

        if (videoMemorySizeInGBs < 0
                || videoMemorySizeInGBs > MAX_VIDEO_MEMORY_SIZE_IN_GIGABYTES) {
            LOGGER.info(VIDEO_MEMORY_SIZE);
            System.exit(1);
        }

        if (clockRateInMHz <= 0 || clockRateInMHz > MAX_CLOCK_RATE_IN_MHZ) {
            LOGGER.error(CLOCK_RATE_MESSAGE);
            throw new NoOneOrTooManyHzException(CLOCK_RATE_MESSAGE);
        }

        this.name = name;
        this.videoMemorySizeInGBs = videoMemorySizeInGBs;
        this.clockRateInMHz = clockRateInMHz;
        this.isIntegrated = isIntegrated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ZeroOrTooManyCharactersException {
        if (name.isEmpty() || name.length() > MAX_GPU_NAME_LENGTH) {
            LOGGER.error(GPU_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(GPU_NAME_MESSAGE);
        }

        this.name = name;
    }

    public int getVideoMemorySizeInGBs() {
        return videoMemorySizeInGBs;
    }

    public void setVideoMemorySizeInGBs(int videoMemorySizeInGBs) {
        if (videoMemorySizeInGBs < 0
                || videoMemorySizeInGBs > MAX_VIDEO_MEMORY_SIZE_IN_GIGABYTES) {
            LOGGER.info(VIDEO_MEMORY_SIZE);
            System.exit(1);
        }

        this.videoMemorySizeInGBs = videoMemorySizeInGBs;
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

    public boolean getIntegrated() {
        return isIntegrated;
    }

    public void setIntegrated(boolean integrated) {
        this.isIntegrated = integrated;
    }

    @Override
    public void use() {
        LOGGER.info("The GPU is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        GPU gpu = (GPU) object;
        return getVideoMemorySizeInGBs() == gpu.getVideoMemorySizeInGBs()
                && getClockRateInMHz() == gpu.getClockRateInMHz()
                && getIntegrated() == gpu.getIntegrated();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVideoMemorySizeInGBs(), getClockRateInMHz(),
                            getIntegrated(), GPU_MASK);
    }

    @Override
    public String toString() {
        return "GPU{" +
                "name='" + name + '\'' +
                ", videoMemorySizeInGBs=" + videoMemorySizeInGBs +
                ", clockRateInMHz=" + clockRateInMHz +
                ", isIntegrated=" + isIntegrated +
                '}';
    }
}
