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

import com.solvd.computer.interfaces.IUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Port implements IUse {
    private final static int PORT_MASK = 50;
    private final static int MAX_NUM_OF_USB_TYPE_A_PORTS = 4;
    private final static int MAX_NUM_OF_MICRO_SD_PORTS = 2;
    private final static int MAX_NUM_OF_HDMI_PORTS = 2;
    private final static int MAX_NUM_OF_HEAD_AND_MIC_COMBO_PORTS = 2;
    private final static int MAX_NUM_OF_USB_TYPE_C_PORTS = 4;
    private final static String USB_TYPE_A_MESSAGE =
            "The number of the integrated USB type A ports should not be less than 0 "
            + "or exceed " + MAX_NUM_OF_USB_TYPE_A_PORTS + " ports.";
    private final static String MICRO_SD_MESSAGE =
            "The number of the integrated MicroSD ports should not be less than 0 "
            + "or exceed " + MAX_NUM_OF_MICRO_SD_PORTS + " ports.";
    private final static String HDMI_MESSAGE =
            "The number of the integrated HDMI ports should not be less than 0 "
            + "or exceed " + MAX_NUM_OF_HDMI_PORTS + " ports.";
    private final static String HEAD_AND_MIC_COMBO_MESSAGE =
            "The number of the integrated Headphones and Microphone Combo ports should "
            + "not be less than 0 or exceed " + MAX_NUM_OF_HEAD_AND_MIC_COMBO_PORTS
            + " ports.";
    private final static String USB_TYPE_C_MESSAGE =
            "The number of the integrated USB Type C ports should not be less than 0 "
            + "or exceed " + MAX_NUM_OF_USB_TYPE_C_PORTS + " ports.";
    private final static Logger LOGGER = LogManager.getLogger(Port.class);

    private int numOfUSBTypeA = 0;
    private int numOfMicroSD = 0;
    private int numOfHDMI = 0;
    private int numOfHeadMicCombo = 0;
    private int numOfUSBTypeC = 0;

    public Port() {}

    public Port(int numOfUSBTypeA, int numOfMicroSD, int numOfHDMI,
                int numOfHeadMicCombo, int numOfUSBTypeC) {
        if (numOfUSBTypeA < 0 || numOfUSBTypeA > MAX_NUM_OF_USB_TYPE_A_PORTS) {
            LOGGER.info(USB_TYPE_A_MESSAGE);
            System.exit(1);
        }

        if (numOfMicroSD < 0 || numOfMicroSD > MAX_NUM_OF_MICRO_SD_PORTS) {
            LOGGER.info(MICRO_SD_MESSAGE);
            System.exit(1);
        }

        if (numOfHDMI < 0 || numOfHDMI > MAX_NUM_OF_HDMI_PORTS) {
            LOGGER.info(HDMI_MESSAGE);
            System.exit(1);
        }

        if (numOfHeadMicCombo < 0
                || numOfHeadMicCombo > MAX_NUM_OF_HEAD_AND_MIC_COMBO_PORTS) {
            LOGGER.info(HEAD_AND_MIC_COMBO_MESSAGE);
            System.exit(1);
        }

        if (numOfUSBTypeC < 0 || numOfUSBTypeC > MAX_NUM_OF_USB_TYPE_C_PORTS) {
            LOGGER.info(USB_TYPE_C_MESSAGE);
            System.exit(1);
        }

        this.numOfUSBTypeA = numOfUSBTypeA;
        this.numOfMicroSD = numOfMicroSD;
        this.numOfHDMI = numOfHDMI;
        this.numOfHeadMicCombo = numOfHeadMicCombo;
        this.numOfUSBTypeC = numOfUSBTypeC;
    }

    public int getNumOfUSBTypeA() {
        return numOfUSBTypeA;
    }

    public void setNumOfUSBTypeA(int numOfUSBTypeA) {
        if (numOfUSBTypeA < 0 || numOfUSBTypeA > MAX_NUM_OF_USB_TYPE_A_PORTS) {
            LOGGER.info(USB_TYPE_A_MESSAGE);
            System.exit(1);
        }

        this.numOfUSBTypeA = numOfUSBTypeA;
    }

    public int getNumOfMicroSD() {
        return numOfMicroSD;
    }

    public void setNumOfMicroSD(int numOfMicroSD) {
        if (numOfMicroSD < 0 || numOfMicroSD > MAX_NUM_OF_MICRO_SD_PORTS) {
            LOGGER.info(MICRO_SD_MESSAGE);
            System.exit(1);
        }

        this.numOfMicroSD = numOfMicroSD;
    }

    public int getNumOfHDMI() {
        return numOfHDMI;
    }

    public void setNumOfHDMI(int numOfHDMI) {
        if (numOfHDMI < 0 || numOfHDMI > MAX_NUM_OF_HDMI_PORTS) {
            LOGGER.info(HDMI_MESSAGE);
            System.exit(1);
        }

        this.numOfHDMI = numOfHDMI;
    }

    public int getNumOfHeadMicCombo() {
        return numOfHeadMicCombo;
    }

    public void setNumOfHeadMicCombo(int numOfHeadMicCombo) {
        if (numOfHeadMicCombo < 0
                || numOfHeadMicCombo > MAX_NUM_OF_HEAD_AND_MIC_COMBO_PORTS) {
            LOGGER.info(HEAD_AND_MIC_COMBO_MESSAGE);
            System.exit(1);
        }

        this.numOfHeadMicCombo = numOfHeadMicCombo;
    }

    public int getNumOfUSBTypeC() {
        return numOfUSBTypeC;
    }

    public void setNumOfUSBTypeC(int numOfUSBTypeC) {
        if (numOfUSBTypeC < 0 || numOfUSBTypeC > MAX_NUM_OF_USB_TYPE_C_PORTS) {
            LOGGER.info(USB_TYPE_C_MESSAGE);
            System.exit(1);
        }

        this.numOfUSBTypeC = numOfUSBTypeC;
    }

    @Override
    public void use() {
        LOGGER.info("The ports are used.");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (hashCode() != object.hashCode()) return false;
        Port port = (Port) object;
        return getNumOfMicroSD() == port.getNumOfMicroSD()
                && getNumOfHDMI() == port.getNumOfHDMI()
                && getNumOfUSBTypeC() == port.getNumOfUSBTypeC();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumOfMicroSD(), getNumOfHDMI(), getNumOfUSBTypeC(),
                            PORT_MASK);
    }

    @Override
    public String toString() {
        return "Ports{" +
                "numOfUSBTypeA=" + numOfUSBTypeA +
                ", numOfMicroSD=" + numOfMicroSD +
                ", numOfHDMI=" + numOfHDMI +
                ", numOfHeadphonesMicrophoneCombo=" + numOfHeadMicCombo +
                ", numOfUSBTypeC=" + numOfUSBTypeC +
                '}';
    }
}
