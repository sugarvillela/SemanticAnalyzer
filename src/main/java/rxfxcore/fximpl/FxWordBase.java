package rxfxcore.fximpl;

import datasource.IntSource;
import datasource.ObjectSource;
import datasource.StringSource;
import generated.enums.ACTION;
import generated.enums.DATATYPE;
import rxfxcore.interfaces.FxWord;

public abstract class FxWordBase implements FxWord {
    protected ObjectSource actionSource, datatypeSource;
    protected IntSource enuSource, numberSource;
    protected StringSource stringSource;

    protected final void initActions(ACTION... actions){
        actionSource = new ObjectSource(actions);
    }
    protected final void initDatatypes(DATATYPE... datatypes){
        datatypeSource = new ObjectSource(datatypes);
    }
    protected final void initEnus(int... enus){
        enuSource = new IntSource(enus);
    }
    protected final void initNumbers(int... numbers){
        numberSource = new IntSource(numbers);
    }
    protected final void initStrings(String... strings){
        stringSource = new StringSource(strings);
    }

    @Override
    public final ACTION nextAction() {
        return (ACTION)actionSource.next();
    }

    @Override
    public final DATATYPE nextDatatype() {
        return (DATATYPE)datatypeSource.next();
    }

    @Override
    public final int nextEnu() {
        return enuSource.next();
    }

    @Override
    public final int nextNumber() {
        return numberSource.next();
    }

    @Override
    public final String nextString() {
        return stringSource.next();
    }

    @Override
    public void rewindIterators() {
        if(actionSource != null){
            actionSource.rewind();
        }
        if(datatypeSource != null){
            datatypeSource.rewind();
        }
        if(enuSource != null){
            enuSource.rewind();
        }
        if(numberSource != null){
            numberSource.rewind();
        }
        if(stringSource != null){
            stringSource.rewind();
        }
    }

    @Override
    public void disp() {
        System.out.println(this.toString());
    }

    protected abstract void initRuntimeTargets(int... targets);
}
