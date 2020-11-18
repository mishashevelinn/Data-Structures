package labs.singlyLL;

public interface List<T> {
    void insert(T newElement) throws Exception;

    void remove() throws Exception;

    void replace(T newElement) throws Exception;

    void clear();

    boolean isEmpty();

    boolean gotoBeginning();

    boolean gotoEnd();

    boolean gotoNext() throws Exception;

    boolean gotoPrior() throws Exception;

    T getCursor();


}

