// Generated file, do not edit
// Last write: 01/13/2021 23:55:00
package generated.lists;

import generated.enums.DATATYPE;
import static generated.enums.DATATYPE.*;

public class FlagStats {
    // Pass enu value to get type:
    public static DATATYPE flagTypeByRange (int index) {
        if (
            (0x01 <= index && index <= 0x05)
        ) {
            return LIST_STRING;
        }
        if (
            (0x07 <= index && index <= 0x08)
        ) {
            return LIST_NUMBER;
        }
        if (
            (0x01000001 <= index && index <= 0x01303000)
        ) {
            return LIST_DISCRETE;
        }
        if (
            (0x02000001 <= index && index <= 0x02000005)
        ) {
            return LIST_SCOPES;
        }
        if (
            (0x03000001 <= index && index <= 0x0D301000)
        ) {
            return LIST_VOTE;
        }
        if (
            (0x0E000001 <= index && index <= 0x014004000)
        ) {
            return LIST_BOOLEAN;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // Pass base index (category name) to get type:
    public static DATATYPE flagTypeByBaseIndex (int index) {
        switch (index) {
            case 0x00:
                return LIST_STRING;
            case 0x06:
                return LIST_NUMBER;
            case 0x01000000:
            case 0x01100000:
            case 0x01200000:
            case 0x01300000:
                return LIST_DISCRETE;
            case 0x02000000:
                return LIST_SCOPES;
            case 0x03000000:
            case 0x03200000:
            case 0x04000000:
            case 0x06200000:
            case 0x07400000:
            case 0x09200000:
            case 0x09400000:
            case 0x0D000000:
            case 0x0D200000:
                return LIST_VOTE;
            case 0x0E000000:
            case 0x0F000000:
            case 0x010000000:
            case 0x011000000:
            case 0x012000000:
            case 0x013000000:
            case 0x014000000:
                return LIST_BOOLEAN;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    // Store Settings:
    public static int getWRow () {
        return 8;
    }
    public static int getWCol () {
        return 4;
    }
    public static int getWVal () {
        return 4;
    }
    // Stats by type:
    public static int getSize (DATATYPE datatype) {
        switch (datatype) {
            case LIST_STRING:
                return 0x06;
            case LIST_NUMBER:
                return 0x03;
            case LIST_DISCRETE:
                return 0x015;
            case LIST_SCOPES:
                return 0x06;
            case LIST_VOTE:
                return 0x03F;
            case LIST_BOOLEAN:
                return 0x05A;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int getLowIndex (DATATYPE datatype) {
        switch (datatype) {
            case LIST_STRING:
                return 0x01;
            case LIST_NUMBER:
                return 0x07;
            case LIST_DISCRETE:
                return 0x01000001;
            case LIST_SCOPES:
                return 0x02000001;
            case LIST_VOTE:
                return 0x03000001;
            case LIST_BOOLEAN:
                return 0x0E000001;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int getHighIndex (DATATYPE datatype) {
        switch (datatype) {
            case LIST_STRING:
                return 0x05;
            case LIST_NUMBER:
                return 0x08;
            case LIST_DISCRETE:
                return 0x01303000;
            case LIST_SCOPES:
                return 0x02000005;
            case LIST_VOTE:
                return 0x0D301000;
            case LIST_BOOLEAN:
                return 0x014004000;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
}
