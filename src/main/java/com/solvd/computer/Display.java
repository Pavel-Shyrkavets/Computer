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

import com.solvd.computer.exceptions.NoOneOrTooManyInchesException;
import com.solvd.computer.exceptions.ZeroOrTooManyCharactersException;
import com.solvd.computer.interfaces.IDisplay;
import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Display implements IDisplay, IUse {
    private final static int DISPLAY_MASK = 20;
    private final static double MAX_SCREEN_SIZE_IN_INCHES = 50.0;
    private final static int MAX_RESOLUTION_NAME_LENGTH = 12;
    private final static int MAX_DISPLAY_TYPE_NAME_LENGTH = 100;
    private final static String SCREEN_SIZE_MESSAGE =
            "The screen size should not be less than or equal to 0.0 "
            + "or exceed " + MAX_SCREEN_SIZE_IN_INCHES + " inches.";
    private final static String RESOLUTION_MESSAGE =
            "The resolution name length should not be 0 "
            + "or exceed " + MAX_RESOLUTION_NAME_LENGTH + " characters.";
    private final static String DISPLAY_TYPE_NAME_MESSAGE =
            "The length of the display type name should not be equal to 0 "
            + "or exceed " + MAX_DISPLAY_TYPE_NAME_LENGTH + " characters.";
    private final static Logger LOGGER = LogManager.getLogger(Display.class);

    private double screenSizeInInches = 0.0;
    private String resolution = "";
    private String type = "";

    public Display() {}

    public Display(double screenSizeInInches, String resolution, String type)
            throws NoOneOrTooManyInchesException, ZeroOrTooManyCharactersException {
        if (screenSizeInInches <= 0.0
                || screenSizeInInches > MAX_SCREEN_SIZE_IN_INCHES) {
            LOGGER.error(SCREEN_SIZE_MESSAGE);
            throw new NoOneOrTooManyInchesException(SCREEN_SIZE_MESSAGE);
        }

        if (resolution.isEmpty() || resolution.length() > MAX_RESOLUTION_NAME_LENGTH) {
            LOGGER.error(RESOLUTION_MESSAGE);
            throw new ZeroOrTooManyCharactersException(RESOLUTION_MESSAGE);
        }

        if (type.isEmpty() || type.length() > MAX_DISPLAY_TYPE_NAME_LENGTH) {
            LOGGER.error(DISPLAY_TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(DISPLAY_TYPE_NAME_MESSAGE);
        }

        this.screenSizeInInches = screenSizeInInches;
        this.resolution = resolution;
        this.type = type;
    }

    public double getScreenSizeInInches() {
        return screenSizeInInches;
    }

    public void setScreenSizeInInches(double screenSizeInInches)
            throws NoOneOrTooManyInchesException {
        if (screenSizeInInches <= 0.0
                || screenSizeInInches > MAX_SCREEN_SIZE_IN_INCHES) {
            LOGGER.error(SCREEN_SIZE_MESSAGE);
            throw new NoOneOrTooManyInchesException(SCREEN_SIZE_MESSAGE);
        }

        this.screenSizeInInches = screenSizeInInches;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution)
            throws ZeroOrTooManyCharactersException {
        if (resolution.isEmpty() || resolution.length() > MAX_RESOLUTION_NAME_LENGTH) {
            LOGGER.error(RESOLUTION_MESSAGE);
            throw new ZeroOrTooManyCharactersException(RESOLUTION_MESSAGE);
        }

        this.resolution = resolution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws ZeroOrTooManyCharactersException {
        if (type.isEmpty() || type.length() > MAX_DISPLAY_TYPE_NAME_LENGTH) {
            LOGGER.error(DISPLAY_TYPE_NAME_MESSAGE);
            throw new ZeroOrTooManyCharactersException(DISPLAY_TYPE_NAME_MESSAGE);
        }

        this.type = type;
    }

    @Override
    public void use() {
        LOGGER.info("The display is used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        Display display = (Display) object;
        return Double.compare(getScreenSizeInInches(),
                display.getScreenSizeInInches()) == 0
                && Objects.equals(getResolution(), display.getResolution());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScreenSizeInInches(), getResolution(), DISPLAY_MASK);
    }

    @Override
    public String toString() {
        return "Display{" +
                "screenSizeInInches=" + screenSizeInInches +
                ", resolution='" + resolution + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
