package store;

public interface IStore {

    /**Sets the row and column specified by encoded index in enu
     * @param enu enum number from generated.lists */
    void set(int enu);

    /**For String impl only, sets element at enu
     * @param enu enum number from generated.lists */
    void set(int enu, String val);

    /**For Number impl only, sets element at enu
     * @param enu enum number from generated.lists */
    void set(int enu, int val);

    /**Drops the row and column specified by encoded index in enu
     * @param enu enum number from generated.lists */
    void drop(int enu);

    /**One of four 'get' methods: boolean result, always meaningful
     * @param enu enum number from generated.lists
     * @return true if a position has been set, false if 0 or null */
    boolean getBoolean(int enu);

    /**One of four 'get' methods: int result, meaningful for discrete and number impl
     * discrete: value only, right shifted to true value
     * number: same as getNumber()
     * @param enu enum number from generated.lists
     * @return numeric value stored
     */
    int getNumber(int enu);     // discrete: value only, right shifted to true value

    /**One of four 'get' methods: String result, meaningful for String impl only, null otherwise
     * @param enu enum number from generated.lists
     * @return String value stored or null if not set */
    String getString(int enu);  // value only, right shifted to true value

    /**One of four 'get' methods: Object result, always meaningful.
     * Know what type of data to expect and type cast it
     * @param enu enum number from generated.lists
     * @return Object version of main type of the store */
    Object get(int enu);

    /**Meaningful for composite and number impl only, 0 otherwise
     * @param enu enum number from generated.lists
     * @return In-place stored value, no shift */
    int getState(int enu);

    /**@param enu enum number from generated.lists. Meaningful in composite impl, others count all set
     * @return the number of set or non-zero positions in the category specified */
    int numNonZero(int enu);

    /**@param enu enum number from generated.lists. Meaningful in composite impl, others count all set
     * @return true if any set or non-zero positions in the category specified */
    boolean anyNonZero(int enu);

    void disp();

    ItrStore getItr();

    ItrStore getItr(int startEnu, int stopEnu);

    public interface ItrStore {
        void rewind(int startEnu, int stopEnu);
        int nextKey();
        boolean hasNext();
        boolean nextBoolean();
        int nextNumber();
        String nextString();
    }
}
