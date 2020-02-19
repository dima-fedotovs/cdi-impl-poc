package guru.bug.cdipoc.apt;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;

public class PoCCompilerPlugin implements Plugin {
    @Override
    public String getName() {
        return "PoCCompilerPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        System.out.println("COMPILER PLUGIN INIT");
    }
}
