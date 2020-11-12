// Generated file, do not edit
// Last write: 11/12/2020 10:33:55
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
        return null;
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
    public static int getSizeListString () {
        return 13;
    }
    public static int getLowIndexListString () {
        return 1;
    }
    public static int getHighIndexListString () {
        return 14;
    }
    public static int getSizeListNumber () {
        return 4;
    }
    public static int getLowIndexListNumber () {
        return 16;
    }
    public static int getHighIndexListNumber () {
        return 19;
    }
    public static int getSizeListDiscrete () {
        return 21;
    }
    public static int getLowIndexListDiscrete () {
        return 268435457;
    }
    public static int getHighIndexListDiscrete () {
        return 285212832;
    }
    public static int getSizeListBoolean () {
        return 17;
    }
    public static int getLowIndexListBoolean () {
        return 536870913;
    }
    public static int getHighIndexListBoolean () {
        return 1073741952;
    }
}
