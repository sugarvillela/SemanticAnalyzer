package recursivelist;

import generated.enums.ACTION;

import java.util.ArrayList;

public interface IMark {
    void mark(ACTION action);
    ArrayList<ACTION> getMarkedActions();
}
