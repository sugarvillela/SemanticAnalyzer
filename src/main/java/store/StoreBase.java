package store;

import generated.enums.DATATYPE;
import generated.lists.FlagStats;

public abstract class StoreBase implements IStore {
    protected final DATATYPE datatype;

    protected StoreBase(DATATYPE datatype) {
        this.datatype = datatype;
    }

    @Override
    public ItrStore getStoreItr() {
        return this.getStoreItr(
                FlagStats.getLowIndex(datatype),
                FlagStats.getHighIndex(datatype)
        );
    }
}
