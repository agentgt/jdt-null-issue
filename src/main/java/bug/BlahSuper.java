package bug;

import java.io.IOException;
import java.io.OutputStream;

public sealed interface BlahSuper<T, E extends Exception> permits Blah, BlahOther {

}
abstract non-sealed class BlahOther implements BlahSuper<OutputStream, IOException> {
	
}
