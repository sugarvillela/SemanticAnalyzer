// Generated file, do not edit
// Last write: 11/18/2020 16:36:34
package generated.lists;

import generated.code.DATATYPE;
import static generated.code.DATATYPE.*;

public class FlagStats {
    // Pass enu value to get type:
    public static DATATYPE flagTypeByRange (int index) {
        if (
            0x01 <= index && index <= 0x0E
        ) {
            return LIST_STRING;
        }
        if (
            0x010 <= index && index <= 0x013
        ) {
            return LIST_NUMBER;
        }
        if (
            0x010000001 <= index && index <= 0x0110000A0
        ) {
            return LIST_DISCRETE;
        }
        if (
            0x020000001 <= index && index <= 0x040000080
        ) {
            return LIST_BOOLEAN;
        }
        throw new IllegalStateException("Dev err: unknown datatype");
    }
    // Pass base index (category name) to get type:
    public static DATATYPE flagTypeByBaseIndex (int index) {
        switch (index) {
            case 0x00:
            case 0x07:
                return LIST_STRING;
            case 0x0F:
                return LIST_NUMBER;
            case 0x010000000:
            case 0x011000000:
                return LIST_DISCRETE;
            case 0x020000000:
            case 0x030000000:
            case 0x040000000:
                return LIST_BOOLEAN;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    // Store Settings:
    public static int getWRow () {
        return 4;
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
                return 0x0F;
            case LIST_NUMBER:
                return 0x05;
            case LIST_DISCRETE:
                return 0x017;
            case LIST_BOOLEAN:
                return 0x014;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int getLowIndex (DATATYPE datatype) {
        switch (datatype) {
            case LIST_STRING:
                return 0x01;
            case LIST_NUMBER:
                return 0x010;
            case LIST_DISCRETE:
                return 0x010000001;
            case LIST_BOOLEAN:
                return 0x020000001;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
    public static int getHighIndex (DATATYPE datatype) {
        switch (datatype) {
            case LIST_STRING:
                return 0x0E;
            case LIST_NUMBER:
                return 0x013;
            case LIST_DISCRETE:
                return 0x0110000A0;
            case LIST_BOOLEAN:
                return 0x040000080;
            default:
                throw new IllegalStateException("Dev err: unknown datatype");
        }
    }
}
