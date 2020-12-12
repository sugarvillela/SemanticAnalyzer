// Generated file, do not edit
// Last write: 12/03/2020 21:23:09
package generated.lists;

import generated.code.DATATYPE;
import static generated.code.DATATYPE.*;

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
            (0x03000001 <= index && index <= 0x0D410000)
        ) {
            return LIST_VOTE;
        }
        if (
            (0x0F000001 <= index && index <= 0x015004000)
        ) {
            return LIST_BOOLEAN;
        }
        throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
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
            case 0x05000001:
            case 0x05301000:
            case 0x08000001:
            case 0x09301000:
            case 0x0A000001:
            case 0x0D100010:
            case 0x0D301000:
                return LIST_VOTE;
            case 0x0F000000:
            case 0x010000000:
            case 0x011000000:
            case 0x012000000:
            case 0x013000000:
            case 0x014000000:
            case 0x015000000:
                return LIST_BOOLEAN;
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: 0x%X", index));
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
                throw new IllegalStateException(String.format("Dev err: unknown datatype: %s", datatype.toString()));
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
                return 0x0F000001;
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: %s", datatype.toString()));
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
                return 0x0D410000;
            case LIST_BOOLEAN:
                return 0x015004000;
            default:
                throw new IllegalStateException(String.format("Dev err: unknown datatype: %s", datatype.toString()));
        }
    }
}
