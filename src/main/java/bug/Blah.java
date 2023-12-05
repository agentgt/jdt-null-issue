package bug;

import java.io.IOException;
import java.io.OutputStream;

public abstract non-sealed class Blah<T, E extends Exception> implements BlahSuper<T, E> {

	public abstract static class InnerBlah extends Blah<OutputStream, IOException> {

	}

}

